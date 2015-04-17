import java.sql.ResultSet;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import DAccess.DBaccount;


public class MyService extends Service<ObservableList<Row>>{
	
	private String userName;
	private String bankName;
	private DBaccount dbaccount;
	
	
	public MyService(String userName, String bankName,DBaccount dbaccount) {
		
		this.userName = userName;
		this.bankName = bankName;
		this.dbaccount=dbaccount;
	}
	

	@Override
	protected Task<ObservableList<Row>> createTask() {
		
		ArrayList<Row> arrayList=new ArrayList<>();
		
		return new Task<ObservableList<Row>>() {
			
			@Override
			protected ObservableList<Row> call(){

			try {
				ResultSet resultSet=dbaccount.clientBankAccounts(userName, bankName);
			
			while(resultSet.next()) {
				Thread.sleep(100);
				arrayList.add(new Row(resultSet.getString("bcid"),resultSet.getString("accounttype"),resultSet.getString("balance")));
			}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
				ObservableList<Row> observableList=FXCollections.observableArrayList(arrayList);
				
				return observableList;
			}//end call method
		
		};//end new task
		
	}//end create task
	
}
