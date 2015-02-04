package Accounts;


public abstract class Account {
//    variables to be used

    public Account(){
        // constructor
    }

    // this is abstract since the different accounts can decide on how
    // they will all add money to the account
    public abstract void addCredit(double money);

    public abstract float CheckBalance();


}
