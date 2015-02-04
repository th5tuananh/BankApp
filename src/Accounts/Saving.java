package Accounts;

/**
 * Created by Mitra on 1/27/2015.
 */
public class Saving extends Account{

    private float AccountBalance;

    public void addCredit(double money){

    }


    public float CheckBalance() {
        return getAccountBalance();
    }




//    Setter and Getters
    public float getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(float accountBalance) {
        AccountBalance = accountBalance;
    }
}
