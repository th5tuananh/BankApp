import DAccess.*;
import jdk.internal.util.xml.impl.ReaderUTF16;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AppMain {


    public static void main(String[] args) throws IOException {
        DBbank DBb = new DBbank();
        DBclient DBc = new DBclient();

        try {
            ResultSet rs;

            String name = "AM";
            System.out.println("\n\n" + name +" banks");
            rs = DBc.ClientBanks(name);
            while(rs.next()){
                System.out.println(rs.getString("bankname"));
            }

            // deleting all data from a specific table
            //DBb.DeleteTableData("bank");
            //DBb.DeleteTableData("client");

            // close connection to database
            DBb.getConn().close();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
