package dao.impl;

import dao.IUserDao;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import util.DBHelper;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

public class UserHibernateDao implements IUserDao {

    private SessionFactory sessionFactory;
    private Session session;
    private Transaction transaction;

    public UserHibernateDao() {
        DBHelper dbHelper = DBHelper.getInstance();
       sessionFactory = dbHelper.createSessionFactory();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();

       try {
        session = sessionFactory.openSession();

            transaction = session.beginTransaction();
           allUsers = (List<User>) session.createQuery("from User").list();
           return allUsers;

       } catch (HibernateException e){
           e.printStackTrace();
           try {
               transaction.rollback();
           } catch (HibernateException e1){
               e1.printStackTrace();
           }
       } finally {
           session.close();
       }
        return allUsers;

    }

    @Override
    public void createUser(User user) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (HibernateException e1){
                e1.printStackTrace();
            }
        } finally {
            session.close();
        }
    }

    @Override
    public void updateUser(User user) {
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(user);
            transaction.commit();
        } catch (HibernateException e){
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (HibernateException e1){
                e1.printStackTrace();
            }
        } finally{
            session.close();
        }
    }

    @Override
    public void deleteUser(User user)  {
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
        }catch (HibernateException e){
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (HibernateException e1){
                e1.printStackTrace();
            }
        } finally{
            session.close();
        }
    }

    @Override
    public User findUserById(long id)  {
        User user = new User();
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User WHERE id = '" + id + "'").uniqueResult();
            transaction.commit();
            return user;
        }catch (HibernateException e){
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (HibernateException e1){
                e1.printStackTrace();
            }
        } finally{
            session.close();
        }

        return user;
    }

    @Override
    public User findUserByLogin(String login){
        User user = new User();
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            user = (User) session.createQuery("FROM User WHERE login = '" + login + "' ").uniqueResult();
            transaction.commit();
            return user;
        }catch (HibernateException e){
            e.printStackTrace();
            try {
                transaction.rollback();
            } catch (HibernateException e1){
                e1.printStackTrace();
            }
        } finally{
            session.close();
        }
        return user;
    }

}
