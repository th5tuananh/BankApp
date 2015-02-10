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
    private boolean connected;


    public DBConnection(){
        super();
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, username, password);
            if (conn != null) this.connected = true;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            connected = false;
        }
    }


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

    public void InsertIntoDBTable(String sql) throws SQLException {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        }catch (SQLException se){
            se.printStackTrace();
        }
    }


    public void AddNewBank(String BankName) throws SQLException{
        try {
            if (BankName.length()!=0) {
                String sql = "INSERT INTO `bank`(`bankname`) VALUES ('" + BankName + "')";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }else{
                // a null name was entered
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }


    public void AddNewClient(String firstname, String lastname) throws SQLException{
        try {
            if (firstname.length()!=0 && lastname.length() != 0) {
                String sql = "INSERT INTO `client`(`firstname`, `lastname`) VALUES ('" + firstname + "','" + lastname +"')";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }else{
                // a null name was entered
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }






    public boolean isConnected() {
        return connected;
    }


}
