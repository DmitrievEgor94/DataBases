package com.mycompany.serializers.xmlformat.dom;

import com.mycompany.models.Author;
import com.mycompany.models.Book;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

class BookGetter {

    private static final String TITLE_FIELD = "title";
    private static final String PUBLICATION_DATE = "publicationDate";
    private static final String AUTHORS_FIELD = "Authors";
    private static final String AUTHOR_CLASS_NAME = "Author";

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    static Book get(NodeList nodes) {

        String title = "";
        LocalDate publicationDate = null;
        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node currentNode = nodes.item(i);

            if (TITLE_FIELD.equals(currentNode.getNodeName())) {
                title = currentNode.getTextContent();
            }

            if (PUBLICATION_DATE.equals(currentNode.getNodeName())) {
                publicationDate = LocalDate.parse(currentNode.getTextContent(), FORMATTER);
            }

            if (AUTHORS_FIELD.equals(currentNode.getNodeName())) {

                for (int i1 = 0; i1 < currentNode.getChildNodes().getLength(); i1++) {
                    Node node = currentNode.getChildNodes().item(i1);

                    if (AUTHOR_CLASS_NAME.equals(node.getNodeName())) {
                        Author author = AuthorGetter.get(node.getChildNodes());
                        authors.add(author);
                    }
                }
            }
        }
        return new Book(title, publicationDate, authors);
    }

}
