import DAccess.*;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class AppMain {


    public static void main(String[] args) {
        DBbank DBb = new DBbank();
        DBclient DBc = new DBclient();
        DBaccount DBa = new DBaccount();


        Scanner scanner = new Scanner(System.in);

        menu();
        System.out.println("\n\nPlease enter a choice : ");
        int choice  = scanner.nextInt();

        while (choice != 5) {

            switch (choice) {
//  ==============>>  choices  <<===============

                default:
                    menu();
                    break;
                case 1:
                    System.out.println("enter the name of a bank to add");
                    String BankName = scanner.next();
                    try {
                        DBb.AddNewBank(BankName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    System.out.println("please enter first name");
                    String fname = scanner.next();
                    System.out.println("please enter last name");
                    String lname = scanner.next();
                    System.out.println("please enter a user name");
                    String username = scanner.next();
                    System.out.println("enter and password");
                    String pass = scanner.next();

                    try {
                        DBc.AddNewClient(fname, lname, username, pass);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    System.out.println("All Banks in System \n");
                    try {
                        ResultSet rs = DBb.RetrieveFromTableName("bank");
                        while (rs.next()) {
                            String bankname = rs.getString("bankname");
                            System.out.println(bankname);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    break;

                case 5:
                    break;




// ==============================================

            }

            // pause for 2 seconds before exit
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            menu();
            System.out.println("\n\nPlease enter a choice : ");
            choice = scanner.nextInt();
        }





        System.out.println("Program exit!!!");


    }



    // menu function
    public static void menu(){
        System.out.println("1) add bank \n" +
                           "2) add client \n" +
                           "3) view banks\n" +
                           "4) view clients\n" +
                           "5) to exit \n");
    }



}
