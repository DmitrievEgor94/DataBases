package com.mycompany.data_extractors;

import com.mycompany.models.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;

public class BooksAndAgeGetter {
    public static List<BookTitleAge> get(List<Book> books) {
        LocalDate currentDay = LocalDate.now();

        return books.stream()
                .map((x) -> new BookTitleAge(x.getTitle(), YEARS.between(x.getPublicationDate(), currentDay)))
                .collect(Collectors.toList());
    }

    public static class BookTitleAge {
        String bookTitle;
        long age;

        BookTitleAge(String bookTitle, long age) {
            this.bookTitle = bookTitle;
            this.age = age;
        }

        @Override
        public String toString() {
            return " " + bookTitle + " " + age;
        }

        String getBookTitle() {
            return bookTitle;
        }

        long getAge() {
            return age;
        }
    }
}
