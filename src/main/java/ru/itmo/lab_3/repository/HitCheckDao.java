package ru.itmo.lab_3.repository;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.itmo.lab_3.entities.TableRow;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Collections;
import java.util.List;

@ManagedBean(eager = true)
@ApplicationScoped
@Getter
@Setter
public class HitCheckDao {
    private SessionFactory factory;

    public HitCheckDao() {
        try {
            Configuration configuration = new Configuration().configure("hibernate.cfg.xml");
            configuration.addAnnotatedClass(TableRow.class);
            factory = configuration.buildSessionFactory();
        } catch (Exception e) {
            System.out.println("Исключение!" + e);
        }
    }

    public void save(TableRow tableRow) {
        Session session = factory.openSession();

        session.beginTransaction();
        session.save(tableRow);
        session.getTransaction().commit();
        session.close();
    }

    public void clearBySessionId(String sessionId) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createQuery("delete from TableRow where sessionId= :sessionId")
                .setParameter("sessionId", sessionId).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public List<TableRow> getAllBySessionId(String sessionId) {
        if( factory != null ) {
            Session session = factory.openSession();
            session.beginTransaction();
            List<TableRow> resultList = session
                    .createQuery("SELECT t FROM TableRow t WHERE t.sessionId = :sessionId")
                    .setParameter("sessionId", sessionId)
                    .getResultList();
            session.getTransaction().commit();
            session.close();
            return resultList;
        } else {
            return Collections.emptyList();
        }
    }
}
