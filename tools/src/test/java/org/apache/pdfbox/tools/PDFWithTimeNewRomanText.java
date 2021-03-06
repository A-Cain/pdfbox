package org.apache.pdfbox.tools;

import java.awt.Color;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.util.Scanner;

public class PDFWithTimeNewRomanText {
    //using the folder to store the PDF file in for now. Changed the location of the file
    public static final String CREATED_PDF = "tools/src/main/java/org/apache/pdfbox/tools/FontResources/TimesNewRoman.pdf";
    public static void main(String[] args) {
        try {
            Scanner myObj = new Scanner(System.in);
            PDDocument pdfDoc = new PDDocument();
            PDPage firstPage = new PDPage();
            // add page to the PDF document
            pdfDoc.addPage(firstPage);
            // For writing to a page content stream
            try(PDPageContentStream cs = new PDPageContentStream(pdfDoc, firstPage)){
                cs.beginText();
                // setting font family and font size base on use input.
                //This fulfill requirement 1.1.1,
                // mechanism for font changing that we will integrate to the text annotation
                System.out.println("Enter 1 for New Times Roman or 2 for Courier ");
                int input=myObj.nextInt();
                if (input==1){
                    cs.setFont(PDType1Font.TIMES_ROMAN, 15);
                }
                else if(input==2){
                    cs.setFont(PDType1Font.COURIER, 15);
                }
                else {
                    System.out.println("You entered invalid selection ");
                }


                // color for the text
                cs.setNonStrokingColor(Color.BLACK);
                // starting position
                cs.newLineAtOffset(20, 750);
                cs.showText("This text is in Times New Roman");
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
    }
}