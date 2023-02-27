package com.vlad.DAO;

import com.vlad.DAO.interfacies.JobDAOint;
import com.vlad.Entity.CountryEntity;
import com.vlad.Entity.JobEntity;
import com.vlad.Entity.PersonEntity;
import com.vlad.SessionFactoryCreater.SessionFactoryGet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public class JobDAO implements JobDAOint {
    SessionFactory sessionFactory = SessionFactoryGet.getSessionFactory();
    Transaction transaction = null;
    JobEntity jobEntity = null;
    Session session = null;

    @Override
    public List<JobEntity> allJobs()
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List<JobEntity> jobEntities =session.createQuery
                    ("FROM JobEntity order by id", JobEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return jobEntities;
    }
    @Override
    public JobEntity takeJob(long id)
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        jobEntity = session.byId(JobEntity.class).load(id);
        session.getTransaction().commit();
        session.close();
        return jobEntity;
    }

    @Override
    public List<PersonEntity> getAllPeopleInJob(long id)
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List<PersonEntity>personEntities =
                session.byId(JobEntity.class).load(id).getPersonEntities();
        transaction.commit();
        session.close();
        return personEntities;
    }

    @Override
    public Set<CountryEntity> getAllCountiesWithThisJob(long id)
    {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Set<CountryEntity> countryEntities =
                session.byId(JobEntity.class).load(id).getCountryEntities();
        transaction.commit();
        session.close();
        return countryEntities;
    }

    @Override
    public void save(JobEntity jobEntity) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("INSERT INTO jobs(name,salary)VALUES(?,?)");
        insertQuery.setParameter(1, jobEntity.getName());
        insertQuery.setParameter(2, jobEntity.getSalary());
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, JobEntity jobEntity) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("UPDATE jobs SET name = ?,salary = ? where id = ?");
        insertQuery.setParameter(1, jobEntity.getName());
        insertQuery.setParameter(2, jobEntity.getSalary());
        insertQuery.setParameter(3, id);
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
    @Override
    public void delete(long id) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("DELETE from jobs where id = ?");
        insertQuery.setParameter(1, id);
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
