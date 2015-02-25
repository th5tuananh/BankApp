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


    //    returing all the data from a SELECT statement
    public ResultSet RetrieveFromTableName(String tablename) throws SQLException {
        try{
            String sql = "SELECT * FROM " + tablename;
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }catch(SQLException se){
            se.printStackTrace();
        }
        return null;
    }

    //    can be used to run a custom sql update or insert statement
    public void UpdateOrInsert(String sql) throws SQLException {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    //    for deleting all data from a specific table
    public void DeleteTableData(String tablename) throws SQLException {
        try{
            String sql = "DELETE  FROM " + tablename;
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }catch(SQLException se){
            se.printStackTrace();
        }
    }



    public Connection getConn() {
        return conn;
    }

}
