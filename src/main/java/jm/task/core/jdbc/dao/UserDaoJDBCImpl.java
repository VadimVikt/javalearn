package jm.task.core.jdbc.dao;

//import jdk.internal.access.JavaIOFileDescriptorAccess;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connect = null;
        try{
            connect = Util.getMySQLConnection();
            String sql;
            sql = "CREATE TABLE IF NOT EXISTS `javalearn`.`users` ( `id` BIGINT NOT NULL AUTO_INCREMENT , `name` VARCHAR(50) NOT NULL , `last_name` VARCHAR(50) NOT NULL , `age` TINYINT NOT NULL , PRIMARY KEY (`id`)) ENGINE = InnoDB";
            Statement statement = connect.createStatement();
            boolean rs = statement.execute(sql);
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        } finally {
            assert connect != null;
            connect.close();
        }
    }

    public void dropUsersTable() throws SQLException {
        Connection connect = null;
        try {
            connect = Util.getMySQLConnection();
            String sql;
            sql = "drop table if exists `javalearn` . users cascade;";
            Statement statement = connect.createStatement();
            boolean rs = statement.execute(sql);
        }catch (SQLException  throwables) {
            throwables.printStackTrace();
        } finally {
            assert connect != null;
            connect.close();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection con = null;
        PreparedStatement preSt;
        String sql = "INSERT INTO `javalearn`. users (name, last_name, age) VALUES(?, ?, ?)";
        try {
            con = Util.getMySQLConnection();
            preSt = con.prepareStatement(sql);
            preSt.setString(1, name);
            preSt.setString(2, lastName);
            preSt.setByte(3, age);
            preSt.executeUpdate();
            System.out.println("User с именем - " + name + " добавлен в базу данных");
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
            assert con != null;
            con.close();
        }
    }

    public void removeUserById(long id) {
        Connection connect;
        PreparedStatement preSt;
        String sql;
        sql = "delete from `javalearn`.users where id= ?";
        try {
            connect = Util.getMySQLConnection();
            preSt = connect.prepareStatement(sql);
            preSt.setLong(1, id);
            preSt.executeUpdate();
        } catch (SQLException  throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        Connection connect = null;
        try {
            connect = Util.getMySQLConnection();
            String sql;
            sql = "SELECT * from users;";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                User us = new User();
                us.setId(rs.getLong(1));
                us.setName(rs.getString(2));
                us.setLastName(rs.getString(3));
                us.setAge(rs.getByte(4));
                users.add(us);
            }
        } catch (SQLException  e) {
            e.printStackTrace();
        } finally {
            assert connect != null;
            connect.close();
        }
        return users;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connect = null;
        try {
            connect = Util.getMySQLConnection();
            String sql;
            sql = "truncate table  `javalearn` . users;";
            Statement statement = connect.createStatement();
            boolean rs = statement.execute(sql);
        }catch (SQLException  throwables) {
            throwables.printStackTrace();
        } finally {
            assert connect != null;
            connect.close();
        }
    }
}
