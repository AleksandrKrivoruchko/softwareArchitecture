package com.avk.connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    FileInputStream fis;
    Properties property = new Properties();
    {
        try {
            fis = new FileInputStream("src/main/resources/application.properties");
            property.load(fis);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(property.getProperty("spring.datasource.url"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
