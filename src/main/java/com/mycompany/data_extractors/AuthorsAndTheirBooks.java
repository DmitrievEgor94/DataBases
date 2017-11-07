package com.mycompany.data_extractors;

import com.mycompany.models.Author;
import com.mycompany.models.Book;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.AbstractMap.SimpleEntry;
import static java.util.Map.Entry;


public class AuthorsAndTheirBooks {

    public static Map<Author, List<Book>> get(List<Book> books) {
        return books.stream()
                .flatMap(book -> book.getAuthors().stream()
                        .map(author -> new SimpleEntry<>(author, book)))
                .collect(Collectors.groupingBy(Entry::getKey, Collectors.mapping(Entry::getValue, Collectors.toList())));
    }
}
