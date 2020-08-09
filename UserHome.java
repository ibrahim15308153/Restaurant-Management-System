import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

class UserHome extends JFrame implements ActionListener
{
	private JLabel labelWelcome, labelName, labelNumber, labelPhone;
	private JButton buttonLogout, buttonfood;
	private JPanel panel;
	public String name,password,accountNumber,phone;
	
	public UserHome(String name,String password, String accountNumber,String phone)
	{
		super("User Home Window");
		
		this.setSize(1200, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		labelWelcome = new JLabel("Welcome to My Food Paradise");
		labelWelcome.setBounds(200, 50, 200, 30);
		panel.add(labelWelcome);
		
		labelName = new JLabel("Name	:	"+name);
		labelName.setBounds(200, 100, 200, 30);
		panel.add(labelName);
		
		labelNumber = new JLabel("Accout Number	:	"+accountNumber);
		labelNumber.setBounds(200, 150, 200, 30);
		panel.add(labelNumber);
		
		labelPhone = new JLabel("Phone	:	"+phone);
		labelPhone.setBounds(200, 200, 200, 30);
		panel.add(labelPhone);
		
		buttonfood = new JButton("Food");
		buttonfood.setBounds(100, 250, 80, 30);
		buttonfood.addActionListener(this);
		panel.add(buttonfood);
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(280, 250, 100, 30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);
		
		this.add(panel);
		
	}
	public void actionPerformed(ActionEvent ae){
		String buttonClicked = ae.getActionCommand();
		if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonfood.getText()))
		{
			UserFoodTable uft=new UserFoodTable();
			uft.setVisible(true);
			this.setVisible(false);
		}
	}
}