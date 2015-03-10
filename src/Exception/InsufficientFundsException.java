package Exception;


public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException() {
    	super("Not enough funds to permit operation.");
    }

    public InsufficientFundsException(String message){
    	super(message);
    }


}