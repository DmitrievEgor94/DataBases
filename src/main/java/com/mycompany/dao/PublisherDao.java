package com.mycompany.dao;

import com.mycompany.models.Publisher;

import java.util.List;

public interface PublisherDao {

    String PUBLISHER_TABLE_NAME = "publishers";
    String SQL_FIND_ALL = "select id, name from " + PUBLISHER_TABLE_NAME + " limit 40";
    String SQL_FIND_BY_ID = "select id, name from " + PUBLISHER_TABLE_NAME + " where id = ?";
    String SQL_INSERT_PUBLISHERS = "insert into " + PUBLISHER_TABLE_NAME + "(name) values(?)";
    String SQL_DELETE = "delete from " + PUBLISHER_TABLE_NAME + " where id = ?";
    String SQL_UPDATE = "update " + PUBLISHER_TABLE_NAME + " set name = ? where id = ?";

    String PUBLISHERS_BOOKS_TABLE_NAME = "publishers_books";
    String SQL_INSERT_PUBLISHERS_BOOKS = "insert into " + PUBLISHERS_BOOKS_TABLE_NAME + "(publisher_id, book_id) values(?, ?)";
    String SQL_GET_BOOKS = "select book_id from " + PUBLISHERS_BOOKS_TABLE_NAME + " where publisher_id = ?";
    String SQL_DELETE_BOOKS = "delete from " + PUBLISHERS_BOOKS_TABLE_NAME + " where publisher_id = ?";

    List<Publisher> findAll();

    Publisher findById(int id);

    void insert(Publisher publisher);

    void update(Publisher publisher);

    void delete(Publisher publisher);

}
