package com.mycompany.serializers.xmlformat.dom;

import com.mycompany.models.Book;
import com.mycompany.models.Publisher;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;

class PublisherGetter {

    private static final String PUBLISHER_NAME_FIELD = "name";
    private static final String BOOKS_FIELD = "Books";
    private static final String BOOK_CLASS_NAME = "Book";

    static Publisher get(NodeList nodes) {

        String name = "";
        List<Book> books = new ArrayList<>();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node currentNode = nodes.item(i);

            if (PUBLISHER_NAME_FIELD.equals(currentNode.getNodeName())) {
                name = currentNode.getTextContent();
            }

            if (BOOKS_FIELD.equals(currentNode.getNodeName())) {

                for (int i1 = 0; i1 < currentNode.getChildNodes().getLength(); i1++) {
                    Node node = currentNode.getChildNodes().item(i1);

                    if (BOOK_CLASS_NAME.equals(node.getNodeName())) {
                        Book book = BookGetter.get(node.getChildNodes());
                        books.add(book);
                    }
                }
            }
        }
        return new Publisher(name, books);
    }
}
