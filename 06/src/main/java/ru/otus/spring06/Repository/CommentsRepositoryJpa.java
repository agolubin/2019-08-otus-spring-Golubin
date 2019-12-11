package ru.otus.spring06.Repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring06.domain.Comments;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

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
        em.remove(em.contains(p) ? p : em.merge(p));
    }

    @Override
    public Comments getByID(Long commentsID) {
        return em.find(Comments.class, commentsID);
    }


    @Override
    public List<Comments> findAll() {
        EntityGraph<?> entityGraph = em.getEntityGraph("CommetsWithBook");
        TypedQuery<Comments> query = em.createQuery("select b from Comments b", Comments.class);
        query.setHint("javax.persistence.fetchgraph", entityGraph);
        return query.getResultList();
    }

}
