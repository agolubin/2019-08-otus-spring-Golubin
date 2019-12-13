package ru.otus.spring06.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06.domain.Book;
import ru.otus.spring06.domain.Comments;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class BookRepositoryJpa implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Book p) {
        em.merge(p);
    }

    @Override
    public void update(Book p) {
        em.merge(p);
    }

    @Override
    public void delete(Book p) {
        TypedQuery<Book> query = em.createQuery(
                "select a from Book a where a.iD = :id",
                Book.class);
        query.setParameter("id", p.getID());
        Book bookdb =  query.getSingleResult();
        em.remove(bookdb);
    }

    @Override
    public Optional<Book> getByID(Long bookID) {
        EntityGraph<?> entityGraph = em.getEntityGraph("BookWithAuthorAndGenre");
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.iD = :id",
                Book.class);
        query.setParameter("id", bookID);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return Optional.of(query.getSingleResult());
    }


    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("BookWithAuthorAndGenre");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();

    }
}
