package com.java;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static BasicDataSource connectionPool = new BasicDataSource();

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileReader("src/main/resources/database.properties"));
            connectionPool.setDriverClassName(properties.getProperty("driver"));
            connectionPool.setUrl(properties.getProperty("url"));
            connectionPool.setUsername(properties.getProperty("username"));
            connectionPool.setPassword(properties.getProperty("password"));
            connectionPool.setMaxTotal(100);
            connectionPool.setDefaultAutoCommit(false);
        } catch (IOException e) {
            System.out.println("File not found");
        }
    }



    public static Connection getInstance() {

        try {
            return connectionPool.getConnection();
        } catch (SQLException e) {
            System.out.println("Unable to connect, please try again later");
            //HACK: add exception to log file
        }
        // Review null value
        return null;
    }
}

