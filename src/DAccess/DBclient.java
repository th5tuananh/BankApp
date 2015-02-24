package DAccess;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

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


}
