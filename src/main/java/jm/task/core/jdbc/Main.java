package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        // Создание 4 User(ов)
        User user = new User("Maksim", "Maksimov", (byte) 26);
        User user1 = new User("Misha", "Petrov", (byte) 12);
        User user2 = new User("Fedor", "Reshkin", (byte) 42);
        User user4 = new User("German", "Mentor", (byte) 99);

        UserService userService = new UserServiceImpl();
        //Создание таблицы User(ов)
        userService.createUsersTable();

        //Добавление 4 User(ов) в таблицу с данными на свой выбор.
        // После каждого добавления должен быть вывод в консоль
        // ( User с именем – name добавлен в базу данных )
        userService.saveUser(user.getName(),user.getLastName(),user.getAge());
        userService.saveUser(user1.getName(),user1.getLastName(),user1.getAge());
        userService.saveUser(user2.getName(),user2.getLastName(),user2.getAge());
        userService.saveUser(user4.getName(),user4.getLastName(),user4.getAge());
        System.out.println(userService.getAllUsers().toString());

        //Очистка таблицы User(ов)
        userService.cleanUsersTable();

        //Удаление таблицы
        userService.dropUsersTable();
    }
}


