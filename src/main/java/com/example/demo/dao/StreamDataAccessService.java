package com.example.demo.dao;

import com.example.demo.models.Comment;
import com.example.demo.models.Stream;
import com.example.demo.models.Views;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class StreamDataAccessService {

    @PersistenceContext
    private EntityManager em;

    static final String STATEMENT_SQLMAP1 = "Statement-SQL-Mapping3";

    @Transactional
    public void setStream(Stream stream) {
        em.createNativeQuery("INSERT INTO streams(UsersName, StreamName, Type, Game, Date, PhotoUrl, StreamKey) VALUES(?1,?2,?3,?4,?5,?6,?7)")
                .setParameter(1, stream.getUsersName() )
                .setParameter(2, stream.getStreamName() )
                .setParameter(3, stream.getType())
                .setParameter(4, stream.getGame())
                .setParameter(5, stream.getDate())
                .setParameter(6, stream.getPhotoUrl())
                .setParameter(7, stream.getKey())
                .executeUpdate();
    }

    public List<Stream> getStreams() {
        Query querysummary = em.createNativeQuery(
                "SELECT u.Id, u.UsersName, u.StreamName, u.Date, u.Type, u.Game, u.Date, u.PhotoUrl, u.StreamKey  FROM streams u" ,
                STATEMENT_SQLMAP1);
        return querysummary.getResultList();
    }
}

