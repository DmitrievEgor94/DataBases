package com.mycompany.data_extractors;

import com.mycompany.models.Author;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AuthorsSorterByAgeTest {
    @Test
    public void getSortedListOfAuthorsByAge() throws Exception {
        List<Author> authors = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        authors.add(new Author("Karova Anna", LocalDate.parse("12.09.1968", formatter), null, Author.Sex.FEMALE));
        authors.add(new Author("Vasilyeva Victoria", LocalDate.parse("23.04.1975", formatter), null, Author.Sex.FEMALE));

        List<Author> rightOrderAuthors = new ArrayList<>();

        rightOrderAuthors.add(authors.get(1));
        rightOrderAuthors.add(authors.get(0));

        assertEquals(rightOrderAuthors, AuthorsSorterByAge.get(authors));
    }

}