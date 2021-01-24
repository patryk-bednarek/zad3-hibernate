package pl.edu.wszib.car.rent.dao.impl;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pl.edu.wszib.car.rent.dao.IVehicleDAO;
import pl.edu.wszib.car.rent.model.Vehicle;

import javax.persistence.NoResultException;
import java.util.List;


@Repository
public class HibernateVehicleDAOImpl implements IVehicleDAO {


    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Vehicle getVehicleById(int id) {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM pl.edu.wszib.car.rent.model.Vehicle WHERE id = :id");
        query.setParameter("id", id);
        Vehicle vehicle = null;
        try {
            vehicle = query.getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Nie znaleziono takiego pojazdu!!!");
        }
        session.close();
        return vehicle;
    }

    @Override
    public void updateVehicle(Vehicle vehicle) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(vehicle);
            tx.commit();
        } catch(Exception e) {
            if (tx != null)
                tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        Session session = this.sessionFactory.openSession();
        Query<Vehicle> query = session.createQuery("FROM pl.edu.wszib.car.rent.model.Vehicle");
        List<Vehicle> vehicle = query.getResultList();
        session.close();
        return vehicle;
    }

    @Override
    public void updateRent(Vehicle vehicle) {

    }
}
