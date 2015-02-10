import DAccess.*;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppMain {

    public static void main(String[] args) throws IOException {
        DBConnection DB = new DBConnection();
        try{




            DB.AddNewBank("");
            ResultSet rs =  DB.RetrieveFromTableName("bank");
                System.out.println("list of banks...");
                while (rs.next()) {
                    String BankName = rs.getString("bankname");
                    int BankID = rs.getInt("bankid");
                    System.out.println(BankID + " " + BankName);
                }

            DB.AddNewClient("Person First name ","Person last name");
            rs = DB.RetrieveFromTableName("client");
            System.out.println("list of Clients...");
            while (rs.next()) {
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                System.out.println("client name is "+ firstname + " " + lastname);
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
