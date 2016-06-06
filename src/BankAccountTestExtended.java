import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class BankAccountTestExtended extends junit.framework.TestCase
{

    BankAccount chequing1;
    BankAccount chequing2;
    BankAccount chequing3;
    
    Transaction t1;
    
  
    
    protected void setUp()
    {
    	System.out.println("setup");
        chequing1 = new BankAccount("chequing1");
        chequing2 = new BankAccount("chequing2", 100);
        chequing3 = new BankAccount("chequing3", 100, 2.00, 5.0);        
    }
        
    public void testAccessors()
    {
	assertEquals("chequing2",  chequing2.getAccountNumber());
	assertEquals(100.0,  chequing2.getBalance(), 0);
	assertEquals(5.0,  chequing3.getAnnualInterestRate(), 0);
	assertEquals(2.0,  chequing3.getWithdrawalFee(), 0);
    }
    
    public void testMutators()
    {
    //test for setters
	chequing1.setWithdrawalFee(3.0);
	chequing1.setAnnualInterestRate(4.0);
	assertEquals(3.0,  chequing1.getWithdrawalFee(), 0);
	assertEquals(4.0,  chequing1.getAnnualInterestRate(), 0);
    }
    
    public void testDeposits()
    {                	
	//test various deposits and withdrawals
	chequing1.setWithdrawalFee(3.0);
	
	chequing1.deposit(500, "deposit 1");
	assertEquals(500.0,  chequing1.getBalance(), 0);
		
	chequing1.withdraw(200, "withdrawal 1");
	assertEquals(297.0,  chequing1.getBalance(), 0);		
	assertEquals(false,  chequing1.isOverDrawn());
				
	chequing1.withdraw(300, "withdrawal 2");
	assertEquals(-6.0,  chequing1.getBalance(), 0);
		
	chequing1.isOverDrawn();
	assertEquals(true,  chequing1.isOverDrawn());
	assertEquals("BankAccount chequing1: (6.0)", chequing1.toString());
    }
    
    public void testTransactions()
    {
    	chequing1.setWithdrawalFee(3.0);
    	
        LocalDate d1 = LocalDate.of(2000, 12, 31);
        LocalTime t1 = LocalTime.of(14, 15, 30);    	
    	LocalDateTime dt1 = LocalDateTime.of(d1, t1);
    	chequing1.deposit(dt1, 600, "deposit 1");
    	    	
        LocalDate d2 = LocalDate.of(2001, 01, 02);
        LocalTime t2 = LocalTime.of(14, 15, 30);    	
    	LocalDateTime dt2 = LocalDateTime.of(d2, t2);
    	chequing1.withdraw(dt2, 200, "withdrawal 1");
    	
        LocalDate d3 = LocalDate.of(2001, 01, 01);
        LocalTime t3 = LocalTime.of(14, 15, 30);    	
    	LocalDateTime dt3 = LocalDateTime.of(d3, t3);
    	chequing1.withdraw(dt3, 300, "withdrawal 2");

        LocalDate d4 = LocalDate.of(2001, 01, 03);
        LocalTime t4 = LocalTime.of(14, 15, 30);    	
    	LocalDateTime dt4 = LocalDateTime.of(d4, t4);
    	chequing1.deposit(dt4, 100, "deposit 2");
    	
    	//order should be: ["deposit 1"], ["withdrawal 2"], ["withdrawal 1"], ["deposit 2"]
    	
    	ArrayList<Transaction> trans1 = chequing1.getTransactions(null, null);
    	assertEquals(4, trans1.size());
    	assertEquals(trans1.get(2).getDescription(), "withdrawal 1");

    	ArrayList<Transaction> trans2 = chequing1.getTransactions(dt2, null);
    	assertEquals(2, trans2.size());
    	assertEquals(trans2.get(0).getDescription(), "withdrawal 1");

    	ArrayList<Transaction> trans3 = chequing1.getTransactions(null, dt3);
    	assertEquals(2, trans3.size());
    	assertEquals(trans3.get(1).getDescription(), "withdrawal 2");

    	ArrayList<Transaction> trans4 = chequing1.getTransactions(dt3, dt2);
    	assertEquals(2, trans4.size());    	    
    }
   
}

