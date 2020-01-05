package Dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import service.UserService;
import util.DBHelper;
import util.GetSessionFactory;

public class UserDaoHibernateFactoryCreator implements IUserDaoFactoryCreator {
    private Session session;
    public UserDaoHibernateFactoryCreator(Session session) {
        this.session = session;
    }

    @Override
    public IUserDao createUserDao() {
        return new UserHibernateDao(GetSessionFactory.getSessionFactory().openSession());
    }
}
