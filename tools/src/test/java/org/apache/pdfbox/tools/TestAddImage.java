package org.apache.pdfbox.tools;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.tools.AddImage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestAddImage{
//test class for AddImage
@Test
public void testLoadFileAndInitializeStream() throws IOException {

    AddImage add = new AddImage();
    String expected = "../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/examplePDFWithText.pdf";
    PDDocument actual = add.loadFileandInitializeStream(expected, 1);
    int actualPages = actual.getNumberOfPages();
    assertEquals(1, actualPages);
}

@Test
public void testCreateImage() throws IOException {

    int expected = 750;
    AddImage add = new AddImage();
    add.loadFileandInitializeStream("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/examplePDFWithText.pdf", 1);
    String imageString = "../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/pamcamke.jpg";
    PDImageXObject actual = add.createImage(imageString);
    assertEquals(750, actual.getWidth());

    }
//this test inserts an image into the second page of a pdf with text
    @Test
    public void testPDFithText() throws IOException {
        AddImage add = new AddImage();
        add.loadFileandInitializeStream("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/examplePDFWithText.pdf", 2);
        String imageString = "../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/pamcamke.jpg";
        PDImageXObject actual = add.createImage(imageString);
        add.writeImage("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/examplePDFWithTextAndImage.pdf");
        File madeFile = new File("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/examplePDFWithTextAndImage.pdf");
        assertTrue(madeFile.exists());
    }

    @Test
    public void testPageCheck() throws IOException {
        AddImage add = new AddImage();
        add.loadFileandInitializeStream("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/examplePDFWithText.pdf", 1);
        add.pageCheck(add.getDocumentToWrite(), 2);
        assertEquals(2, add.getDocumentToWrite().getNumberOfPages());
    }
//this test will insert an image into the first page of a blank PDF
    @Test
    public void testBlankPDF() throws IOException {
        AddImage add = new AddImage();
        add.loadFileandInitializeStream("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/Blank_PDF.pdf", 1);
        String imageString = "../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/pamcamke.jpg";
        PDImageXObject actual = add.createImage(imageString);
        add.writeImage("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/Blank_PDF_Test.pdf");
        File madeFile = new File("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/Blank_PDF_Test.pdf");
        assertTrue(madeFile.exists());
    }
//this test inserts an image in the middle of a blank 3-page pdf
    @Test
    public void testInsertion() throws IOException {
        AddImage add = new AddImage();
        add.loadFileandInitializeStream("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/blank_PDF_3page.pdf", 2);
        String imageString = "../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/pamcamke.jpg";
        PDImageXObject actual = add.createImage(imageString);
        add.writeImage("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/InsertionTest.pdf");
        File madeFile = new File("../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/InsertionTest.pdf");
        assertTrue(madeFile.exists());
    }

}
