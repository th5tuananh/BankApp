import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import DAccess.*;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginScreen{
	
	public static Stage stage;
	
	private double screenWidth;
	private double screenHeight;

	private TextField usernameField;
	private PasswordField passwordField;
	private VBox vbox;
	private HBox hbox;
	private Pane root;
	private Scene scene;
	private Button button;
	private ArrayList<Button> buttonCollection;
	private ArrayList<String> bankNames;

	public static ResultSet getResult() {
		return result;
	}

	private static ResultSet result;

	public static String getUserName() {
		return userName;
	}

	private static String userName=null;
	private String password=null;
	private DBclient client=null;

	public static BankScreen getBankscreen() {
		return bankscreen;
	}

	private static BankScreen bankscreen=null;
	private DBaccount dbaccount;
	private static Map<String, ObservableList<Row>> hashMap;
	
	/**
	 * @param stage
	 */
	public LoginScreen(Stage stage) {	

//		get screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
 		screenWidth= screenSize.getWidth();
 		screenHeight = screenSize.getHeight();
 		
 		dbaccount=new DBaccount();
 		
 		hashMap=new HashMap<>();
 		
		LoginScreen.stage=stage;
		
//		bank icons at the top
		hbox=new HBox(80);
		hbox.setAlignment(Pos.CENTER);
		
		bankscreen=new BankScreen(new Stage(),dbaccount,screenWidth,screenHeight);
		
//		collection of bank names
		buttonCollection=new ArrayList<Button>();
		bankNames=new ArrayList<String>();
		bankNames.add("Scotia Bank");
		bankNames.add("Republic Bank");
		bankNames.add("Royal Bank");
		addBanksLogo();
		
		client=new DBclient();
		
//		layout for textfields and buttons for login
		vbox=new VBox(60);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPrefSize(400, 400);
		vbox.setLayoutX(screenWidth/2-200);
		vbox.setLayoutY(screenHeight/2-200);
		vbox.setFocusTraversable(true);
		
		usernameField=new TextField();
		usernameField.setFocusTraversable(true);
		usernameField.setPromptText("Username");
		usernameField.setScaleY(1.5);
		
		
		passwordField=new PasswordField();
		passwordField.setFocusTraversable(true);
		passwordField.setPromptText("Password");
		passwordField.setScaleY(1.5);
		
		button=new Button("Login");
		button.setFont(Font.font("Times New Roman"));
		button.setFocusTraversable(true);
		button.setDefaultButton(true);
		button.setScaleY(1.25);
		button.setMaxWidth(Double.MAX_VALUE);
		
		
		
		//Login button event handler
		button.setOnAction((event)->{
				validateLogin();		
		});
		
		
		vbox.getChildren().addAll(hbox,usernameField,passwordField,button);
		
		root=new Pane();
		root.getChildren().addAll(vbox);
		
		scene=new Scene(root);
		scene.getStylesheets().add("styles.css");
		
		
		LoginScreen.stage.setTitle("Bank Application");
		LoginScreen.stage.setScene(scene);
		LoginScreen.stage.setMaximized(true);
	}
	
	public void show(){
		LoginScreen.stage.show();
	}
	
	private void validateLogin(){
		System.out.println("validate");
		
		this.userName=this.usernameField.getText();
		this.password=this.passwordField.getText();
		
		System.out.println(userName+"\t"+password);
		if(this.userName.equals("") || this.password.equals("")){
		    Alert alert = new Alert(AlertType.WARNING);
		    alert.setTitle("Warning Dialog");
		    alert.setHeaderText("Username and Password required!");
		    alert.showAndWait();
		    usernameField.clear();
		    passwordField.clear();
		    usernameField.setPromptText("Username");
		    passwordField.setPromptText("Password");
		} else
			try {
				if(!client.IsAvailable(this.userName, this.password)){ 
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error Dialog");
					alert.setHeaderText("Invalid username or password!");
					alert.setContentText("Please enter a valid username and password. Usernames and passwords are case sensitive.");

					alert.showAndWait();
					
				    usernameField.clear();
				    passwordField.clear();
				    usernameField.setPromptText("Username");
				    passwordField.setPromptText("Password");
				}
				else{	
					this.createBankButtons(userName);
					FadeTransition ft=new FadeTransition(Duration.millis(1500),root);
					ft.setFromValue(1.0);
					ft.setToValue(0.0);
					stage.hide();
					ft.play();
					
					bankscreen.show();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}

	
	private void createBankButtons(String userName){
		
		try {

		result=client.ClientBanks(userName);
		
		while(result.next()){
			startService(result.getString("bankname"));
			createButton(result.getString("bankname"));
		}//end while
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		bankscreen.addButtonsToLayout(buttonCollection);

	}
	
	
	private void createButton(String bankName){
		
		
		Image img=new Image(this.getClass().getResourceAsStream("images/"+bankName+".png"),60,40,true,true);
		Button btn=new Button(bankName,new ImageView(img));
		btn.setContentDisplay(ContentDisplay.LEFT);
		btn.setId(bankName);
		btn.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
		btn.setOnAction((event) ->{
			bankscreen.getTableView().setItems(hashMap.get(bankName));
			FadeTransition ft=new FadeTransition(Duration.millis(3000),root);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.setDuration(Duration.ONE);
			ft.play();
			bankscreen.showTable();
		});
		buttonCollection.add(btn);
	}
	
	private void addBanksLogo(){
		bankNames.forEach((name)->{
			Image img=new Image(this.getClass().getResourceAsStream("images/"+name+".png"), 50, 50, true, true);
			ImageView view=new ImageView(img);
			hbox.getChildren().add(view);
		});
		
	}
	
	private void startService(String bankName) {
		MyService myService=new MyService(userName, bankName,dbaccount);
		System.out.println("service started");
		myService.start();
		myService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			
			@Override
			public void handle(WorkerStateEvent arg0) {
				System.out.println("service finished");
				hashMap.put(bankName, myService.getValue());
			}
		});
	}

	public static Map<String, ObservableList<Row>> getHashMap() {
		return hashMap;
	}

}
	
