package com.mycompany.data_extractors;

import com.mycompany.models.Author;
import com.mycompany.models.Book;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CoAuthorsGetter {
    public static List<Author> get(List<Book> books) {

        return books.stream()
                .filter(b -> b.getAuthors().size() > 1)
                .map(Book::getAuthors)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
