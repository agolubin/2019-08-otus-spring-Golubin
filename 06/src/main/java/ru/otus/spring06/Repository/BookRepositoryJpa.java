package ru.otus.spring06.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06.domain.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
        em.remove(em.contains(p) ? p : em.merge(p));
    }

    @Override
    public Book getByID(Long bookID) {
        EntityGraph<?> entityGraph = em.getEntityGraph("BookWithAuthorAndGenre");
        TypedQuery<Book> query = em.createQuery(
                "select b from Book b where b.iD = :id",
                Book.class);
        query.setParameter("id", bookID);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getSingleResult();
    }


    @Override
    public List<Book> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("BookWithAuthorAndGenre");
        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();

    }
}
