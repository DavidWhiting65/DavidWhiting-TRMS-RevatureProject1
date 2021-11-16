package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {

    {System.out.println("in ConnectionUtil class");}

    private static ConnectionUtil cu = null;
    private static Properties props;

    // private constructor so we can control the creation of any instances)
    private ConnectionUtil() {
        props = new Properties();

        InputStream dbProps = ConnectionUtil.class.getClassLoader().getResourceAsStream("connection.properties");
        {System.out.println("dbProps: " + dbProps);}
        try {
            System.out.println("Try props.load(dbProps in ConnectionUtil constructor");
            props.load(dbProps);
        } catch (IOException e) {
            System.out.println("IOException in ConnectionUtil constructor");
            e.printStackTrace();
        }
        {System.out.println("props: " + props);}
    }

    // a getter method to return an instance of this ConnectionUtil Class
    public static synchronized ConnectionUtil getConnectionUtil() {

        {System.out.println("in getConnectionUtil() in ConnectionUtil class");}
        // Does one exist?
        if (cu == null) {
            // if it is null -> create one
            cu = new ConnectionUtil();
        }
        // if it's not null -> return the one that already exists
        System.out.println("cu: " + cu);
        return cu;
    }

    // a method to get the connection itself
    public Connection getConnection() {

        {System.out.println("in getConnection() in ConnectionUtil class");}

        Connection conn = null;

        // Hot fix to basically force the Driver class to load
        try {
            Class.forName(props.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Get the credentials needed to access the database from our connection.properties file
        String url = props.getProperty("url");
        String username = props.getProperty("username");
        String password = props.getProperty("password");

        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

}
