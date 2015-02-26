package JUnitTest;

import DAccess.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class Client {

    private static DBclient DBc;
    private static DBbank DBb;


    @BeforeClass
    public static void runOnceBeforeEachTest() throws SQLException {
        DBc = new DBclient();
        DBb = new DBbank();
        try{
            // Adding clients
            DBc.DeleteTableData("bankclient");
            DBc.DeleteTableData("client");
            DBc.AddNewClient("Mitra","Kalloo","mkalloo","password123");
            DBc.AddNewClient("Will","Smith","WillSmith","WillS");
            DBc.AddNewClient("Harry","Potter","HP","HPot");

        }catch(SQLException e){
            e.printStackTrace();
        }
    }


    // testing Logging is true/false
    @Test
    public void testBankName() throws SQLException {
        assertTrue(DBc.IsAvailable("mkalloo","password123"));
        assertFalse(DBc.IsAvailable("WillSmith","Wills"));
        assertTrue(DBc.IsAvailable("HP","HPot"));
    }

    @Test
    public void clientBanks() throws SQLException{
        int clientid = DBc.getClientID("mkalloo");
        int bankid = DBb.getBankID("Royal Bank");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (NULL, '"+bankid+"','"+clientid+"');");

        ResultSet rs = DBc.ClientBanks("mkalloo");
        while(rs.next()) {
            assertEquals("Royal Bank", rs.getString("bankname"));
        }
    }

}
