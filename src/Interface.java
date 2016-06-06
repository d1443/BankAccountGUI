import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JRadioButton;
import java.awt.Component;
import java.awt.Desktop.Action;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Interface extends JFrame {

	private JPanel contentPane;
	private JTextField txtAmount;
	private JButton btnFinish;
	private JButton btnNewButton_1;
	JRadioButton rdbtnChequing;
	JRadioButton rdbtnSaving;
	JRadioButton rdbtnWithdrawal;
	JRadioButton rdbtnDeposit;
	JLabel lblBalance;
	private double amount;
	public BankAccount Chequing = new BankAccount("Chequing");
	public BankAccount Saving = new BankAccount("Saving");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface frame = new Interface();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Interface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 270, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		rdbtnChequing = new JRadioButton("Chequing");
		rdbtnChequing.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				rdbtnChequing_focusGained(arg0);
			}
		});
		rdbtnChequing.setBounds(10, 32, 109, 23);
		contentPane.add(rdbtnChequing);
		
		rdbtnSaving = new JRadioButton("Saving");
		rdbtnSaving.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				rdbtnSaving_focusGained(arg0);
			}
		});
		rdbtnSaving.setBounds(139, 32, 109, 23);
		contentPane.add(rdbtnSaving);
		
		ButtonGroup accountType = new ButtonGroup();
		accountType.add(rdbtnChequing);
		accountType.add(rdbtnSaving);
		
		JLabel lblSelectAccountType = new JLabel("Select account type:");
		lblSelectAccountType.setBounds(10, 11, 126, 14);
		contentPane.add(lblSelectAccountType);
		
		JLabel lblSelectAction = new JLabel("Select action:");
		lblSelectAction.setBounds(10, 62, 126, 14);
		contentPane.add(lblSelectAction);
		
		rdbtnDeposit = new JRadioButton("Deposit");
		rdbtnDeposit.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent arg0) {
				rdbtnDeposit_focusGained(arg0);
			}
		});
		rdbtnDeposit.setBounds(10, 83, 109, 23);
		contentPane.add(rdbtnDeposit);
		
		rdbtnWithdrawal = new JRadioButton("Withdrawal");
		rdbtnWithdrawal.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				rdbtnWithdrawal_focusGained(e);
			}
		});
		rdbtnWithdrawal.setBounds(139, 83, 109, 23);
		contentPane.add(rdbtnWithdrawal);
		
		ButtonGroup action = new ButtonGroup();
		action.add(rdbtnDeposit);
		action.add(rdbtnWithdrawal);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(139, 113, 86, 20);
		contentPane.add(txtAmount);
		txtAmount.setColumns(10);
		
		JLabel lblEnterAmount = new JLabel("Enter Amount: ");
		lblEnterAmount.setBounds(10, 113, 86, 14);
		contentPane.add(lblEnterAmount);
		
		btnFinish = new JButton("Complete");
		btnFinish.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				btnFinish_mouseClicked(e);
			}
		});
	

		
		btnFinish.setBounds(10, 166, 112, 23);
		contentPane.add(btnFinish);
		
		btnNewButton_1 = new JButton("New button");
		btnNewButton_1.setBounds(139, 166, 89, 23);
		contentPane.add(btnNewButton_1);
		
		lblBalance = new JLabel("Balance: ");
		lblBalance.setBounds(10, 141, 165, 14);
		contentPane.add(lblBalance);
		
	}
	protected void rdbtnDeposit_focusGained(FocusEvent arg0) {
		//action = "deposit";
		btnFinish.setText("Deposit");
		
	}
	
	protected void rdbtnWithdrawal_focusGained(FocusEvent e) {
		//action = "withdrawal";
		btnFinish.setText("Withdrawal");
	}
	
	protected void btnFinish_mouseClicked(MouseEvent e)
	{
		BankAccount account;
		String action = "";
		this.amount = Double.parseDouble(txtAmount.getText());
		if (this.rdbtnChequing.isSelected())
		{
			account = Chequing;
		}
		else 
		{
			account = Saving;
		}
		if (this.rdbtnDeposit.isSelected())
		{
			action = "deposit";
		}
		else if (this.rdbtnWithdrawal.isSelected())
		{
			action = "withdrawal";
		}
		
		
		preformAction(account,action);
		
	}
	public void preformAction(BankAccount account, String action)
	{
		if (account == Chequing)
		{
			if (action == "deposit")
			{
				Chequing.deposit(amount);
			}
			else if(action == "withdrawal")
			{
				Chequing.withdraw(amount);
			}
		}	
		else if (account == Saving)
		{
			if (action == "deposit")
			{
				Saving.deposit(amount);
			}
			else if(action == "withdrawal")
			{
				Saving.withdraw(amount);
			}
		}
		txtAmount.setText("");
		update(account);	
	}
		
	
	private void update(BankAccount account)
	{
		if (account != null)
		{
		lblBalance.setText("Balance: " + account.getBalance());
		}
	}
	protected void rdbtnChequing_focusGained(FocusEvent arg0)
	{
		update(Chequing);
	}
	
	protected void rdbtnSaving_focusGained(FocusEvent arg0)
	{
		update(Saving);
	}
}
