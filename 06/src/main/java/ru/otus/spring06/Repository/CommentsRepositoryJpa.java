package ru.otus.spring06.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06.domain.Comments;
import ru.otus.spring06.domain.Genre;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("JpaQlInspection")
@Repository
@Transactional
public class CommentsRepositoryJpa implements CommentsRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void insert(Comments p) {
        em.merge(p);
    }

    @Override
    public void update(Comments p) {
        em.merge(p);
    }

    @Override
    public void delete(Comments p) {
        TypedQuery<Comments> query = em.createQuery(
                "select a from Comments a where a.iD = :id",
                Comments.class);
        query.setParameter("id", p.getID());
        Comments commentsdb =  query.getSingleResult();
        em.remove(commentsdb);
    }

    @Override
    public Optional<Comments> getByID(Long commentsID) {
        return Optional.of(em.find(Comments.class, commentsID));
    }


    @Override
    public List<Comments> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("CommetsWithBook");
        TypedQuery<Comments> query = em.createQuery("select b from Comments b", Comments.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

}
