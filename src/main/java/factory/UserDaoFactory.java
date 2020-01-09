package factory;

import dao.IUserDao;
import dao.impl.UserHibernateDao;
import dao.impl.UserJdbcDao;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {

    public static IUserDao getDaoByType() {
        FileInputStream fis;
        String type = null;

        Properties properties = new Properties();

        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("property");
            properties.load(is);
            type = properties.getProperty("typeOfDao");
            if (type.equalsIgnoreCase("UserJdbcDao")){

               return new UserJdbcDao();

            } else if (type.equalsIgnoreCase("UserHibernateDao")){

                return new UserHibernateDao();
            }
        } catch (IOException e) {
            System.out.println(" file is ququ");
        }

        throw  new RuntimeException("don't worry " + type);

    }
}