package JUnitTest;

import DAccess.*;

import org.junit.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import static junit.framework.Assert.assertEquals;


public class Bank {

    private static Functions DB;

    @BeforeClass
    public static void runOnceBeforeEachTest() throws SQLException {
        DB = new Functions();
        try{
            // Adding Bank
            DB.DeleteTableData("bank");
            DB.AddNewBank("Royal Bank");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testBankName() throws SQLException {
        ResultSet rs =  DB.RetrieveFromTableName("bank");
        while (rs.next()) {
            String BankName = rs.getString("bankname");
            assertEquals("Royal Bank",BankName);
        }


    }




}
