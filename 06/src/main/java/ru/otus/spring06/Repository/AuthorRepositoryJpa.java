package ru.otus.spring06.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06.domain.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class AuthorRepositoryJpa implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Author p) {
        em.persist(p);
    }

    @Override
    public void update(Author p) {
        em.merge(p);
    }

    @Override
    public void delete(Author p) {
        em.remove(em.contains(p) ? p : em.merge(p));
    }

    @Override
    public Author getByID(Long authorID) {
        return em.find(Author.class, authorID);
    }

    @Override
    public List<Author> findAll() {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a",
                Author.class);
        return query.getResultList();
    }

    @Override
    public Author getAuthorByName(String name, String surName) {
        TypedQuery<Author> query = em.createQuery(
                "select a from Author a where a.name = :name and a.surName = :surName",
                Author.class);
        query.setParameter("name", name);
        query.setParameter("surName", surName);
        return query.getSingleResult();
    }

}
