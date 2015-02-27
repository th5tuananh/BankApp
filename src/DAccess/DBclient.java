package DAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBclient extends DBConnection {

    private Connection conn;

    public DBclient(){
        super();
        conn = getConn();
    }



    public void AddNewClient(String firstname, String lastname, String UserName, String Password) throws SQLException {
        try {
            if (firstname.length()!=0 && lastname.length() != 0 && UserName.length() !=0 && Password.length()!=0) {
                String sql = "INSERT INTO `client`(`firstname`, `lastname`, `username`,`password`) VALUES ('" + firstname + "','" + lastname +"','" + UserName + "','" + Password.hashCode() + "')";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
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

    // getting all the Banks a client is a customer of
    public ResultSet ClientBanks(String client_Username) throws SQLException{
        DBConnection DB = new DBConnection();
        String sql = "select bankname from `bank` where bankid in (SELECT DISTINCT bankid from `bankclient`,`client` where bankclient.clientid in (SELECT clientid from client where client.username  = '"+client_Username+"') )";
        return  DB.SelectStatement(sql);
    }


    // checking is user name and password is present
    public Boolean IsAvailable(String username, String password) throws SQLException{
        int hash = password.hashCode();
        try {
            DBConnection DB = new DBConnection();
            ResultSet rs = DB.SelectStatement("select username, password from `client` where username = '"+username+"' and password = '"+hash+"'");
            if (rs.next()) {
                String UserName = rs.getString("username");
                int HashPassword = rs.getInt("password");
                if(UserName.contentEquals(username) && (HashPassword == hash) ){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
