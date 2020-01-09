package service.impl;

import dao.IUserDao;
import factory.UserDaoFactory;
import model.User;
import service.IUserService;

import java.util.List;
//import static util.DBHelper.createSessionFactory;

public class UserService implements IUserService {

    IUserDao userDao = UserDaoFactory.getDaoByType();

    private UserService(){
    }

    private static UserService instance;
    public static synchronized UserService getInstance(){
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }

//    private static class UserServiceHolder{
//        private final static UserService instance = new UserService();
//    }
//    public static UserService getInstance() {
//        return UserServiceHolder.instance;
//    }
//
    @Override
    public List<User> getAllUser() throws Exception {
        return userDao.getAllUsers();
    }

    @Override
    public void AddUser(User user) throws Exception {
        userDao.createUser(user);
    }

    @Override
    public void updateUser(User user) throws Exception {
        userDao.updateUser(user);
    }

    @Override
    public void removeUser(User user) throws Exception{
        userDao.deleteUser(user);
    }

    @Override
    public User findUserById(Long id) throws Exception {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByLogin(String login) throws Exception {
        return userDao.findUserByLogin(login);
    }

}
