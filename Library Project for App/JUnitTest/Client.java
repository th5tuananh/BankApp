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
            // Adding client
            DBc.AddNewClient("Mitra","Kalloo","MK","password");
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
        assertTrue(DBc.IsAvailable("MK","password"));
        assertFalse(DBc.IsAvailable("WillSmith","Wills"));
        assertTrue(DBc.IsAvailable("HP","HPot"));
        assertTrue(DBc.IsAvailable("AM", "password"));
    }

    @Test
    public void clientBanks() throws SQLException{
        int clientid = DBc.getClientID("MK");
        int bankid = DBb.getBankID("Royal Bank");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (" +1000+ ", '"+bankid+"','"+clientid+"');");
        clientid = DBc.getClientID("AM");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (" +1001+ ", '"+bankid+"','"+clientid+"');");
        bankid = DBb.getBankID("Republic Bank");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (" +1002+ ", '"+bankid+"','"+clientid+"');");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (" +1003+ ", '"+bankid+"','"+clientid+"');");
        bankid = DBb.getBankID("Scotia Bank");
        DBc.UpdateOrInsert("INSERT INTO `softengbankapp`.`bankclient` (`bcid`, `bankid`, `clientid`) VALUES (" +1004+ ", '"+bankid+"','"+clientid+"');");

        ResultSet rs = DBc.ClientBanks("MK");
        while(rs.next()) {
            assertEquals("Royal Bank", rs.getString("bankname"));

        }

        rs = DBc.ClientBanks("AM");
        int count = 0;
        if (rs.next()){
            assertEquals("Royal Bank", rs.getString("bankname"));
            count++;
        }
        if (rs.next()){
            assertEquals("Republic Bank", rs.getString("bankname"));
            count++;
        }
        if (rs.next()){
            assertEquals("Scotia Bank", rs.getString("bankname"));
            count++;
        }

        assertEquals(3,count);


    }

}
