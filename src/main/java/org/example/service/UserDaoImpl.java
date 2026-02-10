package org.example.service;

import org.example.dao.UserDao;
import org.example.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    @Transactional
    public void save(User user){
        getSession().save(user);
    }

    @Transactional
    public User getById(Long id){
        return getSession().get(User.class,id);
    }

    @Transactional
    public List<User> getAll(){
        return getSession().createQuery("from users ",User.class).list();
    }

    @Transactional
    public void update(User user){
        getSession().update(user);
    }

    @Transactional
    public void delete(Long id){
        User user = getById(id);
        getSession().delete(user);
    }
}
