package com.tec.billing.DirectDebit.service;

import com.tec.billing.DirectDebit.entity.AruddStatus;
import com.tec.billing.DirectDebit.entity.OutboundMandateInformation;
import com.tec.billing.DirectDebit.entity.ReturnedDebitItem;
import com.tec.billing.DirectDebit.repository.AruddStatusRepository;
import com.tec.billing.DirectDebit.repository.OutboundMandateInformationRepository;
import com.tec.billing.DirectDebit.repository.ReturnedDebitItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OMIServiceARUDD {

    @Value("${directdebit.ARUDD.inbound.path}")
    private String aruddFilePath;

    @Value("${directdebit.ARUDD.inbound.archive.path}")
    private String archiveFilePath;

    @Autowired
    ReturnedDebitItemRepository returnedDebitItemRepository;

    @Autowired
    OutboundMandateInformationRepository outboundMandateInformationRepository;

    @Autowired
    AruddStatusRepository aruddStatusRepository;

    public String arudd() throws IOException {
        String inboundFilePath = getFilePath(aruddFilePath);
        //List<ReturnedDebitItem> returnedDebitItems = processFileAndMoveToArchive(inboundFilePath, archiveFilePath);
        //returnedDebitItemRepository.saveAll(returnedDebitItems);
        updateDdiStatusBasedOnReturnCode();
        return null;
    }

    private void updateDdiStatusBasedOnReturnCode() {
        List<ReturnedDebitItem> allUnNotifiedYn = returnedDebitItemRepository.getAllUnNotifiedYn();
        List<OutboundMandateInformation> allOmi = outboundMandateInformationRepository.findAll();
        List<AruddStatus> allAruddStatus = aruddStatusRepository.findAll();
        // Step 1: Iterate over allUnNotifiedYn to compare with allOmi
        for (ReturnedDebitItem rdi : allUnNotifiedYn) {
            String reference = rdi.getReference();
            String code = rdi.getReturnCode();
            // Step 2: Iterate over allOmi to find a matching originatorReferenceNo
            for (OutboundMandateInformation omi : allOmi) {
                if (omi.getOriginatorReferenceNumber().equals(reference)) {
                    // Step 3: Find the corresponding statusDesc in allAruddStatus using the code
                    String statusDesc = getStatusDescForCode(code, allAruddStatus);
                    if (statusDesc != null) {
                        // Step 4: Set the ddiStatus of the OutboundMandateInformation to statusDesc
                        omi.setDdiStatus(statusDesc);
                        outboundMandateInformationRepository.save(omi);
                    }
                }
            }
            rdi.setNotifiedYn("Y");
            returnedDebitItemRepository.save(rdi);
        }
    }

    // Helper method to find the status description from allAruddStatus based on the code
    private String getStatusDescForCode(String code, List<AruddStatus> allAruddStatus) {
        for (AruddStatus aruddStatus : allAruddStatus) {
            if (aruddStatus.getStatusCode().equals(code)) {
                return aruddStatus.getDdiStatus();
            }
        }
        return null;  // Return null if no match is found
    }


    public String getFilePath(String directoryPath) throws IOException {
        // Get the list of files in the directory
        DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(directoryPath));
        // Get the first file from the directory (without iteration)
        Path firstFile = stream.iterator().next();
        // Get the file name (without path)
        String fileName = firstFile.getFileName().toString();
        return directoryPath + "/" + fileName;
    }

    // Method to read the file and store data in List<ReturnedDebitItem>
    public List<ReturnedDebitItem> processFileAndMoveToArchive(String filePath, String archiveFolder) throws IOException {
        List<ReturnedDebitItem> rdiList = new ArrayList<>();
        BufferedReader br = null;
        FileWriter writer = null;

        try {
            // Step 1: Read the file
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));

            // Skip the header (first line)
            String header = br.readLine();  // Read file properties line
            System.out.println("File Properties: " + header);

            // Step 2: Process each line
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields.length == 10) {
                    ReturnedDebitItem rdi = new ReturnedDebitItem();
                    rdi.setReference(fields[0]);
                    rdi.setTransactionCode(fields[1]);
                    rdi.setReturnCode(fields[2]);
                    rdi.setReasonForReturn(fields[3]);
                    //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    rdi.setProcessingDate(fields[4]);
                    rdi.setAmount(fields[5]);
                    rdi.setCurrency(fields[6]);
                    rdi.setAccountName(fields[7]);
                    rdi.setSortCode(fields[8]);
                    rdi.setAccountNo(fields[9]);
                    rdi.setNotifiedYn("N");
                    rdi.setCreatedBy("ADMIN");
                    rdi.setCreatedOn(LocalDateTime.now());
                    rdiList.add(rdi);
                    returnedDebitItemRepository.save(rdi);
                }
            }
            System.out.println("File processed successfully.");
            System.out.println("rdiList size = "+ rdiList.size());

            // Step 3: Move the file to the Archive folder
            Path sourcePath = Paths.get(filePath);
            Path targetPath = Paths.get(archiveFolder, "ARUDD_" + System.currentTimeMillis() + ".csv");

            // Moving the file
            try {
                Files.move(sourcePath, targetPath, StandardCopyOption.COPY_ATTRIBUTES);//to be checked
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("File moved to archive: " + targetPath.toString());

        } catch (IOException e) {
            System.err.println("Error processing the file: " + e.getMessage());
            throw e;
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException ex) {
                    System.err.println("Error closing BufferedReader: " + ex.getMessage());
                }
            }
        }
        return rdiList;
    }
}

//read arudd file
//save data in rdi table
//move file to archive folder after processing
//load rdi list 2 where IsNotifiedYN=Y

