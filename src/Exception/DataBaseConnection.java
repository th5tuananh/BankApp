package Exception;

public class DataBaseConnection extends Exception{

    public DataBaseConnection(){ super("NOt connected to the database"); }

    public DataBaseConnection(String message){ super(message); }

}
