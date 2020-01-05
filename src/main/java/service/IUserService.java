package service;

import model.User;
import java.util.List;

public interface IUserService {

    List<User> getAllUser() throws Exception;
    void AddUser(User user) throws Exception;
    void updateUser(User user) throws Exception;
    void removeUser(User user) throws Exception;
    User findUserById(Long id) throws Exception;
    User findUserByLogin(String login) throws Exception;
}
