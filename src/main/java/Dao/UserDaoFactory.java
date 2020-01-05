package Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.UserService;
import util.DBHelper;
import util.GetSessionFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class UserDaoFactory {
    private Session session;
    public UserDaoFactory(Session session) {
        this.session = session;
    }

    public static IUserDaoFactoryCreator getCreatorByType() {
        FileInputStream fis;
        String type = null;

        Properties properties = new Properties();

        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("property");
//  ВОПРОС          fis =new FileInputStream("src/main/resources/test"); //так не работает, почему?
            properties.load(is);
            type = properties.getProperty("typeOfDao");
            if (type.equalsIgnoreCase("UserJdbcDao")){

                return new UserDaoJdbcFactoryCreator(DBHelper.getMysqlConnection());
            } else if (type.equalsIgnoreCase("UserHibernateDao")){
                return new UserDaoHibernateFactoryCreator(GetSessionFactory.getSessionFactory().openSession());
            }
//fis.close();
        } catch (IOException e) {
            System.out.println(" file is ququ");
        }

        throw  new RuntimeException("don't worry " + type);

    }
}