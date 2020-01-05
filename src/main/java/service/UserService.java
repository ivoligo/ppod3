package service;

import Dao.IUserDao;
import Dao.IUserDaoFactoryCreator;
import Dao.UserDaoFactory;
import Dao.UserHibernateDao;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;
import util.GetSessionFactory;

import java.sql.SQLException;
import java.util.List;

import static Dao.UserDaoFactory.getCreatorByType;
//import static util.DBHelper.createSessionFactory;

public class UserService implements IUserService{

//    IUserDaoFactoryCreator creator = getCreatorByType();
//    IUserDao userDao =  creator.createUserDao();
    IUserDao userDao = UserDaoFactory.getCreatorByType().createUserDao();

    private UserService(){
    }
    private static UserService userService;

    private SessionFactory sessionFactory;

    private UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public static UserService getInstance(){
        if (userService == null){
            userService = new UserService(GetSessionFactory.getSessionFactory());
        }
        return userService;
    }

    private  IUserDao getUserHibernateDao() {
        return new UserHibernateDao(sessionFactory.openSession());
    }

    @Override
    public List<User> getAllUser() throws Exception {
        return getUserHibernateDao().getAllUsers();
    }

    @Override
    public void AddUser(User user) throws Exception {
        getUserHibernateDao().createUser(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        getUserHibernateDao().updateUser(user);
    }

    @Override
    public void removeUser(User user) throws Exception{
        getUserHibernateDao().deleteUser(user);
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return getUserHibernateDao().findUserById(id);
    }

    @Override
    public User findUserByLogin(String login) throws Exception {
        return getUserHibernateDao().findUserByLogin(login);
    }

}
