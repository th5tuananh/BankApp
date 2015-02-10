import DAccess.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppMain {

    public static void main(String[] args) throws IOException {
            Functions DB = new Functions();
        try{



            // Adding Banks
            DB.AddNewBank("Republic bank");
            DB.AddNewBank("Royal Bank");

            // Printing out all Banks from the database
            ResultSet rs =  DB.RetrieveFromTableName("bank");
                System.out.println("list of banks...");
                while (rs.next()) {
                    String BankName = rs.getString("bankname");
                    int BankID = rs.getInt("bankid");
                    System.out.println(BankID + " " + BankName);
                }

            // adding clients to the batabase
            DB.AddNewClient("Will","Smith");
            DB.AddNewClient("Harry","Potter");

            // Printing out all clients
            rs = DB.RetrieveFromTableName("client");
            System.out.println("list of Clients...");
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                System.out.println("client name is "+ firstname + " " + lastname);
            }

            // deleting all data from a specific table
            DB.DeleteTableData("bank");
            DB.DeleteTableData("client");

            // close connection to database
            DB.getConn().close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
