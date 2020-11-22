package main.db;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DbHandler {
    
    DbPoolConnection pool = new DbPoolConnection();
    DbConnection db_conn = new DbConnection();
    private int status = 0;
    
    public int insertPerson(String name){
        try(Connection connection = pool.getConnection()){
            connection.setAutoCommit(false);
            String query = "INSERT INTO Person(name) VALUE(?)";
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            status = ps.executeUpdate();
            
            connection.commit();
        } catch (SQLException e) {
            System.err.println("error while inserting person");
            System.out.println(e.toString());
        } finally{
            status = 0;
        }
        
        return status;
    }
    
    public int insertPersonWithoutPool(String name){
        try(Connection connection = db_conn.establishConnection()){
            String query = "INSERT INTO Person(name) VALUE(?)";
            
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, name);
            status = ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("error while inserting person");
            System.out.println(e.toString());
        } finally{
            status = 0;
        }
        
        return status;
    }
}