import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class DeleteItem extends JFrame implements ActionListener
{
	private JLabel serialLabel;
	private JTextField serialTF;
	private JButton buttonBack, buttonLogout, buttonDelete;
	private JPanel panel;
	public String name,password,accountNumber,phone;
	
	public DeleteItem()
	{
		super("Delete Window");
		this.setSize(1200, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setLayout(null);
		
		serialLabel = new JLabel("Enter Serial Number : ");
		serialLabel.setBounds(100,100,150,30);
		panel.add(serialLabel);
		
		serialTF = new JTextField();
		serialTF.setBounds(260,100,100,30);
		panel.add(serialTF);
		
		buttonBack = new JButton("Back");
		buttonBack.setBounds(100, 300, 80, 30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		buttonDelete = new JButton("Delete");
		buttonDelete.setBounds(190, 300, 80, 30);
		buttonDelete.addActionListener(this);
		panel.add(buttonDelete);
		
		buttonLogout = new JButton("Logout");
		buttonLogout.setBounds(280, 300, 80, 30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonBack.getText()))
		{
			AdminHome adh=new AdminHome(name,password,accountNumber,phone);
			adh.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonDelete.getText()))
		{
			deleteFromDB();
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
	}
	
	public void deleteFromDB()
	{
		String query = "DELETE from fooditems where SerialNumber="+serialTF.getText()+";";
		System.out.println(query);
        try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurantdb", "root", "");
			Statement stm = con.createStatement();
			stm.execute(query);
			stm.close();
			con.close();
					
		}
        catch(Exception ex)
		{
			System.out.println("Exception : " +ex.getMessage());
        }
	}
}

	