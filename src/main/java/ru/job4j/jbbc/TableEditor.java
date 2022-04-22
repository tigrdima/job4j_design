package ru.job4j.jbbc;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {
    private Connection connection;
    private final Properties properties;
    private Statement statement;

    public TableEditor(Properties properties) throws Exception {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName(properties.getProperty("driver"));
        String url = properties.getProperty("url_db");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
        statement = connection.createStatement();
    }

    public void createTable(String tableName) {
        try {
            String sql = String.format("create table if not exists %s ()", tableName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropTable(String tableName) {
        try {
            String sql = String.format("drop table if exists %s", tableName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try {
            String sql = String.format("alter table if exists %s add %s %s", tableName, columnName, type);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try {
            String sql = String.format("alter table if exists %s drop column if exists %s",
                    tableName, columnName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try {
            String sql = String.format("alter table %s rename column %s to %s",
                    tableName, columnName, newColumnName);
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            statement.close();
            connection.close();
        }
    }

    public static void main(String[] args) {

        Properties properties = new Properties();

        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {

            properties.load(in);

            try (TableEditor tableEditor = new TableEditor(properties);
                 Connection connection = tableEditor.connection) {

                tableEditor.createTable("www");
                System.out.println(getTableScheme(connection, "www"));

                tableEditor.addColumn("www", "id", "serial primary key");
                System.out.println(getTableScheme(connection, "www"));

                tableEditor.renameColumn("www", "id", "name");
                System.out.println(getTableScheme(connection, "www"));

                tableEditor.dropColumn("www", "name");
                System.out.println(getTableScheme(connection, "www"));

                tableEditor.dropTable("www");
                System.out.println(getTableScheme(connection, "www"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
