package Dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserHibernateDao implements IUserDao{

    private Session session;

    public UserHibernateDao(Session session) {
        this.session = session;
    }

//    public UserHibernateDao() {
//
//    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> allUsers = (List<User>) session.createQuery("from User").list();
        transaction.commit();
        session.close();
        return allUsers;
    }

    @Override
    public void createUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
    }

    @Override
    public void updateUser(User user) {
        Transaction transaction = session.beginTransaction();
        session.update(user);
        transaction.commit();

    }

    @Override
    public void deleteUser(User user)  {
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
    }

    @Override
    public User findUserById(long id)  {
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE id = '" +id+ "'").uniqueResult();
        transaction.commit();
        return user;
    }

    @Override
    public User findUserByLogin(String login){
        Transaction transaction = session.beginTransaction();
        User user = (User) session.createQuery("FROM User WHERE login = '" +login+ "' ").uniqueResult();
        transaction.commit();
        return user;
    }

}
