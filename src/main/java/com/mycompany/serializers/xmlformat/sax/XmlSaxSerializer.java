package com.mycompany.serializers.xmlformat.sax;

import com.mycompany.models.Author;
import com.mycompany.models.Book;
import com.mycompany.models.OriginalModelsContainer;
import com.mycompany.models.Publisher;
import com.mycompany.serializers.Serializer;
import com.mycompany.serializers.xmlformat.GetterAuthorsFromBooks;
import com.mycompany.serializers.xmlformat.GetterBooksFromPublishers;
import com.mycompany.serializers.xmlformat.GetterUniqueElements;
import org.apache.log4j.Logger;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class XmlSaxSerializer implements Serializer {

    private static final Logger log = Logger.getLogger(XmlSaxSerializer.class);


    @Override
    public void serializeObjects(List<Author> authors, List<Book> books, List<Publisher> publishers, String fileWithObjects) throws IOException {
        throw new UnsupportedOperationException(this.getClass() + " doesn't support serializing!");
    }

    @Override
    public OriginalModelsContainer deserializeObject(String fileWithObjects) throws IOException {

        try {
            SaxHandler saxHandler = new SaxHandler();
            XMLReader reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(saxHandler);
            reader.parse(new InputSource(new FileInputStream(fileWithObjects)));

            List<Publisher> publishers = saxHandler.getPublishers();

            List<Book> books = GetterBooksFromPublishers.get(publishers);

            List<Author> authors = GetterAuthorsFromBooks.get(books);

            return GetterUniqueElements.get(authors, books, publishers);

        } catch (SAXException e) {
            log.error(e);
        }

        return null;
    }
}
