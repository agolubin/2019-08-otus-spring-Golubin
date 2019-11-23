package ru.otus.spring05.dao;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring05.Exceptions.AuthorExistException;
import ru.otus.spring05.Exceptions.BookExistException;
import ru.otus.spring05.domain.Author;
import ru.otus.spring05.domain.Book;
import ru.otus.spring05.domain.Genre;

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
    public Book insert(Book book) throws BookExistException{
            int res = countByParam(book.getAuthor().getAuthorID(), book.getGenre().getGenreID(), book.getName());
            if (res == 0) {
                MapSqlParameterSource params = new MapSqlParameterSource();
                params.addValue("name", book.getName());
                params.addValue("genreID", book.getGenre().getGenreID());
                params.addValue("authorID", book.getAuthor().getAuthorID());
                GeneratedKeyHolder key = new GeneratedKeyHolder();

                namedParameterJdbcOperations.update(
                        "insert into book (name, genreID, authorID) values (:name, :genreID, :authorID)", params, key
                );
                book.setBookID((Long) key.getKey());
                return book;
            } else {
                throw new BookExistException("Книга " + book.getName() + " уже добавлена в базу!");
            }


    }

    @Override
    public void update(Book book){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("genreID", book.getGenre().getGenreID());
        params.addValue("authorID", book.getAuthor().getAuthorID());

        int res = namedParameterJdbcOperations.update(
                "update BOOK set name = :name, genreID = :genreID, authorID = :authorID where bookID = :bookID", params
        );
    }

    @Override
    public void deleteByID(Long bookID) {
        int res = countByID(bookID);
        if (res == 1) {
            Map<String, Object> params = Collections.singletonMap("bookID", bookID);
            namedParameterJdbcOperations.update(
                    "delete from book where bookID = :bookID", params
            );
        }
    }

    @Override
    public List <Book> findAll() {
            return namedParameterJdbcOperations
                    .getJdbcOperations()
                    .query("select book.BookID, book.Name, " +
                            "author.Name, author.SurName, genre.Name\n" +
                            "from book \n" +
                            "inner join author\n" +
                            "on author.AuthorID = book.AuthorID\n" +
                            "inner join genre\n" +
                            "on genre.genreID = book.genreID", new BookWholeMapper());
    }

    @Override
    public Book getBookByID(Long bookID){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("bookID", bookID);

        return namedParameterJdbcOperations.queryForObject(
                "select book.BookID, book.Name, " +
                        "author.Name, author.SurName, genre.Name\n" +
                        "from book \n" +
                        "inner join author\n" +
                        "on author.AuthorID = book.AuthorID\n" +
                        "inner join genre\n" +
                        "on genre.genreID = book.genreID", params, new BookWholeMapper()
        );
    }
    public int countByID(Long bookID) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("bookID", bookID);
        int res = namedParameterJdbcOperations.queryForObject(
                "select count(*) from book where bookID = :bookID", params, Integer.class
        );
        return res;
    }

    @Override
    public Book getBookByParam(Long authorID, Long genreID, String name){
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("genreID", genreID);
        params.addValue("authorID", authorID);

        return namedParameterJdbcOperations.queryForObject(
                "select * from book where name = :name and genreID = :genreID and authorID = :authorID", params, new BookMapper()
        );
    }

    @Override
    public int countByParam(Long authorID, Long genreID, String name) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", name);
        params.addValue("genreID", genreID);
        params.addValue("authorID", authorID);
        int res =  namedParameterJdbcOperations.queryForObject(
                "select count(*) from book where name = :name and genreID = :genreID and authorID = :authorID", params, Integer.class
        );

        return res;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long bookID   = resultSet.getLong("bookID");
            String name    = resultSet.getString("name");
            Long genreID = resultSet.getLong("genreID");
            Long authorID = resultSet.getLong("authorID");
            return new Book(bookID, new Author(authorID, "", ""), new Genre(genreID, ""), name );
        }
    }

    private static class BookWholeMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            Long bookID      = resultSet.getLong("book.bookID");
            String name      = resultSet.getString("book.name");
            String genreName = resultSet.getString("genre.Name");
            String authorName = resultSet.getString("author.Name");
            String authorSurName = resultSet.getString("author.SurName");
            return new Book(bookID, new Author(0L, authorName, authorSurName), new Genre(0L, genreName), name );
        }
    }


}
