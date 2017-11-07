package com.mycompany.viewers;

import com.mycompany.models.Author;
import com.mycompany.models.Book;
import com.mycompany.models.Publisher;

import java.util.List;

public interface Viewer {
    void showPublishers(List<Publisher> publishers);

    void showBooks(List<Book> books);

    void showAuthors(List<Author> authors);
}
