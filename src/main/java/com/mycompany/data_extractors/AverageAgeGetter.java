package com.mycompany.data_extractors;

import com.mycompany.models.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.OptionalDouble;

import static java.time.temporal.ChronoUnit.YEARS;

public class AverageAgeGetter {
    public static OptionalDouble get(List<Author> authors) {
        LocalDate currentDay = LocalDate.now();

        return authors.stream()
                .mapToLong(x -> {
                    if (x.getDayOfDeath() != null) {
                        return YEARS.between(x.getDayOfBirthday(), x.getDayOfDeath());
                    } else {
                        return YEARS.between(x.getDayOfBirthday(), currentDay);
                    }
                })
                .average();
    }
}
