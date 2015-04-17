
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import DAccess.DBaccount;
import javafx.animation.FadeTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Transfer {
	
	private ComboBox<String> comboBox2;
	private DBaccount dbAccount;
	private Stage stage;
	private Scene s;
	private ArrayList<String> bcid;
	
	public Transfer(double screenWidth,double screenHeight,Stage stage, DBaccount dbaccount) {
		
		this.stage=stage;
		this.dbAccount=dbaccount;
		comboBox2=new ComboBox<>();
		
		GridPane root=new GridPane();
		root.setAlignment(Pos.CENTER);
		root.setMaxSize(400, 400);
		root.setHgap(20);
		root.setVgap(20);
		
		Text text=new Text("From Account:");
		text.setFont(new Font(18));
		Text text2=new Text("To Account:");
		text2.setFont(new Font(18));
		
		Text text3=new Text("Transfer amount:");
		text3.setFont(new Font(16));
		
		TextField textField=new TextField();
		textField.setPromptText("Amount");
		textField.lengthProperty().addListener(new ChangeListener<Number>() {

			 @Override
		     public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {              
		           
		           if(newValue.intValue() > oldValue.intValue()){
		               char ch = textField.getText().charAt(oldValue.intValue());
		               //Check if the new character is the number or other's
		               if(!(ch >= '0' && ch <= '9' )){       
		                 
		                    //if it's not number then just setText to previous one
		                    textField.setText(textField.getText().substring(0,textField.getText().length()-1)); 
		               }
		          }
		     }			
		});
	
		
		bcid=new ArrayList<>();
		
		Map<String, ObservableList<Row>> map=LoginScreen.getHashMap();
		Iterator<Entry<String, ObservableList<Row>>> it=map.entrySet().iterator();
		
		while(it.hasNext()) {
			ListIterator<Row> listIt=it.next().getValue().listIterator();
			while(listIt.hasNext()) {
				Row r=listIt.next();
				bcid.add(r.getBcid());
			}
		}
		
		
		ComboBox<String> comboBox=new ComboBox<>();
		comboBox.setMaxWidth(Double.MAX_VALUE);
		comboBox.getItems().addAll(bcid);
		comboBox.setOnAction((event)->{
			comboBox2.getItems().removeAll(comboBox2.getItems());
			ArrayList<String> newBcid=new ArrayList<>(bcid);
			String s =comboBox.getSelectionModel().getSelectedItem();
			newBcid.remove(s);
			comboBox2.setMaxWidth(Double.MAX_VALUE);
			comboBox2.getItems().addAll(newBcid);				
		});
		
		Image img=new Image(this.getClass().getResourceAsStream("images/Arrow Right.png"),50,20,true,true);
		
		Button btn=new Button("Transfer",new ImageView(img));
		btn.setId("MyBtn");
		btn.setMaxWidth(Double.MAX_VALUE);
		btn.setContentDisplay(ContentDisplay.RIGHT);
		btn.setOnAction((event)->{
			String src=comboBox.getSelectionModel().getSelectedItem();
			String dest=comboBox2.getSelectionModel().getSelectedItem();
			String st=textField.getText();
			System.out.println("src:"+src+"dest:"+dest+"amt:"+st);
			double transfer = 0;
			int from = 0,to = 0;
			try {
				transfer=Double.parseDouble(st);
				from=Integer.parseInt(src);
				to=Integer.parseInt(dest);
			}catch(java.lang.NumberFormatException ne) {
				Alert alert = new Alert(AlertType.ERROR);
				
				alert.setTitle("Transfer Error");
				alert.setHeaderText("Invalid or incomplete data");
				alert.setContentText("You must enter an amount greater than 0 to transfer. You must also select an account ID to send to and from.");
				alert.showAndWait();
				return;
			}
			transfer(from,to,transfer);
			textField.clear();
			textField.setPromptText("Numbers Only");
		});
		
		Button b=new Button("Back");
		b.setMaxWidth(Double.MAX_VALUE);
		b.setId("MyBtn");
		b.setOnAction((event)->{
			FadeTransition ft=new FadeTransition(Duration.seconds(5),root);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);
			ft.setDelay(Duration.INDEFINITE);
			update();
			ft.play();
			stage.hide();
			stage.setScene(BankScreen.s);
			stage.show();
		});
		
		Button button=new Button("Transfer to another user");
		button.setMaxWidth(Double.MAX_VALUE);
		button.setId("MyBtn");
		button.setOnAction((event)->{
			transferDialog(dbaccount);
		});
		
		VBox vBox=new VBox(20);
		vBox.getChildren().addAll(text,text2,text3);
		
		VBox vBox2=new VBox(20);
		vBox2.getChildren().addAll(comboBox,comboBox2,textField);
		
		VBox vBox3=new VBox(20);
		vBox3.getChildren().addAll(btn,button,b);
		
		root.add(vBox, 0, 0);
		root.add(vBox2, 1, 0);
		root.add(vBox3, 0, 2,2,1);
		
		Scene scene=new Scene(root,screenWidth,screenHeight);
		scene.getStylesheets().add("styles.css");
		stage.hide();
		stage.setScene(scene);
		stage.show();
	}
	
	private void transfer(int from, int to, double transfer) {
		
		int result=0;
		try {
			result=dbAccount.transfer(from, to, transfer);
			System.out.println("Transfer:"+result);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(result==0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Transfer Error");
			alert.setHeaderText("Insufficient Funds");
			alert.setContentText("You cannot transfer more than your current balance");

			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Transfer Result");
			alert.setHeaderText("Transfer Successful!");
			alert.setContentText("You have succcessfully transfered $"+transfer+" FROM account :"+from+" TO account: "+to);

			alert.showAndWait();
		}
			
	}
	
	public void show() {
		
	}

	public void setScene(Scene s) {
		this.s=s;
	}
	
	private void transferDialog(DBaccount dbaccount2) {
		Dialog<Integer> dialog = new Dialog<>();
		dialog.setTitle("Transfer");
		dialog.setHeaderText("Transfer money to another account.");
		ButtonType loginButtonType = new ButtonType("Transfer", ButtonData.OK_DONE);
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, cancel);
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);

