import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application{
	
	private static LoginScreen main;

	public static void main(String[] args) {
		
		Application.launch(args);
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		main=new LoginScreen(arg0);
		main.show();
	}

}
