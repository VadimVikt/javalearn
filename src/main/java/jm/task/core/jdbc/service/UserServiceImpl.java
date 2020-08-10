package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    public void createUsersTable() throws SQLException {
        UserDaoJDBCImpl usersTable = new UserDaoJDBCImpl();
        usersTable.createUsersTable();
    }

    public void dropUsersTable() throws SQLException {
        UserDaoJDBCImpl usersTable = new UserDaoJDBCImpl();
        usersTable.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        UserDaoJDBCImpl newUser = new UserDaoJDBCImpl();
        newUser.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        UserDaoJDBCImpl remUs = new UserDaoJDBCImpl();
        remUs.removeUserById(id);
    }

    public List<User> getAllUsers() throws SQLException {
        UserDaoJDBCImpl allUsers = new UserDaoJDBCImpl();
        return allUsers.getAllUsers();
    }

    public void cleanUsersTable() throws SQLException {
        UserDaoJDBCImpl cleanTable = new UserDaoJDBCImpl();
        cleanTable.cleanUsersTable();

    }
}
