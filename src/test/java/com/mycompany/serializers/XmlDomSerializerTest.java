package com.mycompany.serializers;

import com.mycompany.models.Author;
import com.mycompany.models.Book;
import com.mycompany.models.Publisher;
import com.mycompany.serializers.xmlformat.dom.XmlDomSerializer;
import com.mycompany.viewers.PrinterOnConsole;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class XmlDomSerializerTest {

    private XmlDomSerializer xmlDomSerializer;

    private static final String TEST_FILE_WITH_OBJECTS = Serializer.class.getResource("testXmlDeserializer.xml").getPath();

    private List<Author> checkAuthors;
    private List<Book> checkBooks;
    private List<Publisher> checkPublishers;

    public XmlDomSerializerTest() {
        xmlDomSerializer = new XmlDomSerializer();

        checkAuthors = new ArrayList<>();
        checkBooks = new ArrayList<>();
        checkPublishers = new ArrayList<>();

        init();
    }

    private void init() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        checkAuthors.add(new Author("Karova Anna", LocalDate.parse("12.09.1968", formatter), null, Author.Sex.FEMALE));
        checkAuthors.add(new Author("Vasilyeva Victoria", LocalDate.parse("23.04.1975", formatter), null, Author.Sex.FEMALE));
        checkAuthors.add(new Author("Panarina Julia", LocalDate.parse("21.07.1974", formatter), null, Author.Sex.FEMALE));
        checkAuthors.add(new Author("Gorbachev Vladimir", LocalDate.parse("11.02.1983", formatter), null, Author.Sex.MALE));
        checkAuthors.add(new Author("Sazonova Olga", LocalDate.parse("11.02.1969", formatter), null, Author.Sex.FEMALE));

        List<Author> authorsFirstBook = new ArrayList<>();
        authorsFirstBook.add(checkAuthors.get(0));
        checkBooks.add(new Book("Innovative economy", LocalDate.parse("23.12.2013", formatter), authorsFirstBook));

        List<Author> authorsSecondBook = new ArrayList<>();
        authorsSecondBook.add(checkAuthors.get(1));
        authorsSecondBook.add(checkAuthors.get(2));
        checkBooks.add(new Book("Health Generation", LocalDate.parse("11.05.2016", formatter), authorsSecondBook));

        List<Author> authorsThirdBook = new ArrayList<>();
        authorsThirdBook.add(checkAuthors.get(3));
        authorsThirdBook.add(checkAuthors.get(4));
        checkBooks.add(new Book("Fundamentals of nutritional science", LocalDate.parse("06.01.2016", formatter), authorsThirdBook));

        checkPublishers.add(new Publisher("Asgard", checkBooks));

    }

    @Test
    public void testAuthorsDeserializer() throws Exception {
        List<Author> authors = xmlDomSerializer.deserializeObject(TEST_FILE_WITH_OBJECTS).getAuthors();

        assertEquals(checkAuthors, authors);
    }

    @Test
    public void testBooksDeserializer() throws Exception {
        List<Book> books = xmlDomSerializer.deserializeObject(TEST_FILE_WITH_OBJECTS).getBooks();

        assertEquals(checkBooks, books);
    }

    @Test
    public void testPublishersDeserializer() throws Exception {
        List<Publisher> publishers = xmlDomSerializer.deserializeObject(TEST_FILE_WITH_OBJECTS).getPublishers();

        PrinterOnConsole printerOnConsole = new PrinterOnConsole();
        printerOnConsole.showPublishers(publishers);

        assertEquals(checkPublishers, publishers);
    }
}
