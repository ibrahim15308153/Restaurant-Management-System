import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener
{
	private JLabel accountNumberLabel, passLabel;
	private JTextField accountNumberTF;
	private JPasswordField passPF;
	private JButton buttonLogin, buttonClose,registerButton;
	private JPanel panel;
	private JRadioButton radioAdmin, radioUser;
	private ButtonGroup bg;
	private String Adminquery,Userquery;
	private boolean flag;
	
	public Login()
	{
		super("Login Window");
		
		this.setSize(1360, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel = new JPanel();
		panel.setLayout(null);
		
		accountNumberLabel = new JLabel("Account ID 	: ");
		accountNumberLabel.setBounds(100, 50, 100, 25);
		panel.add(accountNumberLabel);
		
		accountNumberTF = new JTextField();
		accountNumberTF.setBounds(200, 50, 100, 25);
		panel.add(accountNumberTF);
		
		passLabel = new JLabel("Password	: ");
		passLabel.setBounds(100, 75, 100, 25);
		panel.add(passLabel);
		
		passPF = new JPasswordField();
		passPF.setBounds(200, 75, 100, 25);
		panel.add(passPF);
		
		buttonLogin = new JButton("Login");
		buttonLogin.setBounds(100,120,80,40);
		buttonLogin.addActionListener(this);
		panel.add(buttonLogin);
		
		buttonClose = new JButton("Close");
		buttonClose.setBounds(200,120,80,40);
		buttonClose.addActionListener(this);
		panel.add(buttonClose);
		
		registerButton=new JButton("Register");
		registerButton.setBounds(300,120,100,40);
		registerButton.addActionListener(this);
		panel.add(registerButton);
		
		radioUser = new JRadioButton("User");
		radioUser.setBounds(400,50,150,25);
		radioUser.setSelected(true);
		panel.add(radioUser);
		
		radioAdmin = new JRadioButton("Admin");
		radioAdmin.setBounds(400,80,150,25);
		panel.add(radioAdmin);
		
		bg = new ButtonGroup();
		bg.add(radioUser);
		bg.add(radioAdmin);
		
		this.add(panel);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonLogin.getText()))
		{
			System.out.println("hello");
			flag=true;
			check();
		}
		else if(buttonClicked.equals(buttonClose.getText()))
		{
			System.exit(0);
		}
		else if(buttonClicked.equals(registerButton.getText()))
		{
			Register r=new Register();
			r.setVisible(true);
		}
	}
	
	public void check()
	{
		if(radioAdmin.isSelected())
        {
			Adminquery= "SELECT `name`, `password`, `accountNumber`, `phone` FROM `admin`;";   			
			
			System.out.println(Adminquery);
		}
		else if(radioUser.isSelected())
		{
			Userquery = "SELECT `name`, `password`, `accountNumber`, `phone` FROM `user`;";     
			System.out.println(Userquery);
		}
			Connection con=null;
			Statement st = null;
			ResultSet rs = null;
        try
		{
			DataAccess da = new DataAccess();
			if(radioAdmin.isSelected())
			{
				rs = da.getData(Adminquery);
			}
			else if(radioUser.isSelected())
			{
				rs=da.getData(Userquery);
			}
			System.out.println("results received");
					
			while(rs.next())
			{
                String name = rs.getString("name");
                String password = rs.getString("Password");
				String accountNumber = rs.getString("AccountNumber");
				String phone = rs.getString("Phone");
				
				if(accountNumber.equals(accountNumberTF.getText()))
				{
					flag=false;
					if(password.equals(passPF.getText()))
					{
						if(radioUser.isSelected())
						{
							UserHome ush = new UserHome(name,password,accountNumber,phone);
							this.setVisible(false);
							ush.setVisible(true);
						}
						else if(radioAdmin.isSelected())
						{
							AdminHome adh = new AdminHome(name,password,accountNumber,phone);
							this.setVisible(false);
							adh.setVisible(true);
						}
					}
					else
					{
						JOptionPane.showMessageDialog(this,"Invalid pass"); 
					}
				}			
			}
			if(flag){JOptionPane.showMessageDialog(this,"Invalid id"); }
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
        finally
		{
            try
			{
                if(rs!=null)
					rs.close();

                if(st!=null)
					st.close();

                if(con!=null)
					con.close();
            }
            catch(Exception ex){}
        }
    } 
}