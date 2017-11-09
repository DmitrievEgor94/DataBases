package com.mycompany.models;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Book {

    private int id;
    private String title;
    private LocalDate publicationDate;
    private List<Author> authors;

    public Book() {
    }

    public Book(String title, LocalDate publicationDate, List<Author> authors) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.authors = authors;
    }

    @Override
    public String toString() {
        return title;
    }

    public String getTitle() {
        return title;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, publicationDate, authors);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null) return false;

        if (this.getClass() != obj.getClass()) return false;

        Book book = (Book) obj;

        return ((this.title == null) ? (book.title == null) : (this.title.equals(book.title)))
                && ((this.publicationDate == null) ? (book.publicationDate == null) : (this.publicationDate.equals(book.publicationDate)))
                && ((this.authors == null) ? (book.authors == null) : (this.authors.equals(book.authors)));

    }
}
