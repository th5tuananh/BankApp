package Exception;

public class TooLargeWithdrawalException extends Exception{

    public TooLargeWithdrawalException() {
    	super("Limit exceeded.");
    }

    public TooLargeWithdrawalException(String message){
    	super(message);
    }
}