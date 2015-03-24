package DAccess;
import Exception.LessThanZero;



import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBaccount extends DBConnection {

    private Connection conn;
    DBConnection DB = new DBConnection();

    public DBaccount(){
        super();
        conn = getConn();
    }

    public void addAccount(int bcid, String accounttype, double balance) throws SQLException {
            try {
                    String sql = "INSERT INTO `account`(`accountid`,`bcid`,`accounttype`,`balance`) VALUES (NULL,"+ bcid +",'"+ accounttype +"',"+ balance +")";
                    Statement stmt = conn.createStatement();
                    stmt.execute(sql);

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    public ResultSet clientBankAccounts(String clinet_username,String bankname) throws SQLException{
        String sql = "Select * from `account` where bcid in (select bcid from `bankclient` where bankid = '" +getBankID(bankname)+"' and clientid = '"+getClientID(clinet_username)+"')";
        return  DB.SelectStatement(sql);
    }

    public double getBalance(int bcid) throws SQLException {
        String sql= "select * from `account` where bcid = " + bcid;
        ResultSet rs = DB.SelectStatement(sql);
        double balance = -1;
        if (rs.next()){
            balance = rs.getInt("balance");
        }
        return balance;
    }

    public void setBaalance(int bcid, double balance){
        try {
            String sql = "UPDATE `account` SET balance = " + balance + " WHERE bcid = "+ bcid;
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }





}
