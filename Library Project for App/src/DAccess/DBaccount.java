package DAccess;

import Exception.TooLargeWithdrawalException;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBaccount extends DBConnection {

    private Connection conn;
    private DBConnection DB = new DBConnection();

    public DBaccount(){
        super();
        if (getConn() != null){
            conn = getConn();
        }
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

    public void addAccount(int bcid, String Username, String Bankname, String accounttype, double balance) throws SQLException {
        try {
            int check = CreateBCID(bcid,Username,Bankname);
            if (check == 1) {
                String sql = "INSERT INTO `account`(`accountid`,`bcid`,`accounttype`,`balance`) VALUES (NULL," + bcid + ",'" + accounttype + "'," + balance + ")";
                Statement stmt = conn.createStatement();
                stmt.execute(sql);
            }
            else System.out.println("account not added");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public ResultSet clientBankAccounts(String clinet_username,String bankname) throws SQLException{
        String sql = "Select * from `account` where bcid in (select bcid from `bankclient` where bankid = '" +getBankID(bankname)+"' and clientid = '"+getClientID(clinet_username)+"')";
        return  DB.SelectStatement(sql);
    }

    public ResultSet AllClientAccounts(String clinet_username) throws SQLException {
        String sql = "Select * from `account` where bcid in (select bcid from `bankclient` where clientid = '"+getClientID(clinet_username)+"')";
        return  DB.SelectStatement(sql);
    }


    // transferring money from account one to account two
    // return 0 for fail and 1 for a successful transfer
    public int transfer(int accountOneBCID, int accountTwoBCID, double amountToTransfer) throws SQLException {
            double accountOneBalance = getBalance(accountOneBCID);
            double accountTwoBalance = getBalance(accountTwoBCID);
                if (amountToTransfer <= accountOneBalance) {
                    // deducting accountOne balance
                    setBalance(accountOneBCID, accountOneBalance - amountToTransfer);
                    DB.InsertIntoTransactionLog(accountOneBCID, "transfer $" + amountToTransfer + " to account " + accountTwoBCID,accountOneBalance, accountOneBalance - amountToTransfer);
                    // increasing accountTwo balance
                    setBalance(accountTwoBCID, accountTwoBalance + amountToTransfer);
                    DB.InsertIntoTransactionLog(accountTwoBCID, "received $" + amountToTransfer+ " from account " + accountOneBCID,accountTwoBalance,accountTwoBalance + amountToTransfer);
                }else{
                    return 0;
                }
        return 1;
    }



    private double getBalance(int bcid)throws SQLException{
        try {
            String sql = "select * from `account` where bcid = " + bcid;
            ResultSet rs = DB.SelectStatement(sql);
            double balance = -1;
            if (rs.next()) {
                return balance = rs.getInt("balance");
            }
        }catch (SQLException se){
            se.printStackTrace();
        }
        return -1;
    }

    private void setBalance(int bcid, double balance){
        try {
            String sql = "UPDATE `account` SET balance = " + balance + " WHERE bcid = "+ bcid;
            Statement stmt = conn.createStatement();
            stmt.execute(sql);

        } catch (SQLException se) {
            se.printStackTrace();
        }
    }





}
