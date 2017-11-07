package com.mycompany.serializers.xmlformat.dom;

import com.mycompany.models.Author;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class AuthorGetter {

    private static final String NAME_FIELD = "name";
    private static final String DAY_OF_BIRTHDAY_FIELD = "dayOfBirthDay";
    private static final String DAY_OF_DEATH_FIELD = "dayOfDeath";
    private static final String SEX_FIELD = "sex";
    static private final String ABSENT_DEATH_DATE = "-";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    static Author get(NodeList nodes) {

        String name = "";
        LocalDate dayOfBirthDay = null;
        LocalDate dayOfDeath = null;
        Author.Sex sex = null;

        for (int i = 0; i < nodes.getLength(); i++) {
            Node currentNode = nodes.item(i);

            if (NAME_FIELD.equals(currentNode.getNodeName())) {
                name = currentNode.getTextContent();
            }

            if (DAY_OF_BIRTHDAY_FIELD.equals(currentNode.getNodeName())) {
                dayOfBirthDay = LocalDate.parse(currentNode.getTextContent(), FORMATTER);
            }

            if (DAY_OF_DEATH_FIELD.equals(currentNode.getNodeName()) && !ABSENT_DEATH_DATE.equals(currentNode.getTextContent())) {
                dayOfDeath = LocalDate.parse(currentNode.getTextContent(), FORMATTER);
            }

            if (SEX_FIELD.equals(currentNode.getNodeName())) {
                sex = Author.Sex.valueOf(currentNode.getTextContent());
            }
        }

        return new Author(name, dayOfBirthDay, dayOfDeath, sex);
    }
}
