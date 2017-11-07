package com.mycompany.dao;

import com.mycompany.models.Author;

import java.util.List;

public interface AuthorDao {
    String SQL_FIND_ALL = "select * from authors";
    String SQL_FIND_BY_ID = SQL_FIND_ALL + " where id = ?";
    String SQL_INSERT = "insert into authors(id, name, day_of_birthday, day_of_death, sex) values(?,?,?,?,?)";
    String SQL_DELETE = "delete from authors where id = ?";
    String SQL_UPDATE = "update authors set name = ?, day_of_birthday = ?, day_of_death = ?, sex = ? where id = ?";

    List<Author> findAll();

    Author findById(int id);

    void insert(Author author);

    void update(Author author);

    void delete(Author author);
}
