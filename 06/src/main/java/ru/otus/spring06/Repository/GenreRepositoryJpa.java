package ru.otus.spring06.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06.domain.Author;
import ru.otus.spring06.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class GenreRepositoryJpa implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Genre p) {
        em.persist(p);
    }

    @Override
    public Optional<Genre> getByID(Long GenreID) {
        return Optional.of(em.find(Genre.class, GenreID));
    }

    @Override
    public void update(Genre p) {
        em.merge(p);
    }

    @Override
    public void delete(Genre p) {
        TypedQuery<Genre> query = em.createQuery(
                "select a from Genre a where a.iD = :id",
                Genre.class);
        query.setParameter("id", p.getID());
        Genre genredb =  query.getSingleResult();
        em.remove(genredb);
    }

    @Override
    public List<Genre> findAll() {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g",
                Genre.class);
        return query.getResultList();
    }


    @Override
    public Genre getGenreByName(String name) {
        TypedQuery<Genre> query = em.createQuery(
                "select g from Genre g where g.name = :name",
                Genre.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

}
