package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDaoHibernateImpl us;

    public UserServiceImpl() {
        this.us = new UserDaoHibernateImpl();
    }

    @Override   // {        us.createUsersTable();    }
    public void createUsersTable()  {
        us.createUsersTable();
    }

    public void dropUsersTable() {
        us.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age)  {
        us.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        us.removeUserById(id);
    }

    public List<User> getAllUsers()  {
        return us.getAllUsers();
    }

    public void cleanUsersTable() {
       us.cleanUsersTable();
    }
}
