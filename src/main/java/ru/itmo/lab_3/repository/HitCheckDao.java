package ru.itmo.lab_3.repository;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.lab_3.entities.TableRow;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

@ManagedBean(eager = true)
@ApplicationScoped
@Getter
@Setter
public class HitCheckDao {
    private static final String TABLE_NAME = "TableRow";
    private static final String DELETE_ALL_BY_SESSION_ID =
            "DELETE FROM " + TABLE_NAME + " tr WHERE tr.sessionId = :sessionId";
    private static final String GET_ALL_BY_SESSION_ID =
            "SELECT tr FROM " + TABLE_NAME + " tr WHERE tr.sessionId = :sessionId";
    private final EntityManager entityManager;
    private final EntityTransaction entityTransaction;

    public HitCheckDao() {
        entityManager =
                Persistence.createEntityManagerFactory("tableRow").createEntityManager();
        entityTransaction = entityManager.getTransaction();
    }

    public void save(TableRow tableRow) {
        if(!entityTransaction.isActive())
            entityTransaction.begin();
        entityManager.persist(tableRow);
        entityTransaction.commit();
    }

    public void clearBySessionId(String sessionId) {
        if(!entityTransaction.isActive())
            entityTransaction.begin();
        entityManager.createQuery(DELETE_ALL_BY_SESSION_ID)
                .setParameter("sessionId", sessionId)
                .executeUpdate();
        entityTransaction.commit();
    }

    public List<TableRow> getAllBySessionId(String sessionId) {
        if(!entityTransaction.isActive())
            entityTransaction.begin();
        List<TableRow> resultList = entityManager
                .createQuery(GET_ALL_BY_SESSION_ID)
                .setParameter("sessionId", sessionId)
                .getResultList();
        entityTransaction.commit();
        return resultList;
    }
}
