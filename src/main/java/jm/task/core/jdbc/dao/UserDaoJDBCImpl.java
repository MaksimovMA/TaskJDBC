package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Util util = new Util();
        try (Connection conn = util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String sqlCommand = "CREATE TABLE IF NOT EXISTS " +
                    "User (id INT NOT NULL AUTO_INCREMENT," +
                    " Name VARCHAR(30)," +
                    " lastName VARCHAR(30), " +
                    "age TINYINT,  PRIMARY KEY (id))";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlCommand);
            preparedStatement.executeUpdate();
            conn.commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void dropUsersTable() {
        Util util = new Util();
        try (Connection conn = util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String sqlCommand = "DROP TABLE IF EXISTS User";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sqlCommand)) {
                preparedStatement.executeUpdate();
                conn.commit();
            } catch (SQLException e)
            {conn.rollback();}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Util util = new Util();
        try (Connection conn = util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String sqlCommand = "INSERT User (name, lastName, age) VALUE (?,?,?)";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sqlCommand)){
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            conn.commit();
            } catch (SQLException e)
            {conn.rollback();}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.printf("User с именем – %S добавлен в базу данных\n", name);
    }

    public void removeUserById(long id) {
        Util util = new Util();
        try (Connection conn = util.getMySQLConnection()) {
            conn.setAutoCommit(false);
            String sqlCommand = " DELETE FROM User WHERE Id = ?";
            try (PreparedStatement preparedStatement = conn.prepareStatement(sqlCommand)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            conn.commit();
            } catch (SQLException e)
            {conn.rollback();}
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Util util = new Util();
        List<User> users = new ArrayList<>();
        try (Connection conn = util.getMySQLConnection()) {
            String sqlCommand = "SELECT * FROM User";
            PreparedStatement preparedStatement = conn.prepareStatement(sqlCommand);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);
                users.add(new User(name, lastName, age));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        Util util = new Util();
        try (Connection conn = util.getMySQLConnection();
             Statement statement = conn.createStatement()) {
            String sqlCommand = "TRUNCATE TABLE User";
            statement.executeUpdate(sqlCommand);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
