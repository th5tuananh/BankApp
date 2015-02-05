package Accounts;

/**
 * Created by Mitra on 1/27/2015.
 */
public class Saving extends Account{

    private double interestRate;

    public Saving(int number, double balance, double interest){
        super(number, balance);
        interestRate = interest;
    }


    public void calculateInterest() {
        double interest;
        interest = balance * (interestRate/12.0);
        deposit(interest);

    }
    public String toString(){
        String s;
        s = super.toString();
        s = s + " Interest Rate: " + interestRate;
        return s;
    }

}
