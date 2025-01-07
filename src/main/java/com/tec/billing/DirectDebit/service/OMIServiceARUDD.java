package com.tec.billing.DirectDebit.service;

import com.tec.billing.DirectDebit.entity.ReturnedDebitItem;
import com.tec.billing.DirectDebit.repository.ReturnedDebitItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
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

    public String arudd() throws IOException {
        List<ReturnedDebitItem> returnedDebitItems = processFileAndMoveToArchive(aruddFilePath, archiveFilePath);
        returnedDebitItemRepository.saveAll(returnedDebitItems);
        return null;
    }

    // Method to read the file and store data in List<ReturnedDebitItem>
    private List<ReturnedDebitItem> processFileAndMoveToArchive(String filePath, String archiveFolder) throws IOException {
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
                    rdi.setProcessingDate(LocalDateTime.parse(fields[4]));
                    rdi.setAmount(fields[5]);
                    rdi.setCurrency(fields[6]);
                    rdi.setAccountName(fields[7]);
                    rdi.setSortCode(fields[8]);
                    rdi.setAccountNo(fields[9]);
                    rdiList.add(rdi);
                }
            }
            System.out.println("File processed successfully.");
            System.out.println("rdiList size = "+ rdiList.size());

            // Step 3: Move the file to the Archive folder
            Path sourcePath = Paths.get(filePath);
            Path targetPath = Paths.get(archiveFolder, "ARUDD_" + System.currentTimeMillis() + ".csv");

            // Moving the file
            Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);//to be checked
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
