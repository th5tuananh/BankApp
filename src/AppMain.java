import DAccess.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppMain {


    public static void main(String[] args) throws IOException {
        DBbank DBb = new DBbank();
        DBclient DBc = new DBclient();

        try {
            // Adding Banks
            DBb.AddNewBank("Republic bank");
            DBb.AddNewBank("Royal Bank");

            // Printing out all Banks from the database
            ResultSet rs = DBb.RetrieveFromTableName("bank");
            System.out.println("list of banks...");
            while (rs.next()) {
                String BankName = rs.getString("bankname");
                int BankID = rs.getInt("bankid");
                System.out.println(BankID + " " + BankName);
            }

            // adding clients to the batabase
            DBc.AddNewClient("Will", "Smith", "WillSmith", "hi");
            DBc.AddNewClient("Harry", "Potter", "HP", "Hola");

            System.out.println(DBc.IsAvailable("WillSmith", "hi"));

            // Printing out all clients
            rs = DBb.RetrieveFromTableName("client");
            System.out.println("list of Clients...");
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                System.out.println("client name is " + firstname + " " + lastname);
            }


            String name = "mkalloo";
            System.out.println("\n\n" + name +" banks");
            rs = DBc.ClientBanks(name);
            while(rs.next()){
                System.out.println(rs.getString("bankname"));
            }

            // deleting all data from a specific table
            DBb.DeleteTableData("bank");
            DBb.DeleteTableData("client");

            // close connection to database
            DBb.getConn().close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
