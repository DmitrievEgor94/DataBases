package com.mycompany.serializers.xmlformat.sax;

import com.mycompany.models.Author;
import com.mycompany.models.Book;
import com.mycompany.models.Publisher;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {

    private static final String PUBLISHER_CLASS_NAME = "Publisher";
    private static final String BOOK_CLASS_NAME = "Book";
    private static final String AUTHOR_CLASS_NAME = "Author";

    private static final String NAME_FIELD = "name";
    private static final String DAY_OF_BIRTHDAY_FIELD = "dayOfBirthDay";
    private static final String DAY_OF_DEATH_FIELD = "dayOfDeath";
    private static final String SEX_FIELD = "sex";

    static private final String ABSENT_DEATH_DATE = "-";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    private static final String TITLE_FIELD = "title";
    private static final String PUBLICATION_DATE = "publicationDate";

    private Author author;
    private Book book;
    private Publisher publisher;

    private String currentElement = "";

    private List<Author> authors;
    private List<Book> books;
    private List<Publisher> publishers;

    private boolean publisherNameField = false;

    public List<Publisher> getPublishers() {
        return publishers;
    }

    @Override
    public void startDocument() {
        authors = new ArrayList<>();
        books = new ArrayList<>();
        publishers = new ArrayList<>();
    }

    @Override
    public void endDocument() {

    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (AUTHOR_CLASS_NAME.equals(qName)) {
            author = new Author();
        }

        if (BOOK_CLASS_NAME.equals(qName)) {
            book = new Book();
        }

        if (PUBLISHER_CLASS_NAME.equals(qName)) {
            publisher = new Publisher();
            publisherNameField = true;
        }

        currentElement = qName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (NAME_FIELD.equals(currentElement)) {
            if (publisherNameField) {
                publisher.setName(new String(ch, start, length));
                publisherNameField = false;
            } else {
                author.setName(new String(ch, start, length));
            }
        }

        if (DAY_OF_BIRTHDAY_FIELD.equals(currentElement)) {
            LocalDate dayOfBirthday = LocalDate.parse(new String(ch, start, length), FORMATTER);
            author.setDayOfBirthday(dayOfBirthday);
        }

        if (DAY_OF_DEATH_FIELD.equals(currentElement)) {
            LocalDate dayOfDeath = null;

            if (!ABSENT_DEATH_DATE.equals(new String(ch, start, length))) {
                dayOfDeath = LocalDate.parse(new String(ch, start, length), FORMATTER);
            }

            author.setDayOfDeath(dayOfDeath);
        }

        if (SEX_FIELD.equals(currentElement)) {
            Author.Sex sex = Author.Sex.valueOf(new String(ch, start, length));
            author.setSex(sex);
        }

        if (TITLE_FIELD.equals(currentElement)) {
            String title = new String(ch, start, length);
            book.setTitle(title);
        }

        if (PUBLICATION_DATE.equals(currentElement)) {
            LocalDate publicationDate = LocalDate.parse(new String(ch, start, length), FORMATTER);
            book.setPublicationDate(publicationDate);
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (AUTHOR_CLASS_NAME.equals(qName)) {
            authors.add(author);
        }

        if (BOOK_CLASS_NAME.equals(qName)) {
            book.setAuthors(authors);
            authors = new ArrayList<>();
            books.add(book);
        }

        if (PUBLISHER_CLASS_NAME.equals(qName)) {
            publisher.setBooks(books);
            books = new ArrayList<>();
            publishers.add(publisher);
        }

        currentElement = "";
    }
}
