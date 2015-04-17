package DAccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBbank extends DBConnection {

    private Connection conn;
    DBConnection DB = new DBConnection();
    public DBbank() {
        super();
        if (getConn() != null){
            conn = getConn();
        }
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

    public int getBankID(String bank_name) throws SQLException{
        try {
            ResultSet rs = DB.SelectStatement("select * from bank where bankname = '"+bank_name+"'");
            if(rs.next()) {
                return rs.getInt("bankid");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }



}
