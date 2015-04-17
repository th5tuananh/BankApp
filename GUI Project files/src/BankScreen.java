
import java.sql.ResultSet;
import java.util.ArrayList;


import java.util.HashMap;

import DAccess.DBConnection;
import DAccess.DBaccount;
import javafx.animation.FadeTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;


public class BankScreen{
	
	
	
	public static Scene scene;
	private VBox vbox=null;
	private Stage stage=null;
	private double screenWidth;
	private double screenHeight;
	private TableView<Row> tableView;
	private DBaccount dbaccount;
	private DBConnection dbConnection;
	private HashMap<Integer, TransactionLog> hashMap;
	public static Scene s;
	public static Pane root;
	
	public BankScreen(Stage stage,DBaccount dbaccount,double screenWidth,double screenHeight)
	{
		
		this.stage=stage;
		this.dbaccount=dbaccount;
		this.screenWidth=screenWidth;
		this.screenHeight=screenHeight;
		
		hashMap=new HashMap<>();

		dbConnection=new DBConnection();
		dbaccount= new DBaccount();
		
		//create a table and column headings
		tableView=new TableView<>();
		tableView.setEditable(false);
		tableView.requestFocus();
		tableView.setPrefSize(screenWidth, screenHeight-50);

		TableColumn<Row, String> col2=new TableColumn<>("Account Number");
		col2.setCellValueFactory(new PropertyValueFactory<>("bcid"));
		col2.setMinWidth(screenWidth/3);
		
		TableColumn<Row, String> col3=new TableColumn<>("Account Type");
		col3.setCellValueFactory(new PropertyValueFactory<>("accounttype"));
		col3.setMinWidth(screenWidth/3);
		
		TableColumn<Row, String> col4=new TableColumn<>("Balance");
		col4.setCellValueFactory(new PropertyValueFactory<>("balance"));
		col4.setMinWidth(screenWidth/3);
	
			
		tableView.getColumns().setAll(col2,col3,col4);
		
		//layout for buttons/mainscreen
 		vbox=new VBox(30);
 		vbox.setAlignment(Pos.CENTER);
 		vbox.setPrefSize(200,600);
 		vbox.setLayoutX(screenWidth/2-100);
 		vbox.setLayoutY(screenHeight/2-300);

 		Button b=new Button("Logout");
 		b.setMaxSize(100, 50);
 		b.setId("MyBtn");
 		b.setLayoutX((screenWidth/2));
 		b.setLayoutY(screenHeight-120);
 		b.setOnAction((event)->{
 			hashMap.clear();
 		});
 		
 		root = new Pane();
 		root.setPrefSize(screenWidth, screenHeight);

 		//adding elements to root element
 		root.getChildren().addAll(vbox);
 		
 		//load css file to style elements such as buttons
 		scene=new Scene(root,screenWidth,screenHeight);
 		scene.getStylesheets().add("styles.css");

	    
	    //create main screen dimensions and display   
 		
	    stage.setTitle("Bank Application");
	    stage.setMaximized(true);
	    stage.setMinHeight(720);
	    stage.setMinWidth(1024);
		stage.setScene(scene);
		
	}
	
	public void show(){
//		FadeTransition ft=new FadeTransition(Duration.millis(700),root);
//		ft.setFromValue(1.0);
//		ft.setToValue(0.0);
//		stage.hide();
//		ft.play();
		stage.show();
	}
	
	public void addButtonsToLayout(ArrayList<Button> buttonCollection){
		//add all buttons to Tilepane layout
		vbox.getChildren().addAll(buttonCollection);
	}
	
	
	public void showTable() {

		GridPane gridPane=new GridPane();
		gridPane.setVgap(5);
		gridPane.setMaxSize(screenWidth, screenHeight);
		gridPane.setPadding(new Insets(5,10,5,10));
		gridPane.setStyle("-fx-background-image:null");
		
		HBox hBox=new HBox((screenWidth-20)/3);
		hBox.setPadding(new Insets(0, 20, 0, 0));
		hBox.setPrefWidth(150);
		
		gridPane.add(tableView,0,0);
		gridPane.add(hBox, 0, 1);
		gridPane.requestFocus();

		Button btn=new Button("Back");
		btn.setMinWidth(hBox.getPrefWidth());
		btn.setId("MyBtn");
		btn.setOnAction((event)->{
			FadeTransition ft=new FadeTransition(Duration.seconds(5),gridPane);
			ft.setFromValue(1.0);
			ft.setToValue(0.0);		
			stage.hide();
			ft.play();
			stage.setScene(scene);
			stage.show();
			gridPane.getChildren().clear();
		});
		
		Button b=new Button("Account Log");
		b.setMinWidth(hBox.getPrefWidth());
		b.setId("MyBtn");
		b.setOnAction((event)->{
			TransactionLog tl = null;
			Row r=tableView.getSelectionModel().getSelectedItem();
			if(r==null)
				return;
			System.out.println(r.getBcid());
			try {
				int id=Integer.parseInt(r.getBcid().toString());
				if(!hashMap.containsKey(id)) {
					ResultSet rs=dbConnection.getAccountTransactionLog(id);
					tl=new TransactionLog(stage,screenWidth,screenHeight,rs);
					hashMap.put(id, tl);
				}
				else {
					tl=hashMap.get(id);
				}

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			FadeTransition ft=new FadeTransition(Duration.millis(5),gridPane);
			ft.setFromValue(0.0);
			ft.setToValue(1.0);	
			ft.play();
			stage.hide();
			tl.showLog();
//			stage.show();
		});
		
		Button transBtn=new Button("Transfer");
		transBtn.setMinWidth(hBox.getPrefWidth());
		transBtn.setId("MyBtn");
		transBtn.setOnAction((event)->{
			Transfer transfer=new Transfer(screenWidth, screenHeight,stage,dbaccount);
			transfer.setScene(s);
		});
		
		hBox.getChildren().addAll(btn,b,transBtn);
		

		s=new Scene(gridPane,screenWidth,screenHeight);
		s.getStylesheets().add("styles.css");
		
		
		stage.setMaximized(true);
		stage.hide();
		stage.setScene(s);
		stage.show();
	}


	public TableView<Row> getTableView() {
		return tableView;
	}

}
