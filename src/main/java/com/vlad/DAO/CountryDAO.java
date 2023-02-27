package com.vlad.DAO;
import com.vlad.DAO.interfacies.CountryDAOint;
import com.vlad.Entity.CountryEntity;
import com.vlad.Entity.JobEntity;
import com.vlad.Entity.PersonEntity;
import com.vlad.SessionFactoryCreater.SessionFactoryGet;
import org.hibernate.*;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;

@Repository
public class CountryDAO implements CountryDAOint {
    SessionFactory sessionFactory = SessionFactoryGet.getSessionFactory();
    Transaction transaction = null;
    CountryEntity countryEntity;
    Session session;

    @Override
    public List<CountryEntity> allCountries()
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List<CountryEntity> countryEntities =session.createQuery
                    ("FROM CountryEntity order by id", CountryEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return countryEntities;
    }

    @Override
    public CountryEntity takeCountry(long id)
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        countryEntity = session.byId(CountryEntity.class).load(id);
        session.getTransaction().commit();
        session.close();
        return countryEntity;
    }
    @Override
    public Set<JobEntity> getAllJobInThisCountry(long id)
    {
        session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Set<JobEntity> jobEntities =
                session.byId(CountryEntity.class).load(id).getJobEntities();
        transaction.commit();
        session.close();
        return jobEntities;
    }
    @Override
    public List<PersonEntity> getAllPeopleInCountry(long id)
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List<PersonEntity>personEntities =
                session.byId(CountryEntity.class).load(id).getPersonEntities();
        transaction.commit();
        session.close();
        return personEntities;
    }

    @Override
    public void save(CountryEntity countryEntity) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("INSERT INTO countries(name,population)VALUES(?,?)");
        insertQuery.setParameter(1, countryEntity.getName());
        insertQuery.setParameter(2, countryEntity.getPopulation());
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }



    @Override
    public void update(long id, CountryEntity countryEntity) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("UPDATE countries SET name = ?,population = ? where id = ?");
        insertQuery.setParameter(1, countryEntity.getName());
        insertQuery.setParameter(2, countryEntity.getPopulation());
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
                ("DELETE from countries where id = ?");
        insertQuery.setParameter(1, id);
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
