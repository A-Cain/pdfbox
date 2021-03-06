package org.apache.pdfbox.tools;

import java.awt.Color;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


import java.util.Scanner;

public class MultiFontTest {
// this function will create PDF file with Courier text for purposes of test.
    public static final String CREATED_PDF = "tools/src/main/java/org/apache/pdfbox/tools/FontResources/Courier.pdf";
    @Test
    public void CourierTest () {
        try {
            Scanner myObj = new Scanner(System.in);
            PDDocument pdfDoc = new PDDocument();
            PDPage firstPage = new PDPage();
            // add page to the PDF document
            pdfDoc.addPage(firstPage);
            // For writing to a page content stream
            try(PDPageContentStream cs = new PDPageContentStream(pdfDoc, firstPage)){
                cs.beginText();
                    cs.setFont(PDType1Font.COURIER, 15);
                // color for the text
                cs.setNonStrokingColor(Color.BLACK);
                // starting position
                cs.newLineAtOffset(20, 750);
                cs.showText("This text is in Courier");
                // go to next line
                cs.newLine();
                cs.endText();
            }
            // save PDF document
            pdfDoc.save(CREATED_PDF);
            pdfDoc.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        assertFalse(CREATED_PDF.isEmpty());
    }

}