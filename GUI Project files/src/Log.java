import javafx.beans.property.SimpleStringProperty;


public class Log {
	
	private final SimpleStringProperty accountID;
	private final SimpleStringProperty description;
	private final SimpleStringProperty oldBalance;
	private final SimpleStringProperty newBalance;
	private final SimpleStringProperty date;
	
	public Log(String accountID,String description, String oldBalance,String newBalance, String date) {
		this.accountID = new SimpleStringProperty(accountID);
		this.description = new SimpleStringProperty(description);
		this.oldBalance = new SimpleStringProperty(oldBalance);
		this.newBalance = new SimpleStringProperty(newBalance);
		this.date = new SimpleStringProperty(date);
	}

	public String getAccountID() {
		return accountID.get();
	}

	public String getDescription() {
		return description.get();
	}

	public String getOldBalance() {
		return oldBalance.get();
	}

	public String getNewBalance() {
		return newBalance.get();
	}

	public String getDate() {
		return date.get();
	}

}
