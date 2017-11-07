package com.mycompany.models.readers;

import com.mycompany.models.Author;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuthorsListCreator {

    private static final Logger log = Logger.getLogger(BooksListCreator.class);

    static private final String ABSENT_DEATH_DATE = "-";

    static public List<Author> getListWithAuthors() throws FileNotFoundException {

        String fullNameOfFileWithAuthors = AuthorsListCreator.class.getResource("authors.txt").getPath();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        List<Author> listOfAuthors = new ArrayList<>();

        try (Scanner scannerOfAuthors = new Scanner(new File(fullNameOfFileWithAuthors))) {
            while (scannerOfAuthors.hasNext()) {
                String name = scannerOfAuthors.next();

                LocalDate dayOfBirthday = LocalDate.parse(scannerOfAuthors.next(), formatter);

                LocalDate dayOfDeath;

                String deathDateInFile = scannerOfAuthors.next();
                if (deathDateInFile.equals(ABSENT_DEATH_DATE))
                    dayOfDeath = null;
                else
                    dayOfDeath = LocalDate.parse(deathDateInFile, formatter);

                String sexInFile = scannerOfAuthors.next();

                Author.Sex sex = Author.Sex.valueOf(sexInFile.toUpperCase());

                listOfAuthors.add(new Author(name, dayOfBirthday, dayOfDeath, sex));

            }
        } catch (DateTimeParseException e) {
            log.error(e + ". Error in file " + fullNameOfFileWithAuthors);
        }
        return listOfAuthors;
    }

}