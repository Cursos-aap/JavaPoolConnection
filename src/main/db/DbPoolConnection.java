package main.db;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import java.sql.SQLException;
import java.sql.Connection;

public class DbPoolConnection {
    
    private final static String MYSQL_HOST = "127.0.0.1";
    private final static String MYSQL_USER = "tutorial";
    private final static String MYSQL_PASSWORD = "password";
    private final static short MYSQL_PORT = 3306;
    private final static String MYSQL_DATABASE = "tutorial";
    private final static String MYSQL_ENCODING = "UTF-8";
    
    private MysqlConnectionPoolDataSource poolDataSource = null;
        
    private MysqlConnectionPoolDataSource configureDataSource() throws SQLException{
        poolDataSource = new MysqlConnectionPoolDataSource();
        poolDataSource.setServerName(this.MYSQL_HOST);
        poolDataSource.setPort(this.MYSQL_PORT);
        poolDataSource.setDatabaseName(this.MYSQL_DATABASE);
        poolDataSource.setUser(this.MYSQL_USER);
        poolDataSource.setPassword(this.MYSQL_PASSWORD);
        poolDataSource.setAllowMultiQueries(true);
        poolDataSource.setCharacterEncoding(MYSQL_ENCODING);
        poolDataSource.setConnectTimeout(2_000); //2000 ms
        
        return poolDataSource;
    }
    
    protected Connection getConnection(){
        try{
            if(poolDataSource == null)
                configureDataSource();
            return poolDataSource.getPooledConnection().getConnection();
        }catch(SQLException e){
            System.err.println("The pool wasn't created correctly");
            System.out.println(e.toString());
            return null;
        }
    }
    
    protected void destroyPool(){
        poolDataSource = null;
    }
    
}