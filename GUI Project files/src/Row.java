import javafx.beans.property.SimpleStringProperty;


public class Row {
	
	private final SimpleStringProperty bcid;
	private final SimpleStringProperty accounttype;
	private final SimpleStringProperty balance;
	
	public Row(String bcid, String accounttype, String balance) {
		
		this.bcid = new SimpleStringProperty(bcid);
		this.accounttype = new SimpleStringProperty(accounttype);
		this.balance = new SimpleStringProperty(balance);
	}

	public String getBcid() {
		return bcid.get();
	}

	public String getAccounttype() {
		return accounttype.get();
	}

	public String getBalance() {
		return balance.get();
	}

	
	
	

}
