package DAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Mitra on 2/10/2015.
 */
public class Functions extends DBConnection {

    private Connection conn;

    public Functions(){
        super();
        conn = getConn();
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




}
