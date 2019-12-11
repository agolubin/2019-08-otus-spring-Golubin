package ru.otus.spring06.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06.domain.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
    public Genre getByID(Long GenreID) {
        return em.find(Genre.class, GenreID);
    }

    @Override
    public void update(Genre p) {
        em.merge(p);
    }

    @Override
    public void delete(Genre p) {
        em.remove(em.contains(p) ? p : em.merge(p));
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
