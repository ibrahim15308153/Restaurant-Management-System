import java.sql.ResultSet;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserFoodTable extends JFrame implements ActionListener
{
	public String name,password,accountNumber,phone;
    JTable table;
	private JButton back;
	private JPanel panel;
    ResultSet rs;
    DataAccess da;
    public UserFoodTable()
    {
		super("Food Table");
		
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
            "Name", "price", "serial"
        };
		
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);
        String query = "select * from fooditems";
		rs =da.getData(query);
        try{
            while(rs.next())
            {
                String col1 = rs.getString("name");
                String col2 = rs.getString("price");
                String col3 = rs.getString("serial");                
                model.addRow(new Object[]{col1, col2, col3});
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
			UserHome ush=new UserHome(name,password,accountNumber,phone);
			ush.setVisible(true);
			this.setVisible(false);
			dispose();
		}
	}	
}