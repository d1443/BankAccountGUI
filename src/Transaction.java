import java.time.LocalDateTime;

public class Transaction {
	
	 LocalDateTime dt ;
	 double amount;
	 String description;
	 
	public Transaction(LocalDateTime transactionTime, double amount, String description)
	{
		this.dt = transactionTime;
		this.amount = amount;
		this.description = description;
				
	}
	
	public Transaction(double amount, String description)
	{
		this.amount = amount;
		this.description = description;
	}
	
	public String getDescription()
	{
		return this.description;
	}
	
}
