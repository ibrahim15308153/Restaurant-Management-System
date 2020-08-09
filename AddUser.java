import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class AddUser extends JFrame implements ActionListener
{
	private JLabel nameLabel,passwordLabel,phoneLabel,accountNumberLabel;
	private JTextField nameTF,phoneTF,accountNumberTF;
	private JPasswordField passwordF;
	private JButton confirmButton,backButton,cancelButton;
	private JPanel panel;
	public String name,password,accountNumber,phone;
	
	public AddUser()
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
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(confirmButton.getText()))
		{
			insertIntoDB();
			JOptionPane.showMessageDialog(this,"Registration Confirmed");
		}
		else if(buttonClicked.equals(cancelButton.getText()))
		{
			System.exit(0);
		}
		else if(buttonClicked.equals(backButton.getText()))
		{
			AdminHome adh = new AdminHome(name,password,accountNumber,phone);
			adh.setVisible(true);
			this.setVisible(false);
			dispose();
		}
	}
	public void insertIntoDB()
	{
		
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "");
			Statement stm = con.createStatement();
		 
			String Userquery = "INSERT INTO user values ('"+nameTF.getText()+"','"+passwordF.getText()+"','"+accountNumberTF.getText()+"','"+phoneTF.getText()+"');";
			System.out.println(Userquery);
			stm.execute(Userquery);
			stm.close();
			con.close();
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
    }

}