package ru.otus.spring05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring05.domain.Book;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public Book insert(Book book) throws SQLException{
        int count = countByName(book);

        if (count == 0){
            MapSqlParameterSource params = new MapSqlParameterSource();
            params.addValue("name", book.getName());
            params.addValue("genreID", book.getGenreID());
            params.addValue("authorID", book.getAuthorID());
            GeneratedKeyHolder key = new GeneratedKeyHolder();

            namedParameterJdbcOperations.update(
                    "insert into book (name, genreID, authorID) values (:name, :genreID, :authorID)", params, key
            );
            book.setBookID((int) key.getKey());
            return book;
        }
        else
        {
            return getBookByBook(book);
        }

    }

    @Override
    public void update(Book book){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("genreID", book.getGenreID());
        params.addValue("authorID", book.getAuthorID());

        int res = namedParameterJdbcOperations.update(
                "update BOOK set name = :name, genreID = :genreID, authorID = :authorID where bookID = :bookID", params
        );
    }

    @Override
    public void deleteByID(int bookID) {
        int res = checkByID(bookID);
        if (res == 1) {
            Map<String, Object> params = Collections.singletonMap("bookID", bookID);
            namedParameterJdbcOperations.update(
                    "delete from book where bookID = :bookID", params
            );
        }
    }

    @Override
    public List findAll() {
            return namedParameterJdbcOperations
                    .getJdbcOperations()
                    .query("select book.BookID, book.Name, " +
                            "author.Name, author.SurName, genre.Name\n" +
                            "from book \n" +
                            "join author\n" +
                            "on author.AuthorID = book.AuthorID\n" +
                            "join genre\n" +
                            "on genre.genreID = book.genreID", new BookMapper2());
    }
/*
    @Override
    public Book getByID(int bookID) {
        Map<String, Object> params = Collections.singletonMap("bookID", bookID);
        return namedParameterJdbcOperations.queryForObject(
                "select * from book where bookID = :bookID", params, new BookMapper()
        );
    }

*/
    public int checkByID(int bookID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bookID", bookID);
        int res = namedParameterJdbcOperations.queryForObject(
                "select count(*) from book where bookID = :bookID", params, Integer.class
        );
        return res;
    }

    @Override
    public Book getBookByBook(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("genreID", book.getGenreID());
        params.addValue("authorID", book.getAuthorID());

        return namedParameterJdbcOperations.queryForObject(
                "select * from book where name = :name and genreID = :genreID and authorID = :authorID", params, new BookMapper()
        );
    }

    @Override
    public int countByName(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("genreID", book.getGenreID());
        params.addValue("authorID", book.getAuthorID());
        int res =  namedParameterJdbcOperations.queryForObject(
                "select count(*) from book where name = :name and genreID = :genreID and authorID = :authorID", params, Integer.class
        );

        return res;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int bookID   = resultSet.getInt("bookID");
            String name    = resultSet.getString("name");
            int genreID = resultSet.getInt("genreID");
            int authorID = resultSet.getInt("authorID");
            return new Book(bookID, authorID, genreID, name );
        }
    }

    private static class BookMapper2 implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            int bookID   = resultSet.getInt("book.bookID");
            String name    = resultSet.getString("book.name");
            String genreName = resultSet.getString("genre.Name");
            String authorName = resultSet.getString("author.Name");
            String authorSurName = resultSet.getString("author.SurName");
            return new Book(bookID, authorName, authorSurName, genreName, name );
        }
    }


}
