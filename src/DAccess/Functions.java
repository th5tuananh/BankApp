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


    //    returing all the data from a SELECT statement
    public ResultSet RetrieveFromTableName(String tablename) throws SQLException {
        try{
            String sql = "SELECT * FROM " + tablename;
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
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
