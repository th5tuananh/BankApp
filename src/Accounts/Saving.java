package Accounts;

/**
 * Created by Mitra on 1/27/2015.
 */
public class Saving extends Account{


    public Saving(int number, double balance){
        super(number, balance);
    }


    public String toString(){
        String s;
        s = "Saving: " + super.toString();
        return s;
    }

}
