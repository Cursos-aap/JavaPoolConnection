package main.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DbHandler {
    
    DbConnection db_conn = new DbConnection();
    
    public int insertPerson(String name){
        
        int status = 0;
        try(Connection connection = db_conn.establishConnection()){
            String query = "Call tutorial.insertPerson(?)";
            connection.setAutoCommit(false);
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            
            status = ps.executeUpdate();
            
            connection.commit();
        }catch(SQLException e){
            System.err.println("error while inserting person");
            System.err.println(e.getMessage());
        }
        
        return status;
    }
    
}