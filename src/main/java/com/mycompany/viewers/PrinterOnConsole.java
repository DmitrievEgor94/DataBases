package com.mycompany.viewers;

import com.mycompany.models.Author;
import com.mycompany.models.Book;
import com.mycompany.models.Publisher;

import java.util.List;

public class PrinterOnConsole implements Viewer {

    @Override
    public void showPublishers(List<Publisher> publishers) {

        for (Publisher publisher : publishers) {
            System.out.println("Publisher:");
            System.out.printf("Name: %s, Books: \n", publisher.getName());

            if (publisher.getBooks() != null) {
                showBooks(publisher.getBooks());
            }
        }
    }

    @Override
    public void showBooks(List<Book> books) {
        for (Book book : books) {
            int numberOfSpacesBooks = 3;
            String formatForBook = String.format("%" + numberOfSpacesBooks + "s", " ");

            System.out.println(formatForBook + "Book:");
            System.out.printf(formatForBook + "Title: %s, Publication date: %s, Authors: \n", book.getTitle(), book.getPublicationDate());

            if (book.getAuthors() != null) {
                showAuthors(book.getAuthors());
            }

        }
    }

    @Override
    public void showAuthors(List<Author> authors) {
        for (Author author : authors) {
            int numberOfSpacesAuthors = 5;
            String formatForAuthor = String.format("%" + numberOfSpacesAuthors + "s", " ");

            System.out.println(formatForAuthor + "Author:");
            System.out.printf(formatForAuthor + "Name: %s, Birth day: %s, Death day:%s, Sex:%s \n",
                    author.getName(), author.getDayOfBirthday(), author.getDayOfDeath(), author.getSex());
        }
    }
}
