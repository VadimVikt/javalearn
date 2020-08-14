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

    private static SessionFactory sessionFactory = null;
    private static ServiceRegistry serviceRegistry;

    static  {
        try {
            Properties prop = new Properties();
            prop.put(Environment.URL,"jdbc:mysql://localhost:3306/javalearn?useSSL=false&useUnicode=true&serverTimezone=UTC");
            prop.put(Environment.USER,"mysql" );
            prop.put(Environment.PASS, "mysql");
            prop.put(Environment.DRIVER, "com.mysql.jdbc.Driver");
            prop.put(Environment.SHOW_SQL, true);
            prop.put(Environment.HBM2DDL_AUTO, "update");
            prop.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");

            Configuration configuration = new Configuration().setProperties(prop);
            System.out.println(configuration.getProperties());
            configuration.addAnnotatedClass(User.class);
            // через интерфейс ServiceRegistry и потом класс
//                serviceRegistry = new StandardServiceRegistryBuilder().applySetting(configuration.getProperties()).build();
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties());

            sessionFactory = configuration.buildSessionFactory(builder.build());
        } catch (Exception e) {
            System.out.println("Исключение!" + e);
//                e.printStackTrace();
        }
    }
    public static SessionFactory getSessionFactory() {
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
