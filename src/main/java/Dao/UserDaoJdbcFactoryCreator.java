package Dao;

import util.DBHelper;

import java.sql.Connection;

public class UserDaoJdbcFactoryCreator implements IUserDaoFactoryCreator {

    public UserDaoJdbcFactoryCreator(Connection mysqlConnection) {
    }

    @Override
    public IUserDao createUserDao() {
        return new UserJdbcDao(DBHelper.getMysqlConnection());
    }
}
