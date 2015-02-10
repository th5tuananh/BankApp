package DAccess;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnection {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://www.db4free.net/softengbankapp";
    private final String username = "softengbankapp";
    private final String password = "Password123";



    private Connection conn;
    //private boolean connected;


    public DBConnection(){
        super();
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            //if (conn != null) this.connected = true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            //connected = false;
        }
    }



    public Connection getConn() {
        return conn;
    }

}
