package com.java;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    static BasicDataSource connectionPool = new BasicDataSource();

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


    public static void main(String[] args) {
        Connection con = null;
        try {
            con = connectionPool.getConnection();
            PreparedStatement st = con.prepareStatement("select * from Customer");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getInt(2) + " " + rs.getInt(3) + " " + rs.getInt(4) + " " + rs.getInt(5) + " " + rs.getInt(6) + " " + rs.getInt(7));

            }
            con.close();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

}
