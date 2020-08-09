import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Register extends JFrame implements ActionListener
{
	private JLabel nameLabel,passwordLabel,phoneLabel,accountNumberLabel;
	private JTextField nameTF,phoneTF,accountNumberTF;
	private JPasswordField passwordF;
	private JRadioButton adminButton,userButton;
	private JButton confirmButton,backButton,cancelButton;
	private ButtonGroup bg;
	private JPanel panel;
	
	public Register()
	{
		super("Registration Window");
		
		this.setSize(1200, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setLayout(null);
		
		nameLabel=new JLabel("Name	:");
		nameLabel.setBounds(100,50,100,25);
		panel.add(nameLabel);
		
		nameTF=new JTextField();
		nameTF.setBounds(200,50,100,25);
		panel.add(nameTF);
		
		passwordLabel=new JLabel("Password	:");
		passwordLabel.setBounds(100,75,100,25);
		panel.add(passwordLabel);
		
		passwordF=new JPasswordField();
		passwordF.setBounds(200,75,100,25);
		panel.add(passwordF);
		
		phoneLabel=new JLabel("Phone:	");
		phoneLabel.setBounds(100,100,100,25);
		panel.add(phoneLabel);
		
		phoneTF=new JTextField();
		phoneTF.setBounds(200,100,100,25);
		panel.add(phoneTF);
		
		accountNumberLabel=new JLabel("Account Number:	");
		accountNumberLabel.setBounds(100,125,100,25);
		panel.add(accountNumberLabel);
		
		accountNumberTF=new JTextField();
		accountNumberTF.setBounds(200,125,100,25);
		panel.add(accountNumberTF);
		
		adminButton=new JRadioButton("Admin");
		adminButton.setBounds(200,150,80,40);
		adminButton.addActionListener(this);
		panel.add(adminButton);
		
		userButton=new JRadioButton("User");
		userButton.setBounds(100,150,60,40);
		userButton.addActionListener(this);
		panel.add(userButton);
		
		confirmButton=new JButton("Confirm");
		confirmButton.setBounds(100,200,80,40);
		confirmButton.addActionListener(this);
		panel.add(confirmButton);
		
		cancelButton=new JButton("Cancel");
		cancelButton.setBounds(200,200,80,40);
		cancelButton.addActionListener(this);
		panel.add(cancelButton);
		
		backButton=new JButton("Back");
		backButton.setBounds(300,200,80,40);
		backButton.addActionListener(this);
		panel.add(backButton);
		
		bg = new ButtonGroup();
		bg.add(userButton);
		bg.add(adminButton);
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(confirmButton.getText()))
		{
			insertIntoDB();
			dispose();
		}
		else if(buttonClicked.equals(cancelButton.getText()))
		{
			System.exit(0);
		}
		else if(buttonClicked.equals(backButton.getText()))
		{
			dispose();
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
	}
	public void insertIntoDB()
	{
		
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "");
			Statement stm = con.createStatement();
			
			if(adminButton.isSelected())
		{
			String Adminquery = "INSERT INTO admin values ('"+nameTF.getText()+"','"+passwordF.getText()+"','"+accountNumberTF.getText()+"','"+phoneTF.getText()+"');";
			System.out.println(Adminquery);
			stm.execute(Adminquery);
		}
		else if(userButton.isSelected())
		{
			String Userquery = "INSERT INTO user values ('"+nameTF.getText()+"','"+passwordF.getText()+"','"+accountNumberTF.getText()+"','"+phoneTF.getText()+"');";
			System.out.println(Userquery);
			stm.execute(Userquery);
		}
			stm.close();
			con.close();
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
    }

}