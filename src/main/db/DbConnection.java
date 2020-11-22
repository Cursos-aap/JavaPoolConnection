package main.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    
    protected static Connection establishConnection(){
        Properties properties = new Properties();
        properties.put("user", "tutorial");
        properties.put("host", "127.0.0.1");
        properties.put("password", "password");
        properties.put("charset", "UTF-8");
        properties.put("db", "tutorial");
        Connection connection = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(
                "jdbc:mysql://" + properties.getProperty("host") + 
                "/" + properties.getProperty("db"),
                properties
            );
        }catch(ClassNotFoundException | IllegalAccessException | InstantiationException | SQLException e){
            System.err.println("Error while connecting to db at establishConnection");
            System.out.println(e.toString());
        }
        return connection;
    }
    
}
