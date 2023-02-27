package com.vlad.DAO;
import com.vlad.DAO.interfacies.CarDAOint;
import com.vlad.Entity.CarEntity;
import com.vlad.SessionFactoryCreater.SessionFactoryGet;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
public class CarDAO implements CarDAOint {
    SessionFactory sessionFactory = SessionFactoryGet.getSessionFactory();
    Transaction transaction = null;
    CarEntity carEntity;
    Session session;
    String s;

    @Override
    public List<CarEntity> allCars()
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        List<CarEntity> carEntities =session.createQuery
                ("FROM CarEntity order by id", CarEntity.class).getResultList();
        session.getTransaction().commit();
        session.close();
        return carEntities;
    }
    @Override
    public CarEntity takeCar(long id)
    {
        session = sessionFactory.getCurrentSession();
        transaction = session.beginTransaction();
        carEntity = session.byId(CarEntity.class).load(id);
        session.getTransaction().commit();
        session.close();
        return carEntity;
    }

    @Override
    public void save(CarEntity carEntity) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("INSERT INTO cars(name,price)VALUES(?,?)");
        insertQuery.setParameter(1, carEntity.getName());
        insertQuery.setParameter(2, carEntity.getPrice());
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void update(long id, CarEntity carEntity) {
        session = sessionFactory.openSession();
        transaction = session.beginTransaction();
        Query insertQuery = session.createSQLQuery
                ("UPDATE cars SET name = ?,price = ? where id = ?");
        insertQuery.setParameter(1, carEntity.getName());
        insertQuery.setParameter(2, carEntity.getPrice());
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
                ("DELETE from cars where id = ?");
        insertQuery.setParameter(1, id);
        insertQuery.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}