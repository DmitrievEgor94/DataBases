package com.mycompany.dao.impl;

import com.mycompany.dao.BookDao;
import com.mycompany.dao.PublisherDao;
import com.mycompany.models.Book;
import com.mycompany.models.Publisher;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PublisherDaoImpl implements PublisherDao {

    private Connection connection;

    private static final Logger log = Logger.getLogger(PublisherDaoImpl.class);

    public PublisherDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Publisher> findAll() {
        List<Publisher> publishers = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Publisher publisher = new Publisher();

                publisher.setId(rs.getInt(1));
                publisher.setName(rs.getString(2));
                publisher.setBooks(getBooks(publisher.getId()));

                publishers.add(publisher);
            }

        } catch (SQLException e) {
            log.error(e);
        }

        return publishers;
    }

    @Override
    public Publisher findById(int id) {
        Publisher publisher = new Publisher();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            rs.next();
            publisher.setId(rs.getInt(1));
            publisher.setName(rs.getString(2));
            publisher.setBooks(getBooks(publisher.getId()));

        } catch (SQLException e) {
            log.error(e);
        }

        return publisher;
    }

    @Override
    public void insert(Publisher publisher) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PUBLISHERS);

            statement.setInt(1, publisher.getId());
            statement.setString(2, publisher.getName());
            statement.execute();

            PreparedStatement statementPublishersBooks = connection.prepareStatement(SQL_INSERT_PUBLISHERS_BOOKS);

            if (publisher.getBooks() != null) {
                for (Book book : publisher.getBooks()) {
                    statementPublishersBooks.setInt(1, publisher.getId());
                    statementPublishersBooks.setInt(2, book.getId());
                    statementPublishersBooks.execute();
                }
            }

        } catch (SQLException e) {
            log.error(e);
        }

    }

    @Override
    public void update(Publisher publisher) {
        delete(publisher);
        insert(publisher);
    }

    @Override
    public void delete(Publisher publisher) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);

            statement.setInt(1, publisher.getId());

            statement.execute();

        } catch (SQLException e) {
            log.error(e);
        }
    }

    private List<Book> getBooks(int publisherId) throws SQLException {
        BookDao bookDao = new BookDaoImpl(connection);

        PreparedStatement statement = connection.prepareStatement(SQL_GET_BOOKS);
        statement.setInt(1, publisherId);

        ResultSet rs = statement.executeQuery();

        List<Book> books = new ArrayList<>();

        while (rs.next()) {
            int book_id = rs.getInt(1);

            Book book = bookDao.findById(book_id);

            books.add(book);
        }

        return books;
    }
}
