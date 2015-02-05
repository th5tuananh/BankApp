package Accounts;

/**
 * Created by Mitra on 1/27/2015.
 */
public class Checking extends Account {

    private int numCheques;

    public Checking (int number, double balance, int cheques){
        super(number , balance);
        numCheques = cheques;
    }

}
