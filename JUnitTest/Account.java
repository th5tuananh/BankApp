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
            DBA.addAccount(1000,"Checking",54745.00);
            DBA.addAccount(1001,"Savings",9574.00);
            DBA.addAccount(1002,"Checking",45643.00);
            DBA.addAccount(1003,"Savings",53635.00);
            DBA.addAccount(1004,"Checking",67863.00);


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

    @Test
    public void testTransfer() throws SQLException{
       int chk=  DBA.transfer(1000,1001,574);
        DBA.transfer(1002,1003,1000);

        ResultSet rs =  DBA.clientBankAccounts("MK","Royal Bank");
        if (rs.next()) {
            int bcid = rs.getInt("bcid");
            String accounttype = rs.getString("accounttype");
            double balance = rs.getDouble("balance");

            assertEquals(1000, bcid);
            assertEquals("Checking",accounttype);
            assertEquals(1,chk);
            //assertTrue(0==balance);

        }

    }

}
