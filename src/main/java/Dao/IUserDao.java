package Dao;

import model.User;

import java.util.List;

public interface IUserDao {
    
    List<User> getAllUsers() throws Exception;
    void createUser(User user) throws Exception;
    void updateUser(User user) throws Exception;
    void deleteUser(User user) throws Exception;
    User findUserById(long id) throws Exception;
    User findUserByLogin(String login) throws Exception;

}
