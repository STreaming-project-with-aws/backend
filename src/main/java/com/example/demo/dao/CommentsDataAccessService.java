package com.example.demo.dao;

import com.example.demo.models.Comment;
import com.example.demo.models.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
public class CommentsDataAccessService {

    @PersistenceContext
    private EntityManager em;

    static final String STATEMENT_SQLMAP1 = "Statement-SQL-Mapping2";


    @Transactional
    public void setComment(Comment singlecomment) {
        em.createNativeQuery("INSERT INTO comments(UsersName, Content, Date, VideoId) VALUES(?1,?2,?3,?4/*'ceejay', 'v-1'*/)")
                .setParameter(1, singlecomment.getUsersName())
                .setParameter(2, singlecomment.getContent())
                .setParameter(3, singlecomment.getDate())
                .setParameter(4, singlecomment.getVideoId())
                .executeUpdate();
    }


    public List<Comment> getCommentsByVideoIdUp(@Param("videoid") String videoid) {
        Query querysummary = em.createNativeQuery(
                "SELECT u.Id, u.UsersName, u.Content, u.Date, u.VideoId FROM comments u WHERE u.VideoId LIKE :videoid" ,
                STATEMENT_SQLMAP1);
        querysummary.setParameter("videoid", videoid);
        return querysummary.getResultList();
    }
}
