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
            } else {
                // a null name was entered
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public String BankName(int ID){
        String str = "";
        DBConnection DB = new DBConnection();
        try {
            ResultSet rs = DB.RetrieveFromTableName("bank");
            while (rs.next()) {
                if (ID == rs.getInt("bankid")){
                    str = rs.getString("bankname");
                    return str;
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return str;
    }


}
