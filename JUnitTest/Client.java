import DAccess.*;
import org.junit.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.Assert.*;

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
            DBc.AddNewClient("Amit","Maraj","AM","password");

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
        assertTrue(DBc.IsAvailable("AM", "password"));
    }

    @Test
    public void clientBanks() throws SQLException{
        int clientid = DBc.getClientID("mkalloo");
        int bankid = DBb.getBankID("Royal Bank");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (NULL, '"+bankid+"','"+clientid+"');");
        clientid = DBc.getClientID("AM");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (NULL, '"+bankid+"','"+clientid+"');");
        bankid = DBb.getBankID("Republic Bank");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (NULL, '"+bankid+"','"+clientid+"');");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (NULL, '"+bankid+"','"+clientid+"');");
        bankid = DBb.getBankID("Social Bank");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (NULL, '"+bankid+"','"+clientid+"');");

        ResultSet rs = DBc.ClientBanks("mkalloo");
        while(rs.next()) {
            assertEquals("Royal Bank", rs.getString("bankname"));

        }

        rs = DBc.ClientBanks("AM");
        if (rs.next()){
            assertEquals("Royal Bank", rs.getString("bankname"));
        }
        if (rs.next()){
            assertEquals("Republic Bank", rs.getString("bankname"));
        }
        if (rs.next()){
            assertEquals("Social Bank", rs.getString("bankname"));
        }


    }

}
