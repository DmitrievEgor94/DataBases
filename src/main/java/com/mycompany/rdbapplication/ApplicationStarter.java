package com.mycompany.rdbapplication;

import com.mycompany.dao.AuthorDao;
import com.mycompany.dao.BookDao;
import com.mycompany.dao.PublisherDao;
import com.mycompany.dao.impl.AuthorDaoImpl;
import com.mycompany.dao.impl.BookDaoImpl;
import com.mycompany.dao.impl.PublisherDaoImpl;
import com.mycompany.models.Author;
import com.mycompany.models.Book;
import com.mycompany.models.OriginalModelsContainer;
import com.mycompany.models.Publisher;
import com.mycompany.serializers.Serializer;
import com.mycompany.serializers.xmlformat.sax.XmlSaxSerializer;
import com.mycompany.viewers.PrinterOnConsole;
import com.mycompany.viewers.Viewer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ApplicationStarter {

    private static final Logger log = Logger.getLogger(ApplicationStarter.class);
    private static final String FILE_WITH_OBJECTS = Serializer.class.getResource("bookPublishers.xml").getPath();

    public static void startApplication(boolean createDb) {

        Connection connection;

        try {
            if (createDb) {
                connection = DbCreator.createDb();

                if (connection == null) return;

                insertData(connection);
            } else {
                connection = DataSource.getConnection();
            }
        } catch (SQLException | IOException e) {
            log.error(e);
            return;
        }

        PublisherDao publisherDao = new PublisherDaoImpl(connection);

        List<Publisher> publishers = publisherDao.findAll();
        Viewer view = new PrinterOnConsole();

        System.out.println("До удаления:");
        view.showPublishers(publishers);
        System.out.println();

        System.out.println("После удаления:");
        publisherDao.delete(publishers.get(0));
        view.showPublishers(publisherDao.findAll());
        System.out.println();

        System.out.println("После вставки:");
        publisherDao.insert(publishers.get(0));
        view.showPublishers(publisherDao.findAll());
        System.out.println();

        System.out.println("После изменения:");
        publishers.get(0).setBooks(null);
        publisherDao.update(publishers.get(0));
        view.showPublishers(publisherDao.findAll());
        System.out.println();

        DataSource.closeConnection(connection);
    }

    private static void insertData(Connection connection) {
        AuthorDao authorDao = new AuthorDaoImpl(connection);
        BookDao bookDao = new BookDaoImpl(connection);
        PublisherDao publisherDao = new PublisherDaoImpl(connection);

        Serializer serializer = new XmlSaxSerializer();
        try {
            OriginalModelsContainer container = serializer.deserializeObject(FILE_WITH_OBJECTS);

            List<Author> authors = container.getAuthors();
            List<Book> books = container.getBooks();
            List<Publisher> publishers = container.getPublishers();

            AtomicInteger counter = new AtomicInteger(0);

            authors.forEach(author -> author.setId(counter.incrementAndGet()));
            counter.set(0);

            books.forEach(book -> book.setId(counter.incrementAndGet()));
            counter.set(0);

            publishers.forEach(publisher -> publisher.setId(counter.incrementAndGet()));

            authors.forEach(authorDao::insert);
            books.forEach(bookDao::insert);
            publishers.forEach(publisherDao::insert);

        } catch (IOException e) {
            log.error(e);
        }

    }
}