package com.mycompany.data_extractors;

import com.mycompany.models.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.YEARS;

public class PensionersGetter {
    public static List<Author> get(List<Author> authors) {
        LocalDate currentDay = LocalDate.now();

        Predicate<Author> conditionsForPensioners = (x) -> {
            if (x.getDayOfDeath() != null) {
                if (YEARS.between(x.getDayOfBirthday(), currentDay) > 65 && x.getSex() == Author.Sex.MALE
                        || YEARS.between(x.getDayOfBirthday(), currentDay) > 63 && x.getSex() == Author.Sex.FEMALE)
                    return true;
            }
            return false;
        };

        return authors.stream()
                .filter(conditionsForPensioners)
                .collect(Collectors.toList());
    }

}
