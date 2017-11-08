package com.mycompany.dao;

import com.mycompany.models.Book;

import java.util.List;

public interface BookDao {

    String BOOK_TABLE_NAME = "books";
    String SQL_FIND_ALL = "select id, title, publication from " + BOOK_TABLE_NAME + " limit 40";
    String SQL_FIND_BY_ID = "select id, title, publication from " + BOOK_TABLE_NAME + " where id = ?";
    String SQL_INSERT_BOOKS = "insert into " + BOOK_TABLE_NAME + "(title, publication) values(?,?)";
    String SQL_DELETE = "delete from " + BOOK_TABLE_NAME + " where id = ?";
    String SQL_UPDATE = "update " + BOOK_TABLE_NAME + " set title = ?, publication = ? where id = ?";

    String BOOK_AUTHORS_TABLE_NAME = "books_authors";
    String SQL_GET_AUTHORS = "select author_id from "+ BOOK_AUTHORS_TABLE_NAME+ " where book_id = ?";
    String SQL_INSERT_BOOKS_AUTHORS = "insert into " + BOOK_AUTHORS_TABLE_NAME + "(book_id, author_id) values(?,?)";
    String SQL_DELETE_BOOKS_AUTHORS = "delete from " + BOOK_AUTHORS_TABLE_NAME + " where book_id = ?";

    List<Book> findAll();

    Book findById(int id);

    void insert(Book book);

    void update(Book book);

    void delete(Book book);
}
