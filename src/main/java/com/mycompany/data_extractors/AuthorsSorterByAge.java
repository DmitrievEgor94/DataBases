package com.mycompany.data_extractors;

import com.mycompany.models.Author;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

public class AuthorsSorterByAge {

    public static List<Author> get(List<Author> authors) {
        LocalDate currentDay = LocalDate.now();

        return authors.stream()
                .sorted(Comparator.comparingLong(value -> value.getDayOfDeath() != null
                        ? DAYS.between(value.getDayOfBirthday(), value.getDayOfDeath())
                        : DAYS.between(value.getDayOfBirthday(), currentDay)))
                .collect(Collectors.toList());

    }
}
