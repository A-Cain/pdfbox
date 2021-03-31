package org.apache.pdfbox.tools;

import java.io.File;
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
    public void testAnnotateText() throws IOException
    {
        AddText add = new AddText();
        
        String path = "../tools/src/main/java/org/apache/pdfbox/tools/AddTextResources/examplePDFWithText.pdf";

        PDDocument actual = add.loadFileandInitializeStream(path);

        PDFTextStripper stripper = new PDFTextStripper();
        String key = stripper.getText(actual);
        
        String str = add.annotateText(" annotation");

        String expected = key + " annotation";

        expected = expected.replace("\n", "").replace("\r", "");

        Boolean result = str.equals(expected);

        key = key.replace("\n", "").replace("\r", "");

        add.writeText(0, key, PDType1Font.HELVETICA, 12);

        actual.close();

        assertTrue(result);

    }

    @Test
    public void testWriteText()
    {

    }
}
