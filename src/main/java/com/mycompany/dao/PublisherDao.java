package com.mycompany.dao;

import com.mycompany.models.Publisher;

import java.util.List;

public interface PublisherDao {
    String SQL_FIND_ALL = "select * from publishers";
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " where id = ?";
    String SQL_INSERT_PUBLISHERS = "insert into publishers(id, name) values(?,?)";
    String SQL_INSERT_PUBLISHERS_BOOKS = "insert into publishers_books(publisher_id, book_id) values(?, ?)";
    String SQL_DELETE = "delete from publishers where id = ?";
    String SQL_GET_BOOKS = "select book_id from publishers_books where publisher_id = ?";

    List<Publisher> findAll();

    Publisher findById(int id);

    void insert(Publisher publisher);

    void update(Publisher publisher);

    void delete(Publisher publisher);

}
