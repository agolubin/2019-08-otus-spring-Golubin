package ru.otus.spring05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring05.Exceptions.AuthorExistException;
import ru.otus.spring05.domain.Author;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Author insert(Author author) throws AuthorExistException {
        int count = countByName(author.getName(), author.getSurName());

        if (count == 0){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("surName", author.getSurName());
            params.addValue("name", author.getName());
            GeneratedKeyHolder key = new GeneratedKeyHolder();

            namedParameterJdbcOperations.update(
                    "insert into author (`surName`, `name`) values (:surName, :name)", params, key
            );
            author.setAuthorID((Long) key.getKey());
            return author;
        }
        else
        {
            throw new AuthorExistException("Автор " + author.getName() + " " + author.getSurName() + " уже добавлен в базу!");
        }

    }

    @Override
    public void update(Author author){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("authorID", author.getAuthorID());
        params.addValue("surName", author.getSurName());
        params.addValue("name", author.getName());

        int res = namedParameterJdbcOperations.update(
                "update AUTHOR set surName = :surName, name = :name where authorID = :authorID", params
        );
    }

    @Override
    public void deleteByID(Long authorID) {
        int res = countByID(authorID);
        if (res == 1) {
            Map<String, Object> params = Collections.singletonMap("authorID", authorID);
            namedParameterJdbcOperations.update(
                    "delete from author where authorID = :authorID", params
            );
        }
    }

    @Override
    public List <Author> findAll() {
            return namedParameterJdbcOperations.getJdbcOperations().query("select * from AUTHOR", new AuthorMapper());
    }

    @Override
    public int countByName(String name, String surName) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("surName", surName);
        params.put("name", name);
        int res = namedParameterJdbcOperations.queryForObject(
                "select count(*) from author where name = :name and surname = :surName", params, Integer.class
        );
        return res;
    }

    public int countByID(Long authorID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("authorID", authorID);
        int res = namedParameterJdbcOperations.queryForObject(
                "select count(*) from author where authorID = :authorID", params, Integer.class
        );
        return res;
    }

    @Override
    public Author getAuthorByName(String name, String SurName) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("surName", SurName);
            params.put("name", name);
            return namedParameterJdbcOperations.queryForObject(
                    "select * from author where name = :name and surname = :surName", params, new AuthorMapper()
            );
            }
        catch (Exception e) {
            return null;
        }
    }

    private static class AuthorMapper implements RowMapper<Author> {

        @Override
        public Author mapRow(ResultSet resultSet, int i) throws SQLException {
            Long authorID  = resultSet.getLong("authorID");
            String name    = resultSet.getString("name");
            String surName = resultSet.getString("surName");
            return new Author(authorID, name, surName);
        }
    }

}
