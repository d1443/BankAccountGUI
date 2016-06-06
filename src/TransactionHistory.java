import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class TransactionHistory {

	private JFrame frame;
	private JLabel lblTransactions;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionHistory window = new TransactionHistory();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TransactionHistory() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		
		
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
	    lblTransactions = new JLabel("New label");
		lblTransactions.setBounds(10, 10, 414 , 241);
		frame.getContentPane().add(lblTransactions);
	}
	
}
