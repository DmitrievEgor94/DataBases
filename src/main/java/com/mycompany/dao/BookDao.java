package com.mycompany.dao;

import com.mycompany.models.Book;

import java.util.List;

public interface BookDao {
    String SQL_FIND_ALL = "select * from books";
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " where id = ?";
    String SQL_INSERT_BOOKS = "insert into books(id,title, publication) values(?,?,?)";
    String SQL_INSERT_BOOKS_AUTHORS = "insert into books_authors(book_id, author_id) values(?,?)";
    String SQL_DELETE = "delete from books where id = ?";
    String SQL_GET_AUTHORS = "select author_id from books_authors where book_id = ?";

    List<Book> findAll();

    Book findById(int id);

    void insert(Book book);

    void update(Book book);

    void delete(Book book);
}
