package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    //В класс Util должна быть добавлена конфигурация для Hibernate ( рядом с JDBC)
    //Настройки Hibernate
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/javalearn?useSSL=false&useUnicode=true&serverTimezone=UTC");
                settings.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
                settings.put(Environment.USER, "mysql");
                settings.put(Environment.PASS, "mysql");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.HBM2DDL_AUTO, "update");
                configuration.setProperties(settings);

                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static Connection getMySQLConnection() throws SQLException {
        String hostName = "localhost";
        String dbName = "javalearn";
        String userName = "mysql";
        String password = "mysql";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    private static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException {
        String conUrl = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?verifyServerCertificate=false&useSSL=false&requireSSL=false&serverTimezone=UTC";
        return DriverManager.getConnection(conUrl, userName, password);
    }
}
