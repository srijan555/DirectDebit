package com.tec.billing.DirectDebit.utils;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.tec.billing.DirectDebit.entity.OutboundMandateInformation;
import org.springframework.stereotype.Component;

import javax.xml.bind.*;
import java.io.IOException;
import java.io.StringReader;

@Component
public class SendEmailForAuddis {

    //deserialize xml to object, we can also send omi directly
    public OutboundMandateInformation parseXml(String xmlString) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(OutboundMandateInformation.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (OutboundMandateInformation) unmarshaller.unmarshal(new StringReader(xmlString));
    }

    public void generatePdf(OutboundMandateInformation omi, String templatePath, String outputFilePath) throws IOException {
        PdfReader reader = new PdfReader(templatePath);
        PdfWriter writer = new PdfWriter(outputFilePath);
        PdfDocument pdfDoc = new PdfDocument(reader, writer);
        Document document = new Document(pdfDoc, PageSize.A4);
        // Load the font
        PdfFont font = PdfFontFactory.createFont("Helvetica-Bold");
/*        // Stamp the values on the PDF
        Paragraph title = new Paragraph("AUDDIS Information Template").setFont(font).setFontSize(20).setFixedPosition(100, 700, 200);
        document.add(title);*/
        document.add(new Paragraph("Account Name: " + omi.getAccountName()).setFont(font).setFontSize(12).setFixedPosition(100, 700, 200));
        document.add(new Paragraph("Account No: " + omi.getAccountNo()).setFont(font).setFontSize(12).setFixedPosition(100, 680, 200));
        document.add(new Paragraph("Amount: " + omi.getAmount()).setFont(font).setFontSize(12).setFixedPosition(100, 660, 200));
        document.add(new Paragraph("Created By: " + omi.getCreatedBy()).setFont(font).setFontSize(12).setFixedPosition(100, 640, 200));
        document.add(new Paragraph("Created On: " + omi.getCreatedOn()).setFont(font).setFontSize(12).setFixedPosition(100, 620, 200));
        document.add(new Paragraph("Group Name: " + omi.getGroupName()).setFont(font).setFontSize(12).setFixedPosition(100, 600, 200));
        document.add(new Paragraph("Group No: " + omi.getGroupNo()).setFont(font).setFontSize(12).setFixedPosition(100, 580, 200));
        document.add(new Paragraph("Originator Reference No: " + omi.getOriginatorReferenceNumber()).setFont(font).setFontSize(12).setFixedPosition(100, 560, 200));
        document.add(new Paragraph("Payment Method: " + omi.getPaymentMethod()).setFont(font).setFontSize(12).setFixedPosition(100, 540, 200));
        document.add(new Paragraph("Sort Code: " + omi.getSortCode()).setFont(font).setFontSize(12).setFixedPosition(100, 520, 200));
        document.add(new Paragraph("System Entity Code: " + omi.getSystemEntityCode()).setFont(font).setFontSize(12).setFixedPosition(100, 500, 200));
        document.add(new Paragraph("System Transaction Seq: " + omi.getSystemTransactionSeq()).setFont(font).setFontSize(12).setFixedPosition(100, 480, 200));
        document.add(new Paragraph("Transaction Type: " + omi.getTransactionType()).setFont(font).setFontSize(12).setFixedPosition(100, 460, 200));
        document.close();
    }
}
