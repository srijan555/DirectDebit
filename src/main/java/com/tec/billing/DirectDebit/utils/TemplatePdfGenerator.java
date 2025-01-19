package com.tec.billing.DirectDebit.utils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class TemplatePdfGenerator {

    public void createTemplatePdf(String templatePath) throws IOException {
        // Ensure the directory exists before creating the file
        File file = new File(templatePath);
        File directory = file.getParentFile();
        if (!directory.exists()) {
            directory.mkdirs(); // Create directories if they don't exist
        }

        // Proceed to create the PDF
        PdfWriter writer = new PdfWriter(new FileOutputStream(file));
        PdfDocument pdfDoc = new PdfDocument(writer);
        Document document = new Document(pdfDoc, PageSize.A4);

        // Load the Helvetica font
        PdfFont font = PdfFontFactory.createFont("Helvetica");

        // Add static content to the template PDF
        Paragraph title = new Paragraph("AUDDIS Information Template").setFont(font).setFontSize(16);
        document.add(title);

        // Space between title and form fields
        document.add(new Paragraph("\n"));
        document.add(new Paragraph("\n"));

        // Add placeholders for each field
        document.add(new Paragraph("Account Name: ___________________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Account No: ____________________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Amount: ________________________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Created By: ____________________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Created On: ____________________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Group Name: ____________________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Group No: _____________________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Originator Ref. No: ____________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Payment Method: _______________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Sort Code: ____________________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("System Entity Code: ___________________").setFont(font).setFontSize(12));
        document.add(new Paragraph("Transaction Type: _____________________").setFont(font).setFontSize(12));

        document.close();

        System.out.println("Template PDF created successfully at: " + templatePath);
    }
}