//		TextField acount1 = new TextField();
//		acount1.setPromptText("Account source ID");
		ComboBox<String> cb=new ComboBox<>();
		cb.setMaxWidth(Double.MAX_VALUE);
		cb.getItems().addAll(bcid);
		TextField account2 = new TextField();
		account2.setPromptText("Account destination ID");
		TextField amount = new TextField();
		amount.setPromptText("Amount");

		grid.add(new Text("FROM:"), 0, 0);
		grid.add(cb, 1, 0);
		grid.add(new Text("TO:"), 0, 1);
		grid.add(account2, 1, 1);
		grid.add(new Text("Amount:"), 0, 2);
		grid.add(amount, 1, 2);
		dialog.getDialogPane().setContent(grid);

		
		dialog.setResultConverter(dialogButton->{
			try {
			int id=Integer.parseInt(cb.getSelectionModel().getSelectedItem());
			int id2=Integer.parseInt(account2.getText());
			int amt=Integer.parseInt(amount.getText());
				if(dialogButton==loginButtonType) 
					return dbaccount2.transfer(id, id2, amt);
				else {
					dialog.close();
//					return 2;
				}
			} catch (Exception e) {
//				transferAlert();
				}

			return 0;
		});
		
		Optional<Integer> result=dialog.showAndWait();
		int r=result.get();
		if(r==0)
			transferAlert();
		else if(r==1)
			transferSuccess();
	}
	
	private void transferAlert() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Transfer Error");
		alert.setHeaderText("An error occurred!");
		alert.setContentText("You must enter valid account id's. The amount to be sent must not be more than your current balance.");

		alert.showAndWait();
	}
	
	private void transferSuccess() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Transfer Result");
		alert.setHeaderText("Transfer Successful!");
		alert.setContentText("You have succcessfully transfered money to your friends account");

		alert.showAndWait();
	}

	private void update(){
		Map<String, ObservableList<Row>> map=LoginScreen.getHashMap();
		Iterator it=map.entrySet().iterator();
		ResultSet set=LoginScreen.getResult();
		try {
			while(set.next()){
                Entry pair=(Entry)it.next();
                MyService service=new MyService(LoginScreen.getUserName(),set.getString("bankname"),new DBaccount());
				LoginScreen.getBankscreen().getTableView().setItems(service.getValue());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
