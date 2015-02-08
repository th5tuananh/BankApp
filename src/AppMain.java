import DAccess.*;

import java.io.IOException;
import java.sql.SQLException;


public class AppMain {

    public static void main(String[] args) throws IOException {
        Connection DB = new Connection();
        try {
            DB.RetrieveFromTableName();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
