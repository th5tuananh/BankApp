package JUnitTest;

import DAccess.*;

import org.junit.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.assertNotSame;
import static junit.framework.TestCase.assertEquals;


public class Bank {

    private static DBbank DB;

    @BeforeClass
    public static void runOnceBeforeEachTest() throws SQLException {
        DB = new DBbank();
        try{
            // Adding Bank
            DB.DeleteTableData("bank");
            DB.DeleteTableData("bankclient");
            DB.AddNewBank("Royal Bank");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testBankName() throws SQLException {
        ResultSet rs =  DB.SelectStatement("select bankname from `bank` where bankname = 'Royal Bank'");
        while (rs.next()) {
            String BankName = rs.getString("bankname");
            assertEquals("Royal Bank", BankName);
            assertNotSame("bank",BankName);
        }


    }




}
