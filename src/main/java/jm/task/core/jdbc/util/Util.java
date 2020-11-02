package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";
    private static final String LOCALHOST = "localhost";
    private static final String DB_NAME = "Test";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "12345678";
    private static final String POOL_SIZE = "2";
    private static final String HBM_2_DDL_AUTO = "update";
    private static final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
    private static final String URL = "jdbc:mysql://localhost:3306/Test?serverTimezone=Europe/Moscow&useSSL=false";

    Session session = null;

    // Connect to MySQL
    public Connection getMySQLConnection() throws SQLException {
        return getMySQLConnection(LOCALHOST, DB_NAME, USER_NAME, PASSWORD);
    }

    public Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException {
        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?serverTimezone=Europe/Moscow&useSSL=false";
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

    private Session createHibernateSession() {
        SessionFactory sessionFactory = null;
        ServiceRegistry serviceRegistry = null;
        try {
            try {
                Configuration cfg = new Configuration().
                        addResource("User.hbm.xml").configure();
                serviceRegistry = new StandardServiceRegistryBuilder().
                        applySettings(cfg.getProperties()).build();
                sessionFactory = cfg.buildSessionFactory(serviceRegistry);
            } catch (Throwable e) {
                System.err.println("Failed to create sessionFactory object." + e);
                throw new ExceptionInInitializerError(e);
            }
            session = sessionFactory.openSession();
            System.out.println("Session open");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return session;
    }

    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory;
        Configuration configuration = new Configuration()
                .setProperty("hibernate.connection.driver_class", DRIVER_CLASS)
                .setProperty("hibernate.connection.url", URL)
                .setProperty("hibernate.connection.password", PASSWORD)
                .setProperty("hibernate.connection.username", USER_NAME)
                .setProperty("hibernate.connection.pool_size", POOL_SIZE)
                .setProperty("hibernate.hbm2ddl.auto", HBM_2_DDL_AUTO)
                .setProperty("hibernate.dialect", HIBERNATE_DIALECT)
                .addAnnotatedClass(User.class);
        StandardServiceRegistryBuilder sBuilder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        return sessionFactory = configuration.buildSessionFactory(sBuilder.build());
    }
}
