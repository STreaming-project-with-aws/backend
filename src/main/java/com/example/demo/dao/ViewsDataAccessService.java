package com.example.demo.dao;

import com.example.demo.models.Views;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

@Repository
public class ViewsDataAccessService {

    @PersistenceContext
    private EntityManager em;

//    static final String STATEMENT_SQLMAP1 = "Statement-SQL-Mapping2";

    /*public void setView(@Param("user") String user, @Param("videoid") String videoid ) {
        Query querysummary = em.createNativeQuery(
                "INSERT INTO views(User, VideoId) VALUES(:user , :videoid)" ,
                STATEMENT_SQLMAP1);
        querysummary.setParameter("user", user);
        querysummary.setParameter("videoid", videoid);
//        return querysummary.getResultList();
//        return null;
    }*/

    @Transactional
    public void setView(Views views) {
        em.createNativeQuery("INSERT INTO views(UsersName, VideoId) VALUES(?1,?2/*'ceejay', 'v-1'*/)")
                .setParameter(1, views.getUsersName())
                .setParameter(2, views.getVideoId())
                .executeUpdate();
    }

    @Transactional
    public Object getCount() {
        String queryString = "SELECT Count(*) FROM views";
        Query query = em.createNativeQuery(queryString);
        return query.getSingleResult();
    }
}
