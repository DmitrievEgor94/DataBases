package com.mycompany.dao.impl;

import com.mycompany.dao.AuthorDao;
import com.mycompany.dao.BookDao;
import com.mycompany.models.Author;
import com.mycompany.models.Book;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

public class BookDaoImpl implements BookDao {

    private Connection connection;

    private static final Logger log = Logger.getLogger(BookDaoImpl.class);

    public BookDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Book book = new Book();

                book.setId(rs.getInt(1));
                book.setTitle(rs.getString(2));
                book.setPublicationDate(rs.getDate(3).toLocalDate());
                book.setAuthors(getAuthors(book.getId()));

                books.add(book);
            }

        } catch (SQLException e) {
            log.error(e);
        }

        return books;
    }

    @Override
    public Book findById(int id) {
        Book book = new Book();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            rs.next();
            book.setId(rs.getInt(1));
            book.setTitle(rs.getString(2));
            book.setPublicationDate(rs.getDate(3).toLocalDate());
            book.setAuthors(getAuthors(book.getId()));

        } catch (SQLException e) {
            log.error(e);
        }

        return book;
    }

    @Override
    public void insert(Book book) {
        try {
            PreparedStatement statementForBooks = connection.prepareStatement(SQL_INSERT_BOOKS, Statement.RETURN_GENERATED_KEYS);

            statementForBooks.setString(1, book.getTitle());
            statementForBooks.setDate(2, valueOf(book.getPublicationDate()));
            statementForBooks.execute();

            ResultSet generatedKeys = statementForBooks.getGeneratedKeys();

            generatedKeys.next();

            book.setId(generatedKeys.getInt(1));

            PreparedStatement statementForBooksAuthors = connection.prepareStatement(SQL_INSERT_BOOKS_AUTHORS);

            if (book.getAuthors() != null) {
                for (Author author : book.getAuthors()) {
                    statementForBooksAuthors.setInt(1, book.getId());
                    statementForBooksAuthors.setInt(2, author.getId());
                    statementForBooksAuthors.execute();
                }
            }

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void update(Book book) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement(SQL_UPDATE);

            updateStatement.setString(1, book.getTitle());
            updateStatement.setDate(2, valueOf(book.getPublicationDate()));
            updateStatement.setInt(3, book.getId());

            updateStatement.execute();

            PreparedStatement deleteStatement = connection.prepareStatement(SQL_DELETE_BOOKS_AUTHORS);
            deleteStatement.setInt(1, book.getId());
            deleteStatement.execute();

            PreparedStatement statementForBooksAuthors = connection.prepareStatement(SQL_INSERT_BOOKS_AUTHORS);
            statementForBooksAuthors.execute();

            if (book.getAuthors() != null) {
                for (Author author : book.getAuthors()) {
                    statementForBooksAuthors.setInt(1, book.getId());
                    statementForBooksAuthors.setInt(2, author.getId());
                    statementForBooksAuthors.execute();
                }
            }

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Book book) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);

            statement.setInt(1, book.getId());

            statement.execute();

        } catch (SQLException e) {
            log.error(e);
        }

    }

    private List<Author> getAuthors(int bookId) throws SQLException {
        AuthorDao authorDao = new AuthorDaoImpl(connection);

        PreparedStatement statement = connection.prepareStatement(SQL_GET_AUTHORS);
        statement.setInt(1, bookId);

        ResultSet rs = statement.executeQuery();

        List<Author> authors = new ArrayList<>();

        while (rs.next()) {
            int author_id = rs.getInt(1);

            Author author = authorDao.findById(author_id);

            authors.add(author);
        }

        return authors;
    }

}
