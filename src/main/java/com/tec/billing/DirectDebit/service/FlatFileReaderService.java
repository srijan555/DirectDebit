package com.tec.billing.DirectDebit.service;

import com.tec.billing.DirectDebit.entity.ReturnedDebitItem;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class FlatFileReaderService {

    public static List<ReturnedDebitItem> parseFlatFile(String filePath) throws IOException {
        List<ReturnedDebitItem> returnedDebitItems = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        // Skip the header line
        reader.readLine();

        // Read data lines
        while ((line = reader.readLine()) != null) {
            String[] fields = line.split(",");

            // Create a new ReturnedDebitItem object
            ReturnedDebitItem item = new ReturnedDebitItem();

            // Populating the ReturnedDebitItem object with the respective field values
            item.setTransactionCode(fields[0].trim());
            /*item.setType(fields[1].trim());
            item.setCode(Integer.parseInt(fields[2].trim()));
            item.setDescription(fields[3].trim());
            item.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(fields[4].trim()));  // Date parsing
            item.setAmount(Double.parseDouble(fields[5].trim()));  // Amount
            item.setCurrency(fields[6].trim());
            item.setPayerName(fields[7].trim());
            item.setPayerId(Integer.parseInt(fields[8].trim()));
            item.setReferenceNumber(Long.parseLong(fields[9].trim()));*/

            // Add the item to the list
            returnedDebitItems.add(item);
        }

        reader.close();
        return returnedDebitItems;
    }
}
