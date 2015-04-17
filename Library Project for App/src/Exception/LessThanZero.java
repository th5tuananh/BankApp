package Exception;

public class LessThanZero extends Exception {

    public LessThanZero (){
        super("Money cannot go below 0");
    }

    public LessThanZero (String message){
        super(message);
    }


}
