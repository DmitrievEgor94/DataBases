package com.mycompany.serializers.xmlformat;

import com.mycompany.models.Book;
import com.mycompany.models.Publisher;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class GetterBooksFromPublishers {
    public static List<Book> get(List<Publisher> publishers) {
        return publishers.stream()
                .map(Publisher::getBooks)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }
}
