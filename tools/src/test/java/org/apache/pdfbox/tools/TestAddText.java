package org.apache.pdfbox.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.tools.AddImage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


public class TestAddText extends TestCase
{
    
    @Test
    public void testLoadFileAndInitializeStream() throws IOException
    {
        AddText add = new AddText();
        
        String expected = "../tools/src/main/java/org/apache/pdfbox/tools/AddImageResources/examplePDFWithText.pdf";

        PDDocument actual = add.loadFileandInitializeStream(expected);
        int actualPages = actual.getNumberOfPages();
        assertEquals(1, actualPages);
    }

    @Test
    public void testAnnotateTextOnePage() throws IOException
    {
        AddText add = new AddText();
        
        String path = "../tools/src/main/java/org/apache/pdfbox/tools/AddTextResources/examplePDFWithText.pdf";

        PDDocument actual = add.loadFileandInitializeStream(path);

        PDFTextStripper stripper = new PDFTextStripper();
        String key = stripper.getText(actual);
        
        add.annotateText(" annotation", 0);

        String str = stripper.getText(actual);
        str = str.replace("\n", "").replace("\r", "");

        String expected = key + " annotation";

        expected = expected.replace("\n", "").replace("\r", "");

        Boolean result = str.equals(expected);

        key = key.replace("\n", "").replace("\r", "");

        add.writeText(0, key, PDType1Font.HELVETICA, 12);

        actual.close();

        assertTrue(result);
    }

    @Test
    public void testAnnotateTextBlankPage() throws IOException
    {
        AddText add = new AddText();
        
        String path = "../tools/src/main/java/org/apache/pdfbox/tools/AddTextResources/test_blank.pdf";

        PDDocument actual = add.loadFileandInitializeStream(path);

        PDFTextStripper stripper = new PDFTextStripper();
        String key = stripper.getText(actual);
        
        add.annotateText(" annotation", 0);

        String str = stripper.getText(actual);
        str = str.replace("\n", "").replace("\r", "");

        String expected = key + " annotation";

        expected = expected.replace("\n", "").replace("\r", "");

        Boolean result = str.equals(expected);

        key = key.replace("\n", "").replace("\r", "");

        add.writeText(0, key, PDType1Font.HELVETICA, 12);

        actual.close();

        assertTrue(result);
    }

    @Test
    public void testAnnotateTextMidPage() throws IOException
    {
        AddText add = new AddText();
        
        String old = "../tools/src/main/java/org/apache/pdfbox/tools/AddTextResources/test_2pages.pdf";
        String path = "../tools/src/main/java/org/apache/pdfbox/tools/AddTextResources/test_2pages_new.pdf";

        File in = new File(old);
        File ou = new File(path);

        try (FileInputStream fis = new FileInputStream(in);
             FileOutputStream fos = new FileOutputStream(ou))
        {

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {

                fos.write(buffer, 0, length);
            }
        }

        PDDocument actual = add.loadFileandInitializeStream(path);

        PDFTextStripper stripper = new PDFTextStripper();
        stripper.setStartPage(2);
        stripper.setEndPage(2);
        String key = stripper.getText(actual);
        
        add.annotateText(" annotation", 1);

        String str = stripper.getText(actual);
        str = str.replace("\n", "").replace("\r", "");

        String expected = key.replace("\n", "").replace("\r", "");

        expected = expected + " annotation";

        Boolean result = str.equals(expected);

        key = key.replace("\n", "").replace("\r", "");

        add.writeText(1, key, PDType1Font.HELVETICA, 12);

        actual.close();

        assertTrue(result);
    }

    @Test
    public void testWriteText()
    {
        
    }
}
