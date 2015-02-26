package DAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBbank extends DBConnection {

    private Connection conn;

    public DBbank() {
        super();
        conn = getConn();
    }


    public void AddNewBank(String BankName) throws SQLException {
        try {
            if (BankName.length() != 0) {
                String sql = "INSERT INTO `bank`(`bankname`) VALUES ('" + BankName + "')";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public int getBankID(String bank_name){
        try {
            DBConnection DB = new DBConnection();
            ResultSet rs = DB.SelectStatement("select bankid from bank where bankname = '"+bank_name+"'");
            if(rs.next()) {
                return rs.getInt("bankid");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return -1;
    }



}
