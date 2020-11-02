package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {

    public void createUsersTable() {
        UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
        userDaoJDBC.createUsersTable();
    }

    public void dropUsersTable() {
        UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
        userDaoJDBC.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
        userDaoJDBC.saveUser(name,lastName,age);
    }

    public void removeUserById(long id) {
        UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
        userDaoJDBC.removeUserById(id);
    }

    public List<User> getAllUsers() {
        UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
        return userDaoJDBC.getAllUsers();
    }

    public void cleanUsersTable() {
        UserDaoHibernateImpl userDaoJDBC = new UserDaoHibernateImpl();
        userDaoJDBC.cleanUsersTable();
    }
}
