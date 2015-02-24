package JUnitTest;


import DAccess.*;
import Login.*;

import org.junit.BeforeClass;
import org.junit.Test;


import java.sql.SQLException;

import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class Client {

    private static UserLogin UL;
    private static DBclient DBc;


    @BeforeClass
    public static void runOnceBeforeEachTest() throws SQLException {
        DBc = new DBclient();
        try{
            // Adding clients
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
        UL = new UserLogin();
        assertTrue(UL.IsAvailable("mkalloo","password123"));
        assertFalse(UL.IsAvailable("WillSmith","Wills"));
        assertTrue(UL.IsAvailable("HP","HPot"));
        }


}
