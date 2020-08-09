import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

class CreateItem extends JFrame implements ActionListener
{
	private JLabel nameLabel,priceLabel,serialLabel;
	private JTextField nameTF,priceTF,serialTF;
	private JButton buttonBack, buttonLogout, buttonUpdate;
	private JPanel panel;
	public String name,password,accountNumber,phone;
	
	public CreateItem()
	{
		super("Create Window");
		
		this.setSize(1200,650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setLayout(null);
		
		nameLabel=new JLabel("Name of the items");
		nameLabel.setBounds(100,50,150,30);
		panel.add(nameLabel);
		
		nameTF=new JTextField();
		nameTF.setBounds(260,50,100,30);
		panel.add(nameTF);
		
		priceLabel=new JLabel("Price");
		priceLabel.setBounds(100,100,150,30);
		panel.add(priceLabel);
		
		priceTF=new JTextField();
		priceTF.setBounds(260,100,100,30);
		panel.add(priceTF);
		
		serialLabel=new JLabel("Serial No.");
		serialLabel.setBounds(100,150,150,30);
		panel.add(serialLabel);
		
		serialTF=new JTextField();
		serialTF.setBounds(260,150,100,30);
		panel.add(serialTF);
		
		buttonBack=new JButton("Back");
		buttonBack.setBounds(100, 300, 80, 30);
		buttonBack.addActionListener(this);
		panel.add(buttonBack);
		
		buttonUpdate=new JButton("Update");
		buttonUpdate.setBounds(200, 300, 80, 30);
		buttonUpdate.addActionListener(this);
		panel.add(buttonUpdate);
		
		buttonLogout=new JButton("Log out");
		buttonLogout.setBounds(300, 300, 80, 30);
		buttonLogout.addActionListener(this);
		panel.add(buttonLogout);
		
		this.add(panel);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(buttonBack.getText()))
		{
			AdminHome adh = new AdminHome(name,password,accountNumber,phone);
			adh.setVisible(true);
			this.setVisible(false);
		}
		else if(buttonClicked.equals(buttonUpdate.getText()))
		{
			insertIntoDB();
		}
		else if(buttonClicked.equals(buttonLogout.getText()))
		{
			Login l = new Login();
			l.setVisible(true);
			this.setVisible(false);
		}
	}
	public void insertIntoDB()
	{
		String query = "INSERT INTO fooditems values ('"+nameTF.getText()+"','"+priceTF.getText()+"','"+serialTF.getText()+"');";
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