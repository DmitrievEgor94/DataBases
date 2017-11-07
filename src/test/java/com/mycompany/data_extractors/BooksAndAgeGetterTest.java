package com.mycompany.data_extractors;

import com.mycompany.models.Author;
import com.mycompany.models.Book;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BooksAndAgeGetterTest {
    @Test
    public void get() throws Exception {
        List<Author> authors = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        authors.add(new Author("Karova Anna", LocalDate.parse("12.09.1968", formatter), null, Author.Sex.FEMALE));
        authors.add(new Author("Vasilyeva Victoria", LocalDate.parse("23.04.1975", formatter), null, Author.Sex.FEMALE));
        authors.add(new Author("Panarina Julia", LocalDate.parse("21.07.1974", formatter), null, Author.Sex.FEMALE));

        List<Book> books = new ArrayList<>();

        List<Author> authorsFirstBook = new ArrayList<>();
        authorsFirstBook.add(authors.get(0));
        authorsFirstBook.add(authors.get(2));
        books.add(new Book("Innovative economy", LocalDate.parse("23.12.2013", formatter), authorsFirstBook));

        List<Author> authorsSecondBook = new ArrayList<>();
        authorsSecondBook.add(authors.get(1));
        books.add(new Book("Health Generation", LocalDate.parse("11.05.2016", formatter), authorsSecondBook));

        List<String> rightTitles = new ArrayList<>();
        rightTitles.add(books.get(0).getTitle());
        rightTitles.add(books.get(1).getTitle());

        List<Long> rightAges = new ArrayList<>();
        rightAges.add((long) 3);
        rightAges.add((long) 1);

        List<String> titles = BooksAndAgeGetter.get(books).stream()
                .map(BooksAndAgeGetter.BookTitleAge::getBookTitle)
                .collect(Collectors.toList());

        List<Long> ages = BooksAndAgeGetter.get(books).stream()
                .map(BooksAndAgeGetter.BookTitleAge::getAge)
                .collect(Collectors.toList());

        assertEquals(rightTitles, titles);
        assertEquals(rightAges, ages);
    }
}