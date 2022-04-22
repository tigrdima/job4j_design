package ru.job4j.jbbc;

import ru.job4j.io.Config;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {

    private static Connection getConnection() throws Exception {
        Config config = new Config("src/main/resources/app.properties");
        config.load();

        Class.forName(config.value("driver"));
        String url = config.value("url_db");
        String login = config.value("login");
        String password = config.value("password");
        return DriverManager.getConnection(url, login, password);
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try (Connection connection = getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
