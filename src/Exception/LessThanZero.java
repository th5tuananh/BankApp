package Exception;

/**
 * Created by Mitra on 1/27/2015.
 */
public class LessThanZero extends Exception {

    public LessThanZero (){
        super("Money cannot go below 0");
    }

    public LessThanZero (String message){
        super(message);
    }


}
