package DAccess;
import DAccess.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/**
 * Created by Mitra on 2/24/2015.
 */
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
            }else{
                // a null name was entered
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
    }

    public int getClientID(String user_name) throws SQLException{
        DBConnection DB = new DBConnection();
        try {
            ResultSet rs = DB.RetrieveFromTableName("client");
            while (rs.next()) {
                String UserName = rs.getString("username");
                int ID = rs.getInt("clientid");

                if(UserName.contentEquals(user_name)){
                    return ID;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void ClientBanks(String client_name) throws SQLException{
        DBConnection DB = new DBConnection();
        DBbank DBb = new DBbank();
        int ID = getClientID(client_name);
        try {
            ResultSet rs = DB.RetrieveFromTableName("bankclient");
            while (rs.next()) {
                if (ID == rs.getInt("clientid")){
                    System.out.println(DBb.BankName(rs.getInt("bankid")));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }


    }


}
