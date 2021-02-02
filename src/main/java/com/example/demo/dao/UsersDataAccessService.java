package com.example.demo.dao;

import com.example.demo.models.User;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UsersDataAccessService {

    @PersistenceContext
    private EntityManager em;

    static final String STATEMENT_SQLMAP1 = "Statement-SQL-Mapping1";

    public List<User> getUserById(@Param("id") int id) {
        Query querysummary = em.createNativeQuery(
                "SELECT u.Id, u.Name, u.Level FROM users u WHERE u.Id = :id" ,
                STATEMENT_SQLMAP1);
        querysummary.setParameter("id", id);
        return querysummary.getResultList();
    }

}
