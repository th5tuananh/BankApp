package Accounts;
import Exception.*;

/**
 * Created by Mitra on 1/27/2015.
 */
public class Chequing extends Account {

    public Chequing(int number, double balance, int cheques){
        super(number , balance);
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        super.withdraw(amount);
    }

    public String toString(){
        String s = "Chequing :" + super.toString();
        return s;
    }
}
