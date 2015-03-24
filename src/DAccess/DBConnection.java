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

    public void closeDB() throws SQLException {
        conn.close();
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

    //  running any select statement
    public ResultSet SelectStatement(String sql) throws SQLException{
        try{
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            return rs;
        }catch(SQLException se){
            se.printStackTrace();
        }
        return null;
    }


    //    returing all the data from a table
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

    public int getBankID(String bank_name) throws SQLException{
        try {
            DBConnection DB = new DBConnection();
            ResultSet rs = DB.SelectStatement("select bankid from bank where bankname = '"+bank_name+"'");
            if(rs.next()) {
                return rs.getInt("bankid");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    public int getClientID(String username) throws SQLException{
        try{
            DBConnection DB = new DBConnection();
            ResultSet rs = DB.SelectStatement("select clientid from client where username = '"+username+"'");
            if (rs.next()) {
                return rs.getInt("clientid");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }



    public Connection getConn() {
        return conn;
    }

}
