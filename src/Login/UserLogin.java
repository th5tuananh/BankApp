package Login;
import DAccess.*;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Mitra on 2/24/2015.
 */
public class UserLogin {
    DBConnection DB;

        public UserLogin(){
            DB = new DBConnection();
        }


    public Boolean IsAvailable(String username, String password){
        int hash = password.hashCode();
        try {
            ResultSet rs = DB.RetrieveFromTableName("client");
            while (rs.next()) {
                String UserName = rs.getString("username");
                int HashPassword = rs.getInt("password");

                if(UserName.contentEquals(username) && (HashPassword == hash) ){
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
