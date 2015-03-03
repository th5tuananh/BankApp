package DAccess;


import java.sql.Connection;
import java.sql.ResultSet;

public class DBaccount extends DBConnection {

    private Connection conn;

    public DBaccount(){
        super();
        conn = getConn();
    }

    public ResultSet clientBankAccounts(String clinet_username,String bankname){

        ResultSet rs = null;
        return rs;
    }



}
