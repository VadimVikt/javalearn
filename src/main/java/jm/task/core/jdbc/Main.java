package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        UserServiceImpl userService = new UserServiceImpl();
        //Создание таблицы
        userService.createUsersTable();
        //Добавление 4 юзеров в таблицу
        List<User> users = new ArrayList<>();
        users.add(new User("Jhon", "Smith", (byte) 32));
        users.add(new User("Ivan", "Petrov", (byte) 18));
        users.add(new User("Li-Si-Cin", "Лисицын", (byte) 48));
        users.add(new User("Александр", "Sidorov", (byte) 55));
        for (User u : users) {
            userService.saveUser(u.getName(), u.getLastName(), u.getAge());
        }
        //Получение всех юзеров и вывод в консоль
        List<User> us =  userService.getAllUsers();
        for (User u : us) {
            System.out.println(u.toString());
        }
        //Очистка таблицы
        userService.cleanUsersTable();
        //Удаление таблицы
        userService.dropUsersTable();
    }
}
