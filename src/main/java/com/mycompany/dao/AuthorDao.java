package com.mycompany.dao;

import com.mycompany.models.Author;

import java.util.List;

public interface AuthorDao {
    String AUTHOR_TABLE_NAME = "authors";
    String SQL_FIND_ALL = "select id, name, day_of_birthday, day_of_death, sex from " + AUTHOR_TABLE_NAME + " limit 40";
    String SQL_FIND_BY_ID = "select id, name, day_of_birthday, day_of_death, sex from " + AUTHOR_TABLE_NAME + " where id = ?";
    String SQL_INSERT = "insert into " + AUTHOR_TABLE_NAME + "( name, day_of_birthday, day_of_death, sex) values(?,?,?,?)";
    String SQL_DELETE = "delete from " + AUTHOR_TABLE_NAME + " where id = ?";
    String SQL_UPDATE = "update " + AUTHOR_TABLE_NAME + " set name = ?, day_of_birthday = ?, day_of_death = ?, sex = ? where id = ?";

    List<Author> findAll();

    Author findById(int id);

    void insert(Author author);

    void update(Author author);

    void delete(Author author);
}
