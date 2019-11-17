package ru.otus.spring05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring05.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Genre insert(Genre genre) throws SQLException{
        int count = countByName(genre);

        if (count == 0){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", genre.getName());
            GeneratedKeyHolder key = new GeneratedKeyHolder();


            namedParameterJdbcOperations.update(
                    "insert into genre (`name`) values (:name)", params, key
            );
            genre.setGenreID((int) key.getKey());
            return genre;
        }
        else
        {
            return getGenreByGenre(genre);
        }

    }

    @Override
    public void update(Genre genre){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("genreID", genre.getGenreID());
        params.addValue("name", genre.getName());

        int res = namedParameterJdbcOperations.update(
                "update GENRE set name = :name where genreID = :genreID", params
        );
    }

    @Override
    public void deleteByID(int genreID) {
        int res = checkByID(genreID);
        if (res == 1) {
            Map<String, Object> params = Collections.singletonMap("genreID", genreID);
            namedParameterJdbcOperations.update(
                    "delete from genre where genreID = :genreID", params
            );
        }
    }

    @Override
    public List findAll() {
            return namedParameterJdbcOperations.getJdbcOperations().query("select * from GENRE", new GenreMapper());
    }

    @Override
    public Genre getByID(int genreID) {
        Map<String, Object> params = Collections.singletonMap("genreID", genreID);
        return namedParameterJdbcOperations.queryForObject(
                "select * from genre where genreID = :genreID", params, new GenreMapper()
        );
    }

    @Override
    public int countByName(Genre genre) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name", genre.getName());
        int res = namedParameterJdbcOperations.queryForObject(
                "select count(*) from genre where name = :name", params, Integer.class
        );
        return res;
    }

    public int checkByID(int genreID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("genreID", genreID);
        int res = namedParameterJdbcOperations.queryForObject(
                "select count(*) from genre where genreID = :genreID", params, Integer.class
        );
        return res;
    }

    @Override
    public Genre getGenreByGenre(Genre genre) {
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("name", genre.getName());
            return namedParameterJdbcOperations.queryForObject(
                    "select * from genre where name = :name", params, new GenreMapper()
            );
        }
        catch (Exception e) {
            return null;
        }
    }

    private static class GenreMapper implements RowMapper<Genre> {

        @Override
        public Genre mapRow(ResultSet resultSet, int i) throws SQLException {
            int genreID   = resultSet.getInt("genreID");
            String name    = resultSet.getString("name");
            return new Genre(genreID, name);
        }
    }

}
