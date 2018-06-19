package student;
import student.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;

class ViewTeacher extends JFrame {
	
    JFrame student;
    Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
	

    static JTable table;
	int ID;
	int rollNo;
    DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	String firstName,lastName,fatherName,address,gender,dateOfBirth,yearFySyTy,semester,contactNo,parentContactNo,email;
    String[] columnNames = {"ID", "First Name", "Last Name", "Father Name","Address","Gender","D.O.B","Contact No","Email"};
	TableColumn IDcolumn = null;
	TableColumn Emailcolumn = null;
	ViewTeacher(){	
		student = new JFrame("Student Management System");
        student.setLayout(null);
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
		
		table.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 15 ));
		table.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		IDcolumn = table.getColumnModel().getColumn(0);
		IDcolumn.setPreferredWidth(18);
		
	
		Emailcolumn = table.getColumnModel().getColumn(8);
		Emailcolumn.setPreferredWidth(190);
		
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.setBounds(40,70,1200,600);
				
		
        try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("select * from TeacherTable");
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                fatherName = rs.getString(4);
				address = rs.getString(5);
                gender = rs.getString(6);
                dateOfBirth = rs.getString(7);
				contactNo = rs.getString(8);
                email = rs.getString(9);
                model.addRow(new Object[]{ID, firstName, lastName, fatherName,address,gender,dateOfBirth,contactNo,email});
                i++;
            }
            if (i < 1) {
			JOptionPane.showMessageDialog(null,"No records present.");
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
     
				
		JButton butAddStudent = new JButton("Add Teacher");
		butAddStudent.setBounds(340,20,150,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);
	
		JButton butDeletetStudent = new JButton("Delete Teacher");
		butDeletetStudent.setBounds(520,20,130,26);
		butDeletetStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butDeletetStudent);
		
		
		JButton butSearch = new JButton("Search");
		butSearch.setBounds(680,20,80,26);
		butSearch.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butSearch);
		
		JButton butLogOut = new JButton("Log out");
		butLogOut.setBounds(780,20,100,26);
		butLogOut.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butLogOut);
				
		butAddStudent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new AddTeacher();
				}
			});	
			
		butDeletetStudent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new DeleteTeacher();
				}
			});	

			butLogOut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
				}
			});	
			
	
			butSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JTextField txtID = new JTextField();
				Object[] message = {
					"Enter ID to search:", txtID
					};
				int option = JOptionPane.showConfirmDialog(null, message, "Search", JOptionPane.OK_CANCEL_OPTION);
				if (option == JOptionPane.OK_OPTION) {

				int  id = Integer.parseInt(txtID.getText());

				searchID(id);
				}
			}
		});
		
			
		student.add(scroll);
        student.setVisible(true);
		student.setResizable(false);
        student.setSize(1280, 750);
        student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void searchID(int id)
	{	
		while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }

		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
			st = con.createStatement();
			pst = con.prepareStatement("select * from TeacherTable where ID = "+id);
			ResultSet rs = pst.executeQuery();
	        int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                firstName = rs.getString(2);
                lastName = rs.getString(3);
                fatherName = rs.getString(4);
				address = rs.getString(5);
                gender = rs.getString(6);
                dateOfBirth = rs.getString(7);
				contactNo = rs.getString(8);
                email = rs.getString(9);
                model.addRow(new Object[]{ID, firstName, lastName, fatherName,address,gender,dateOfBirth,contactNo,email});
                i++;
            }
            if (i < 1) {
               JOptionPane.showMessageDialog(null,"No records present.");
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
     
	}
	public static void main(String args[])
	{
		new ViewTeacher();
	}
}