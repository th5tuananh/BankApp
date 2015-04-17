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

        while (choice != 99) {

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
                    System.out.println("All Clients in System \n");
                    try {
                        ResultSet rs = DBb.RetrieveFromTableName("client");
                        while (rs.next()) {
                            fname = rs.getString("firstname");
                            lname = rs.getString("lastname");
                            username = rs.getString("username");
                            System.out.println("Name : "+ fname + " "+ lname + " | Username : " + username);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 5:
                    System.out.println("Add New Account in System \n");

                    System.out.println("please enter and account number \n");
                    int bcid = scanner.nextInt();
                    System.out.println("please enter username");
                    username = scanner.next();
                    System.out.println("please enter Bank Name");
                    String bankname = scanner.next();
                    System.out.println("please enter account type");
                    String accounttype = scanner.next();
                    System.out.println("please enter balancer \n");
                    double balance = scanner.nextInt();

                    try {
                        DBa.addAccount(bcid,username,bankname,accounttype,balance);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;

                case 6:
                    System.out.println("All Clients in System \n");
                    System.out.println("please enter client username");
                    username = scanner.next();
                    try {
                        ResultSet rs = DBa.AllClientAccounts(username);
                        while (rs.next()) {
                            bcid = rs.getInt("bcid");
                            accounttype = rs.getString("accounttype");
                            balance = rs.getDouble("balance");
                            System.out.println("account # : "+ bcid + " | "+ accounttype + " | balance : $" + balance);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;




// ==============================================

            }

            // pause for 2 seconds before exit
            try {
                Thread.sleep(2500);
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
                           "5) add account\n" +
                           "6) view all client accounts\n" +
                           "99) to exit \n");
    }



}
