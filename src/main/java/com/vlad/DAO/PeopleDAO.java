package com.vlad.DAO;

import com.vlad.DAO.interfacies.PeopleDAOint;
import com.vlad.Entity.PersonEntity;
import com.vlad.SessionFactoryCreater.SessionFactoryGet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PeopleDAO implements PeopleDAOint {
    SessionFactory sessionFactory = SessionFactoryGet.getSessionFactory();
    Transaction transaction = null;
    PersonEntity personEntity;
    Session session;

    @Override
    public List<PersonEntity> allPeople()
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();

        List<PersonEntity> personEntities = session.createQuery
                ("FROM PersonEntity order by id", PersonEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return personEntities;
    }
    @Override
    public PersonEntity takePerson(long id)
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        personEntity = session.byId(PersonEntity.class).load(id);
        session.getTransaction().commit();
        session.close();
        return personEntity;
    }

    @Override
    public void save(PersonEntity personEntity) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("INSERT INTO people(name,country_id,job_id)VALUES(?,?,?)");
        insertQuery.setParameter(1, personEntity.getName());
        insertQuery.setParameter(2, personEntity.getCountryEntity().getId());
        insertQuery.setParameter(3, personEntity.getJobEntity().getId());
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, PersonEntity personEntity) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("UPDATE people SET name = ?,country_id = ?,job_id = ? where id = ?");
        insertQuery.setParameter(1, personEntity.getName());
        insertQuery.setParameter(2, personEntity.getCountryEntity().getId());
        insertQuery.setParameter(3, personEntity.getJobEntity().getId());
        insertQuery.setParameter(4, id);
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void delete(long id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("DELETE from people where id = ?");
        insertQuery.setParameter(1, id);
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
