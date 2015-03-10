package DAccess;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBaccount extends DBConnection {

    private Connection conn;

    public DBaccount(){
        super();
        conn = getConn();
    }

    public ResultSet clientBankAccounts(String clinet_username,String bankname) throws SQLException{
        DBConnection DB = new DBConnection();
        String sql = "select * from `account` where bcid in (select bcid from `bankclient` where bankid = '" +getBankID(bankname)+"' and clientid = '"+getClientID(clinet_username)+"')";
        return  DB.SelectStatement(sql);
    }



}
