import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ShowTableUsers extends JFrame implements ActionListener
{
	JButton back;
	JPanel  panel;
	public String name,password,accountNumber,phone;
    JTable table;
    ResultSet rs;
	DataAccess da;
    public ShowTableUsers()
    {	 
		super("User Table");
		this.setSize(1200, 650);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		panel=new JPanel();
		panel.setLayout(null);
		
		back=new JButton("Back");
		back.setBounds(300,500,80,40);
		back.addActionListener(this);
		panel.add(back);
	
        table = new JTable();
        da = new DataAccess();
        String[] columnNames = new String[] {
            "Name", "Phone", "Password","Account Number"
        };
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
		
		
        String query = "select * from user";
        rs =da.getData(query);
		
        try{
            while(rs.next())
            {
				System.out.println(rs.getString("name"));
                String col1 = rs.getString("name");
                String col2 = rs.getString("phone");
                String col3 = rs.getString("password"); 
				String col4=rs.getString("accountNumber");	
                model.addRow(new Object[]{col1, col2, col3,col4});
            }
            table.setModel(model);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
       
        this.add(new JScrollPane(table));
		this.pack();
		this.add(panel);		
		
        this.setVisible(true);
    }
	public void actionPerformed(ActionEvent ae)
	{
		String buttonClicked = ae.getActionCommand();
		
		if(buttonClicked.equals(back.getText()))
		{
			AdminHome adh=new AdminHome(name,password,accountNumber,phone);
			adh.setVisible(true);
			this.setVisible(false);
			dispose();
		}
	}	
}		



