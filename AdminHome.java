import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class AdminHome extends JFrame implements ActionListener
{
	private JButton foodButton,updateButton,deleteButton,logoutButton,addUserButton,showUserButton;
	public JLabel welcomeLabel;
	public String accountNumber;
	private JPanel panel;
	
	public AdminHome(String name,String password,String accountNumber,String phone)
	{
		super("Admin Home Window");
		this.setSize(1200, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		foodButton=new JButton("Foods");
		foodButton.setBounds(450,150,80,30);
		foodButton.addActionListener(this);
		panel.add(foodButton);
		
		addUserButton=new JButton("Add Users");
		addUserButton.setBounds(550,150,120,30);
		addUserButton.addActionListener(this);
		panel.add(addUserButton);
		
		showUserButton=new JButton("Show Users");
		showUserButton.setBounds(700,150,120,30);
		showUserButton.addActionListener(this);
		panel.add(showUserButton);
		
		updateButton=new JButton("Update");
		updateButton.setBounds(100,150,100,30);
		updateButton.addActionListener(this);
		panel.add(updateButton);
		
		deleteButton=new JButton("Delete");
		deleteButton.setBounds(220,150,100,30);
		deleteButton.addActionListener(this);
		panel.add(deleteButton);
		
		logoutButton=new JButton("log out");
		logoutButton.setBounds(340,150,100,30);
		logoutButton.addActionListener(this);
		panel.add(logoutButton);
		
		welcomeLabel=new JLabel("Welcome  "+name);
		welcomeLabel.setBounds(230,50,200,30);
		panel.add(welcomeLabel);
		
		this.add(panel);
		this.accountNumber=accountNumber;
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(updateButton.getText()))
		{
			CreateItem ci = new CreateItem();
			ci.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(deleteButton.getText()))
		{
			DeleteItem del = new DeleteItem();
			del.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(foodButton.getText()))
		{
			ShowTableFood stf=new ShowTableFood();
			stf.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(addUserButton.getText()))
		{
			
			AddUser au=new AddUser();
			au.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(showUserButton.getText()))
		{
			ShowTableUsers stu=new ShowTableUsers();
			stu.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(logoutButton.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
	}
}	