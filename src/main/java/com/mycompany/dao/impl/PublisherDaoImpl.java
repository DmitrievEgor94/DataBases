package com.mycompany.dao.impl;

import com.mycompany.dao.BookDao;
import com.mycompany.dao.PublisherDao;
import com.mycompany.models.Book;
import com.mycompany.models.Publisher;
import org.apache.log4j.Logger;

import java.sql.*;
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
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_PUBLISHERS, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, publisher.getName());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();

            generatedKeys.next();

            publisher.setId(generatedKeys.getInt(1));

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
        try {
            PreparedStatement updatePublishers = connection.prepareStatement(SQL_UPDATE);

            updatePublishers.setString(1, publisher.getName());
            updatePublishers.setInt(2, publisher.getId());

            updatePublishers.execute();

            PreparedStatement deleteStatement = connection.prepareStatement(SQL_DELETE_BOOKS);
            deleteStatement.setInt(1, publisher.getId());
            deleteStatement.execute();

            PreparedStatement statementForPublishersBooks = connection.prepareStatement(SQL_INSERT_PUBLISHERS_BOOKS);

            if (publisher.getBooks() != null) {
                for (Book book : publisher.getBooks()) {
                    statementForPublishersBooks.setInt(1, book.getId());
                    statementForPublishersBooks.setInt(2, book.getId());
                    statementForPublishersBooks.execute();
                }
            }

        } catch (SQLException e) {
            log.error(e);
        }
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
