package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

// URL -> protocol//[hosts][/database][?properties]
// jdbc:mysql://user:password@[myhost1:1111,address=(host=myhost2)(port=2222)(key2=value2)]/db

public class DbConnection {
    
    protected Connection establishConnection() {
        
        Properties properties = new Properties();
        properties.put("user", "tutorial");
        properties.put("host", "127.0.0.1");
        properties.put("password", "password");
        properties.put("charset", "UTF-8");
        properties.put("db", "tutorial");
        
        Connection connection = null;
        final String url = "jdbc:mysql://" + properties.getProperty("host") + "/" + properties.getProperty("db");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, properties);
        } catch (ClassNotFoundException | IllegalAccessException
                | InstantiationException | SQLException e){
            System.err.println("Error while connecting to db at DbConnection.establishConnection");
            System.err.println(e.getMessage());
        }
        
        return connection;
        
    }
    
}