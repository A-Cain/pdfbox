package org.apache.pdfbox.tools;

/*
Requirement 1.0: A tool shall provide a mechanism for users to annotate
existing PDF documents with text.

Requirement 1.1: In annotations composed of text, the user shall be able
to specify the font of each glyph in the text from a set of at least two
different fonts.
*/

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

public class AddText
{
    private PDDocument documentToWrite;
    private String path;
    

    public PDDocument loadFileandInitializeStream(String path) throws IOException {

        File file = new File(path);
        PDDocument doc = Loader.loadPDF(file);
        documentToWrite = doc;

        this.path = path;
    
        return doc;
    }

    public String annotateText(String annotation, int a_page) throws IOException
    {
        COSDocument doc = documentToWrite.getDocument();

        String key = "";

        if (!doc.isEncrypted()) {
            
            PDFTextStripper stripper = new PDFTextStripper();
            stripper.setStartPage(a_page+1);
            stripper.setEndPage(a_page+1);
            key = stripper.getText(documentToWrite);

            key += annotation;

            key = key.replace("\n", "").replace("\r", "");

            PDPage page = documentToWrite.getPage(a_page);

            PDPageContentStream content_stream = new PDPageContentStream(documentToWrite, page, PDPageContentStream.AppendMode.OVERWRITE, true);
            
            content_stream.beginText();

            content_stream.setFont(PDType1Font.HELVETICA, 12);

            content_stream.newLineAtOffset( 100, 700 );

            content_stream.showText(key);

            content_stream.endText();

            content_stream.close();
        }

        documentToWrite.save(this.path);

        return key;
    }

    public String writeText(int page_num, String string, PDFont font, int font_size) throws IOException
    {
        PDPage page = documentToWrite.getPage(page_num);

        PDPageContentStream content_stream = new PDPageContentStream(documentToWrite, page, PDPageContentStream.AppendMode.OVERWRITE, true);
            
        content_stream.beginText();

        content_stream.setFont(font, font_size);

        content_stream.showText(string);

        content_stream.endText();

        content_stream.close();

        return string;
    }
}
