package com.mycompany.dao.impl;

import com.mycompany.dao.AuthorDao;
import com.mycompany.models.Author;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Date.valueOf;

public class AuthorDaoImpl implements AuthorDao {

    private Connection connection;

    private static final Logger log = Logger.getLogger(AuthorDaoImpl.class);

    public AuthorDaoImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Author> findAll() {
        List<Author> authors = new ArrayList<>();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Author author = new Author();

                author.setId(rs.getInt(1));
                author.setName(rs.getString(2));
                author.setDayOfBirthday(rs.getDate(3).toLocalDate());

                Date date = rs.getDate(4);
                if (date == null) {
                    author.setDayOfDeath(null);
                } else {
                    author.setDayOfDeath(date.toLocalDate());
                }

                author.setSex(Author.Sex.valueOf(rs.getString(5)));

                authors.add(author);
            }

        } catch (SQLException e) {
            log.error(e);
        }

        return authors;
    }

    @Override
    public Author findById(int id) {
        Author author = new Author();

        try {
            PreparedStatement statement = connection.prepareStatement(SQL_FIND_BY_ID);

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            rs.next();
            author.setId(rs.getInt(1));
            author.setName(rs.getString(2));
            author.setDayOfBirthday(rs.getDate(3).toLocalDate());

            Date date = rs.getDate(4);
            if (date == null) {
                author.setDayOfDeath(null);
            } else {
                author.setDayOfDeath(date.toLocalDate());
            }

            author.setSex(Author.Sex.valueOf(rs.getString(5)));

        } catch (SQLException e) {
            log.error(e);
        }

        return author;
    }


    @Override
    public void insert(Author author) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT);

            statement.setInt(1, author.getId());
            statement.setString(2, author.getName());
            statement.setDate(3, valueOf(author.getDayOfBirthday()));

            if (author.getDayOfDeath() != null) {
                statement.setDate(4, valueOf(author.getDayOfDeath()));
            } else {
                statement.setDate(4, null);
            }

            statement.setString(5, author.getSex().toString());

            statement.execute();

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void update(Author author) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE);

            statement.setString(1, author.getName());
            statement.setDate(2, valueOf(author.getDayOfBirthday()));

            if (author.getDayOfDeath() != null) {
                statement.setDate(3, valueOf(author.getDayOfDeath()));
            } else {
                statement.setDate(3, null);
            }

            statement.setString(4, author.getSex().toString());
            statement.setInt(5, author.getId());

            statement.execute();

        } catch (SQLException e) {
            log.error(e);
        }
    }

    @Override
    public void delete(Author author) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE);

            statement.setInt(1, author.getId());

            statement.execute();

        } catch (SQLException e) {
            log.error(e);
        }

    }
}
