package com.mycompany;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mycompany.models.Author;
import com.mycompany.models.Book;
import com.mycompany.models.OriginalModelsContainer;
import com.mycompany.models.Publisher;
import com.mycompany.serializers.Serializer;
import com.mycompany.serializers.xmlformat.sax.XmlSaxSerializer;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class XmlToJson {

    private static final String FILE_FOR_PUBLISHERS = "publishers";
    private static final String FILE_FOR_BOOKS = "books";
    private static final String FILE_FOR_AUTHORS = "authors";
    private static final Logger log = Logger.getLogger(XmlToJson.class);
    private static final String FILE_WITH_OBJECTS = Serializer.class.getResource("bookPublishers.xml").getPath();


    private static void transform() {

        Serializer serializer = new XmlSaxSerializer();

        ObjectMapper mapper = new ObjectMapper();

        try {

            OriginalModelsContainer container = serializer.deserializeObject(FILE_WITH_OBJECTS);

            List<Author> authors = container.getAuthors();
            List<Book> books = container.getBooks();
            List<Publisher> publishers = container.getPublishers();

            AtomicInteger counter = new AtomicInteger(0);

            authors.forEach(author -> author.setId(counter.incrementAndGet()));
            counter.set(0);

            books.forEach(book -> book.setId(counter.incrementAndGet()));
            counter.set(0);

            publishers.forEach(publisher -> publisher.setId(counter.incrementAndGet()));



            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_FOR_PUBLISHERS), publishers);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_FOR_BOOKS), books);
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(FILE_FOR_AUTHORS), authors);

        } catch (IOException e) {
            log.error(e);
        }
    }

    public static void main(String[] args) {
        transform();
    }


}
