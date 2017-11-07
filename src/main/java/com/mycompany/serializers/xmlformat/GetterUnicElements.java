package com.mycompany.serializers.xmlformat;

import com.mycompany.models.Author;
import com.mycompany.models.Book;
import com.mycompany.models.OriginalModelsContainer;
import com.mycompany.models.Publisher;

import java.util.List;

public class GetterUnicElements {

    public static OriginalModelsContainer get(List<Author> authors, List<Book> books, List<Publisher> publishers) {

        books.forEach(book -> book.getAuthors().forEach(a -> a = authors.get(authors.indexOf(a))));

        publishers.forEach(publisher -> publisher.getBooks().forEach(b->b=books.get(books.indexOf(b))));

        return new OriginalModelsContainer(authors, books, publishers);
    }
}
