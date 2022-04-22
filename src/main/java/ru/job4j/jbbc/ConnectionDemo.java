package ru.job4j.jbbc;

import ru.job4j.io.Config;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDemo {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Config config = new Config("C:\\projects\\job4j_design\\src\\main\\java\\ru\\job4j\\jbbc\\app.properties");
        config.load();

        Class.forName(config.value("driver"));
        String url = config.value("url_db");
        String login = config.value("login");
        String password = config.value("password");

        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getUserName());
            System.out.println(metaData.getURL());
        }
    }
}
