package Accounts;


public abstract class Account {
//    variables to be used
    private int number;
    private static int numberGenerator=10;
    protected double balance;



    public Account() {
        number = numberGenerator;
        numberGenerator = numberGenerator + 10;
    }

    public Account(int number , double balance){
        this.number = number;
        this.balance = balance;
    }

    public void deposit(double amount){
        balance = balance + amount;
    }

    public void withdraw(double amount) {
        if( balance >= amount ) balance = balance - amount;

    }

    public String toString() {
        String s;
        s = "Number: " + number + "Balance: " + balance;
        return s;
    }

//    Setters and Getters

    public void setNumber(int n) {
        number = n;
    }

    public int getNumber(){
        return number;
    }

    public void setBalance(double amount){
        balance = amount;
    }

    public double getBalance() {
        return balance;
    }





}
