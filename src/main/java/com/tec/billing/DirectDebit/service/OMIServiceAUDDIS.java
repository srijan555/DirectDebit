package com.tec.billing.DirectDebit.service;

import com.tec.billing.DirectDebit.entity.EntityRegister;
import com.tec.billing.DirectDebit.entity.OutboundMandateInformation;
import com.tec.billing.DirectDebit.entity.OutputFormsSchedule;
import com.tec.billing.DirectDebit.entity.Transaction;
import com.tec.billing.DirectDebit.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OMIServiceAUDDIS {

    @Autowired
    EntityRegisterRepository entityRegisterRepository;

    @Autowired
    OutboundMandateInformationRepository omiRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    OutputFormsScheduleRepository ofsRepository;

    /*@Autowired
    private static CalenderHolidayRepository calenderHolidayRepository;*/

    @Value("${directdebit.AUDDIS.CSV}")
    private String filePathAUDDIS;

    @Value("${directdebit.AUDDIS.XML}")
    private String xmlFilePath;

/*    @Value("${directdebit.parameter.workingDaysToAdd}")
    private int activeMandateDays;

    static String year = "2025";
    public List<java.util.Date> holidayList = calenderHolidayRepository.getUkHolidayDatesForYear(year);
    public List<LocalDate> ukHolidayList = holidayList.stream().map(date -> date.toInstant()
            .atZone(ZoneId.systemDefault()).toLocalDate()).collect(Collectors.toList());*/

    public String auddis() throws JAXBException, IOException {
        LocalDateTime currentBusinessDate = getCurrentBusinessDate();
        List<EntityRegister> entityRegisterBeforeBusinessDate = entityRegisterRepository.findEntityRegisterBeforeBusinessDate(currentBusinessDate);
        List<Transaction> transactionList = createTransactionfromEntityRegister(entityRegisterBeforeBusinessDate);
        List<OutboundMandateInformation> listOMI = createOMIfromEntityRegister(entityRegisterBeforeBusinessDate);
        updateSTSinOMI(listOMI, transactionList);
        List<OutboundMandateInformation> omiListForFlatFile = loadOmiDataForFlatFile();
        omiRepository.saveAll(listOMI);
        entityRegisterRepository.saveAll(entityRegisterBeforeBusinessDate);
        boolean csvGenerated = generateCSVfileForAUDDIS(omiListForFlatFile);
        if(csvGenerated){
            List<OutputFormsSchedule> ofsList = createOfsFromOmi(omiListForFlatFile);
            try {
                ofsRepository.saveAll(ofsList);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            updateFileYNinOMI(omiListForFlatFile);
        }
        //Testing working day logic
        List<LocalDate> ukHolidayList = new ArrayList<>();
        LocalDate localDate = addWorkingDays(LocalDate.now(), 5, ukHolidayList, "desc");
        return null;
    }

    public LocalDate addWorkingDays(LocalDate startDate, int workingDaysToAdd, List<LocalDate> ukHolidayList, String order) {
        LocalDate currentDate = startDate;
        int addedWorkingDays = 0;
        // Loop until we have added the required working days
        while (addedWorkingDays < workingDaysToAdd) {
            if(order.equalsIgnoreCase("asc")){
                currentDate = currentDate.plusDays(1); // Move to next day
            } else if (order.equalsIgnoreCase("desc")) {
                currentDate = currentDate.plusDays(-1); // Move to prev day
            }
            // Check if it's a working day (Mon-Fri) and not a UK public holiday
            if (isWorkingDay(currentDate, ukHolidayList)) {
                addedWorkingDays++;
            }
        }
        return currentDate;
    }

    // Check if the given date is a working day (Mon-Fri) and not a UK holiday
    private static boolean isWorkingDay(LocalDate date, List<LocalDate> ukHolidayList) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        // Check if it's a weekend or a UK holiday
        //return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && !UK_HOLIDAYS.contains(date);
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY && !ukHolidayList.contains(date);
    }

    private List<OutputFormsSchedule> createOfsFromOmi(List<OutboundMandateInformation> omiListForFlatFile) throws JAXBException, IOException {
        List<OutputFormsSchedule> ofsList = new ArrayList<>();
        for(OutboundMandateInformation omi : omiListForFlatFile){
            String auddisXML = generateXml(omi);
            String dateFormatted = new SimpleDateFormat("dd_MM_YYYY").format(new java.util.Date());
            String updatedXmlFilePath = xmlFilePath + "/AUDDIS_" + omi.getGroupNo() +"_" + dateFormatted + ".xml";
            saveXmlToFile(omi, updatedXmlFilePath, auddisXML);
            OutputFormsSchedule ofs = new OutputFormsSchedule();
            ofs.setOutputDirectory(xmlFilePath);//path of xml file to be set
            ofs.setOutputFormCode("DD_AUDDIS_XML_TO_DAAS");
            ofs.setOutputFileName(updatedXmlFilePath);
            ofs.setSystemEntityCode(omi.getSystemEntityCode());
            ofs.setOutputStatus("SCHEDULED");
            ofs.setOutputResponse(auddisXML);
            ofs.setCreatedBy("ADMIN");// to be updated with security context holder username
            ofs.setCreatedOn(Date.valueOf(LocalDate.now()));
            ofsList.add(ofs);
        }
        return ofsList;
    }

    public static void saveXmlToFile(OutboundMandateInformation omi, String xmlFilePath, String auddisXML) throws JAXBException, IOException {
        File file = new File(xmlFilePath);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(auddisXML);
        }
    }

    public static String generateXml(OutboundMandateInformation omi) throws JAXBException {
        // Create JAXB context and marshaller
        JAXBContext context = JAXBContext.newInstance(OutboundMandateInformation.class);
        Marshaller marshaller = context.createMarshaller();
        // To format the XML nicely
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        // Convert the object to XML string
        StringWriter stringWriter = new StringWriter();
        marshaller.marshal(omi, stringWriter);
        return stringWriter.toString();
    }

    private void updateFileYNinOMI(List<OutboundMandateInformation> omiListForFlatFile) {
        for(OutboundMandateInformation omi : omiListForFlatFile){
            omi.setFileYn("Y");
            omiRepository.save(omi);
        }
    }

    private boolean generateCSVfileForAUDDIS(List<OutboundMandateInformation> omiList) {
        String dateFormatted = new SimpleDateFormat("dd_MM_YYYY").format(new java.util.Date());
        String filePath = filePathAUDDIS + "/AUDDIS_" + dateFormatted + ".csv";
        boolean csvGenerated = generateCSV(filePath, omiList);
        return csvGenerated;
    }

    public static boolean generateCSV(String filePath, List<OutboundMandateInformation> omiList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Sort Code,Account No,Account Name,ORN,Amount,Transaction Type");  // column headers
            writer.newLine();
            for (OutboundMandateInformation omi : omiList) {
                String[] row = {
                        omi.getSortCode(),
                        omi.getAccountNo(),
                        omi.getAccountName(),
                        omi.getOriginatorReferenceNumber(),
                        String.valueOf(omi.getAmount()),
                        omi.getTransactionType()
                };
                writer.write(String.join(",", row));
                writer.newLine();
            }
            System.out.println("CSV file generated successfully at " + filePath);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error writing CSV file.");
            return false;
        }
    }

    private List<OutboundMandateInformation> loadOmiDataForFlatFile() {
        List<OutboundMandateInformation> omiListForFlatFile = omiRepository.loadOmiForInactiveAndFileAsN();
        return omiListForFlatFile;
    }

    private void updateSTSinOMI(List<OutboundMandateInformation> listOMI, List<Transaction> transactionList) {
        for (OutboundMandateInformation omi : listOMI) {
            for (Transaction tl : transactionList) {
                if (omi.getSystemEntityCode().equals(tl.getSystemEntityCode())) {
                    omi.setSystemTransactionSeq(tl.getSystemTransactionSeq());
                    break;
                }
            }
        }
    }

    private List<Transaction> createTransactionfromEntityRegister(List<EntityRegister> entityRegisterBeforeBusinessDate) {
        List<Transaction> transactionList = new ArrayList<>();
        for(int i=0; i< entityRegisterBeforeBusinessDate.size(); i++){
            Transaction transaction = new Transaction();
            transaction.setTransactionType("CREATE_MANDATE");
            transaction.setSystemEntityCode(entityRegisterBeforeBusinessDate.get(i).getSystemEntityCode());
            transaction.setTransactionEffectiveDate(Date.valueOf(LocalDate.now()));
            transactionList.add(transaction);
            transactionRepository.save(transaction);
        }
        return transactionList;
    }

    /*private void saveData() {
    }*/

    private LocalDateTime getCurrentBusinessDate() {
        String inputDate = "2025-01-05 00:00:00.000000";
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS");
        LocalDateTime currentDate = LocalDateTime.parse(inputDate, inputFormatter);
        System.out.println("Parsed LocalDateTime inside OMIService.auddis(): " + currentDate);
        return currentDate;
    }

    private List<OutboundMandateInformation> createOMIfromEntityRegister(List<EntityRegister> entityRegisterBeforeBusinessDate) {
        List<OutboundMandateInformation> listOMI = new ArrayList<>();
        for(int i=0; i< entityRegisterBeforeBusinessDate.size(); i++){
            OutboundMandateInformation omi = new OutboundMandateInformation();
            omi.setAccountName(entityRegisterBeforeBusinessDate.get(i).getAccountName());
            omi.setAccountNo(entityRegisterBeforeBusinessDate.get(i).getAccountNo());
            //active mandate date
            omi.setAmount(0.00);
            omi.setCreatedBy(entityRegisterBeforeBusinessDate.get(i).getCreatedBy());//later user id will be taken from SecurityContextHolder
            omi.setCreatedOn(Date.valueOf(LocalDate.now()));
            omi.setDdiStatus("Inactive");
            //ddi status addacs date
            //bank amend date
            //paymnet method amend date
            omi.setDdiStatusPrevDate(Date.valueOf(LocalDate.now()));
            omi.setDdiStatusUpdateDate(Date.valueOf(LocalDate.now().plusDays(5)));//later to be changed with working day logic
            //omi.setDdiStatusUpdateDate(Date.valueOf(addWorkingDays(LocalDate.now(), activeMandateDays, ukHolidayList,"asc")));
            omi.setFileYn("N");
            omi.setGroupEffectiveDate(entityRegisterBeforeBusinessDate.get(i).getGroupEffectiveDate());
            omi.setGroupName(entityRegisterBeforeBusinessDate.get(i).getGroupName());
            omi.setGroupNo(entityRegisterBeforeBusinessDate.get(i).getGroupNo());
            // is addacs yn
            //is bank amend yn
            //is payment method yn
            omi.setOriginatorReferenceNumber("TEC-"+entityRegisterBeforeBusinessDate.get(i).getGroupNo());
            omi.setPaymentMethod("DIRECTDEBIT");
            omi.setSortCode(entityRegisterBeforeBusinessDate.get(i).getSortCode());
            omi.setSystemEntityCode(entityRegisterBeforeBusinessDate.get(i).getSystemEntityCode());
            //system transaction seq
            omi.setTransactionType("0N");
            listOMI.add(omi);
            entityRegisterBeforeBusinessDate.get(i).setIsMandateCreatedYN("Y");
            //omiRepository.save(omi);
        }
        return listOMI;
    }

    //load ER where er.createdOn<=bd -DONE
    //model map ER to OMI, update er.IsMandateCreatedYN=Y -DONE
    //persist OMI -DONE
    //load OMI where ddiStatus=INACTIVE and fileYN!=Y -DONE
    //generate flat file -DONE
    //place in sftp location -DONE
    //generate transaction -DONE
    //generate xml -DONE
    //implement working day logic with calander holiday -DONE
}
