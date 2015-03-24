import DAccess.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.*;
import static org.junit.Assert.*;

public class Account {

    private static DBaccount DBA = new DBaccount();


    @BeforeClass
    public static void runOnceBeforeEachTest() throws SQLException {
        try{
            DBA.addAccount(1000,"Checking",1.00);
            DBA.addAccount(1001,"Savings",1000.00);
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Test
    public void testAccounts() throws SQLException {
        ResultSet rs =  DBA.clientBankAccounts("MK","Royal Bank");
        if (rs.next()) {
            int bcid = rs.getInt("bcid");
            String accounttype = rs.getString("accounttype");
            double balance = rs.getDouble("balance");

            assertEquals(1000, bcid);
            assertEquals("Checking",accounttype);
        }
    }

}
