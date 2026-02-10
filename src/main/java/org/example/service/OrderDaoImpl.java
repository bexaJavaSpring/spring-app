package org.example.service;

import org.example.dao.OrderDao;
import org.example.models.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void save(Order order){
        getSession().save(order);
    }

    @Transactional
    public Order getById(Long id){
        return getSession().get(Order.class,id);
    }

    @Transactional
    public List<Order> getAll(){
        return getSession().createQuery("from orders",Order.class).list();
    }

    @Transactional
    public void update(Order order){
        getSession().update(order);
    }

    @Transactional
    public void delete(Long id){
        Order order = getById(id);
        getSession().delete(order);
    }
}
