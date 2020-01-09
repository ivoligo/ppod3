package dao.impl;

import dao.IUserDao;
import model.User;
import util.DBHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDao implements IUserDao {
    private Connection connection;
    public UserJdbcDao() {
        connection = DBHelper.getInstance().getMysqlConnection();
    }


    @Override
    public List<User> getAllUsers() throws Exception {
        List<User> userList = new ArrayList<>();
        Statement stmt = connection.createStatement();
        stmt.execute("select * from users3");
        ResultSet rs = stmt.getResultSet();
        while(rs.next()){
            Long id = rs.getLong(1);
            User user = new User(id, findUserById(id).getLogin(), findUserById(id).getAge(), findUserById(id).getCity(), findUserById(id).getPassword());
            userList.add(user);
        }
        rs.close();
        stmt.close();
        return userList;
    }

    @Override
    public void createUser(User user) throws Exception {
        String login = user.getLogin();
        int age = user.getAge();
        String city = user.getCity();
        String password = user.getPassword();
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("insert into users3(login, age, city,password) VALUES ('" +login + "', '" +age + "', '" +city + "','" +password + "')");
        stmt.close();
    }

    @Override
    public void updateUser(User user) throws Exception {
        PreparedStatement stmt = connection.prepareStatement("update users3 SET login=? , age=? , city=?, password=? where id= ?");
        stmt.setString(1, user.getLogin());
        stmt.setInt(2, user.getAge());
        stmt.setString(3,user.getCity());
        stmt.setString(4, user.getPassword());
        stmt.setLong(5, user.getId());
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void deleteUser(User user) throws Exception {
        Long id = user.getId();
        PreparedStatement stmt = connection.prepareStatement("delete from users3 where id=?");
        stmt.setLong(1,id);
        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public User findUserById(long id) throws Exception {
        PreparedStatement stmt = connection.prepareStatement("select * from users3 where id=?");
        stmt.setLong(1,id);
        ResultSet rs = stmt.executeQuery();
        String login = null;
        int age = 0;
        String city = null;
        String password = null;
        while (rs.next()){
            login = rs.getString(2);
            age = rs.getInt(3);
            city = rs.getString(4);
            password = rs.getString(5);
        }
        User userById = new User(id, login, age, city, password);
        rs.close();
        stmt.close();
        return userById;
    }

    @Override
    public User findUserByLogin(String login) throws Exception {
        return findUserById(getUserIdByLogin(login));
    }

    public long getUserIdByLogin(String login) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement("select * from users3 where login=?");
        stmt.setString(1,login);
        ResultSet rs = stmt.executeQuery();
        long id = 0;
        if (rs.first()) {
            id = rs.getLong(1);
        }
        rs.close();
        stmt.close();
        return id;
    }
}
