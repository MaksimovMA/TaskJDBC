package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            try {
                Query query = session.createSQLQuery("CREATE TABLE IF NOT EXISTS User (id INT, name VARCHAR(20)," +
                        " lastName VARCHAR(20), age INT)");
                query.executeUpdate();
                tx1.commit();
            } catch (Exception e) {
                if (tx1 != null) {
                    tx1.rollback();
                }
            }
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            try {
                Query query = session.createSQLQuery("DROP TABLE if exists User");
                query.executeUpdate();
                tx1.commit();
            } catch (Exception e) {
                if (tx1 != null) {
                    tx1.rollback();
                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            try {
                session.save(new User(name, lastName, age));
                tx1.commit();
            } catch (Exception e) {
                if (tx1 != null) {
                    tx1.rollback();
                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            try {
                User user = session.get(User.class, id);
                session.delete(user);
                tx1.commit();
            } catch (Exception e) {
                if (tx1 != null) {
                    tx1.rollback();

                }

            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            try {
                users = session.createQuery("FROM User").list();
                tx1.commit();
            } catch (Exception e) {
                if (tx1 != null) {
                    tx1.rollback();
                }
            }
        }
        return users;
    }


    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction tx1 = session.beginTransaction();
            try {
                Query query = session.createSQLQuery("DELETE FROM User");
                query.executeUpdate();
                tx1.commit();
            } catch (Exception e) {
                if (tx1 != null) {
                    tx1.rollback();
                }
            }
        }
    }
}