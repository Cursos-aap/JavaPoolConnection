package main.db;
import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import java.sql.SQLException;
import java.sql.Connection;

public class DbPoolConnection {
    
    private final String MYSQL_HOST = "127.0.0.1";
    private final String MYSQL_USER = "tutorial";
    private final String MYSQL_PASSWORD = "password";
    private final short MYSQL_PORT = 3306;
    private final short MYSQL_POOL_SIZE = 15;
    private final String MYSQL_DATABASE = "tutorial";
    private final String MYSQL_ENCODING = "UTF-8";
    private final String URL = String.format(
        "jdbc:mysql://%s:%d/%s?max-connections=%d", 
        MYSQL_HOST,
        MYSQL_PORT,
        MYSQL_DATABASE,
        MYSQL_POOL_SIZE
    );
        
    private MysqlConnectionPoolDataSource poolDataSource = null;
    
    private MysqlConnectionPoolDataSource configureDataSource() throws SQLException{
        
        this.poolDataSource = new MysqlConnectionPoolDataSource();
        this.poolDataSource.setURL(URL);
        this.poolDataSource.setServerName(this.MYSQL_HOST);      
        this.poolDataSource.setPort(this.MYSQL_PORT);
        this.poolDataSource.setDatabaseName(this.MYSQL_DATABASE);
        this.poolDataSource.setUser(this.MYSQL_USER);
        this.poolDataSource.setPassword(this.MYSQL_PASSWORD);
        this.poolDataSource.setAllowMultiQueries(true);
        this.poolDataSource.setCharacterEncoding(MYSQL_ENCODING);
        this.poolDataSource.setConnectTimeout(2_000); //2000 ms
        
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
        if(poolDataSource != null){
            try {
                poolDataSource.getPooledConnection().close();
            } catch (SQLException e) {
                System.err.println("An error ocurred while closing the pool");
                System.out.println(e.toString());
            }
        }
        poolDataSource = null;
    }
    
}
