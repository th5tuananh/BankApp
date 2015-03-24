import DAccess.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.*;
import static org.junit.Assert.*;


public class Bank {

    private static DBbank DB;

    @BeforeClass
    public static void runOnceBeforeEachTest() throws SQLException {
        DB = new DBbank();
        try{
            // Adding Bank
            DB.DeleteTableData("bank");
            DB.DeleteTableData("client");
            DB.DeleteTableData("bankclient");
            DB.DeleteTableData("account");

            DB.AddNewBank("Royal Bank");
            DB.AddNewBank("Republic Bank");
            DB.AddNewBank("Scotia Bank");
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
