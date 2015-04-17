import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class TransactionLog{
	
	TableView<Log> tableView;
	private HBox hBox;
	private double screenWidth;
	private double screenHeight;
	private Stage stage;
	private FadeTransition ft;
	
	public TransactionLog(Stage st, double screenWidth, double screenHeight, ResultSet rs) {
		
		stage=st;
		this.screenHeight=screenHeight;
		this.screenWidth=screenWidth;
		
		hBox=new HBox(50);
		hBox.setPrefWidth(150);
		
		Button btn=new Button("Back");
		btn.setMinWidth(hBox.getPrefWidth());
		btn.setId("MyBtn");
		btn.setOnAction((event)->{
			stage.hide();
			stage.setScene(BankScreen.s);
			ft.play();
			stage.show();
		});
		
		hBox.getChildren().add(btn);
		
		tableView=new TableView<>();
		tableView.setPrefSize(screenWidth, screenHeight-50);
		
		TableColumn<Log, String> tableColumn=new TableColumn<Log, String>("Account ID");
		tableColumn.setCellValueFactory(new PropertyValueFactory<>("accountID"));
		tableColumn.setMinWidth(screenWidth/5);

		TableColumn<Log, String> tableColumn2=new TableColumn<Log, String>("Description");
		tableColumn2.setCellValueFactory(new PropertyValueFactory<>("description"));
		tableColumn2.setMinWidth(screenWidth/5);

		TableColumn<Log, String> tableColumn3=new TableColumn<Log, String>("Previous Balance");
		tableColumn3.setCellValueFactory(new PropertyValueFactory<>("oldBalance"));
		tableColumn3.setMinWidth(screenWidth/5);

		TableColumn<Log, String> tableColumn4=new TableColumn<Log, String>("New Balance");
		tableColumn4.setCellValueFactory(new PropertyValueFactory<>("newBalance"));
		tableColumn4.setMinWidth(screenWidth/5);

		TableColumn<Log, String> tableColumn5=new TableColumn<Log, String>("Date");
		tableColumn5.setCellValueFactory(new PropertyValueFactory<>("date"));
		tableColumn5.setMinWidth(screenWidth/5);
		
		tableView.getColumns().setAll(tableColumn,tableColumn2,tableColumn3,tableColumn4,tableColumn5);
		
		addLog(rs);
				
	}
	
	private void addLog(ResultSet rs) {
		ArrayList<Log> arrayList=new ArrayList<>();
		try {
			while(rs.next()) {
				arrayList.add(new Log(rs.getString("account"), rs.getString("description"), rs.getString("old_balance"), rs.getString("new_balance"), rs.getString("date")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObservableList<Log> observableList=FXCollections.observableArrayList(arrayList);
		tableView.setItems(observableList);
				
	}
	
	private GridPane createGrid() {
		GridPane gridPane =new GridPane();
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(5,10,5,10));
		gridPane.setStyle("-fx-background-image:null");
		gridPane.setMaxSize(screenWidth, screenHeight);
		gridPane.add(tableView,0,0);
		gridPane.add(hBox,0,1);
		
		return gridPane;
	}
	
	public void showLog() {
		ft=new FadeTransition(Duration.seconds(4),createGrid());
		ft.setFromValue(1.0);
		ft.setToValue(0.0);
		
		Scene s=new Scene(createGrid(),screenWidth,screenHeight);
		s.getStylesheets().add("styles.css");
		ft.play();
		stage.hide();
		stage.setScene(s);
		stage.show();
	}

	
}
