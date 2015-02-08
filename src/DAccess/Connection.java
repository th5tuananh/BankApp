package DAccess;

import java.io.IOException;
import java.sql.*;

/**
 * Created by Mitra on 2/7/2015.
 */
public class Connection {

    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://www.db4free.net/comp3550project";
    private final String username = "comp3550project";
    private final String password = "password123";

    private java.sql.Connection conn;
    private boolean connected;


    public Connection(){
        super();
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,username,password);
            if (conn != null) this.connected = true;
            System.out.println("works i think!!");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            connected = false;
        }
    }


    public void RetrieveFromTableName() throws SQLException{

        try{
            String sql = "SELECT * FROM locations";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            int largest = 0;
            String temp = "";
            while(rs.next()){
                String location = rs.getString("location");
                int tweets = rs.getInt("tweets");
                System.out.println(location + " " + tweets );
                if (tweets > largest){
                    temp = location;
                    largest = tweets;
                }

            }

            System.out.println("\n\nThe most tweeted is " + temp + " with amount " + largest);

        }catch(SQLException se){
            se.printStackTrace();
        }

    }

    public void InsertIntoDB(String sql) throws SQLException {

        try {
            Statement stmt = conn.createStatement();
            stmt.executeQuery(sql);

        }catch (SQLException se){
            se.printStackTrace();
        }



    }


}
