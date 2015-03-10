package Accounts;
import Exception.*;

public abstract class Account {
//    variables to be used
    private int number;
    private static int numberGenerator=10;
    protected double balance;


//constructor
    public Account() {
        number = numberGenerator;
        numberGenerator = numberGenerator + 10;
    }

//methods
    public Account(int number , double balance){
        this.number = number;
        this.balance = balance;
    }

    public void deposit(double amount){
        balance = balance + amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException{
        if( balance >= amount ) balance = balance - amount;
        else throw new InsufficientFundsException();
    }

    public void transfer(double amount, Account accFrom, Account accTo) throws InsufficientFundsException{
        if(amount <= accFrom.balance){
            accFrom.withdraw(amount);
            accTo.deposit(amount);
        }
        else throw new InsufficientFundsException();
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
