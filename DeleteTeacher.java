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

class DeleteTeacher extends JFrame   {
	Connection con = null;
	Statement st = null;
	String sql;
	JFrame student;
	JTextField txtTeacherID;
	
    ResultSet rs;
    PreparedStatement pst;
    static JTable table;
	int ID;
	int rollNo;
	DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	String firstName,lastName,year;
    String[] columnNames = {"ID","First Name", "Last Name"};
	
	DeleteTeacher(){	
		student =  new JFrame("Student Management System");
		student.setLayout(null);
		
		
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
		
		table.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 15 ));
		table.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		 JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.setBounds(10,70,530,560);
		student.add(scroll);
		
		
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
                model.addRow(new Object[]{ID,firstName,lastName});
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
    
		JLabel labMessage= new JLabel("Enter    student   ID   which    is    to  be  deleted");
		labMessage.setBounds(10,650,400,30);
		labMessage.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labMessage);
		
		txtTeacherID= new JTextField(){
		  public void processKeyEvent(KeyEvent ev) {
			char c = ev.getKeyChar();
			try {
			  if (c > 31 && c < 127) {
				Integer.parseInt(c + "");
			  }
			  super.processKeyEvent(ev);
			}
			catch (NumberFormatException nfe) {
			}
		  }
		};
		txtTeacherID.setBounds(437,655,100,20);
		txtTeacherID.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(txtTeacherID);
		
				
		JButton butAddStudent = new JButton("Add Teacher");
		butAddStudent.setBounds(40,20,116,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);

		JButton butViewStudens = new JButton("View Teacher");
		butViewStudens.setBounds(180,20,116,26);
		butViewStudens.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewStudens);
		
		
		JButton butSearch = new JButton("Search");
		butSearch.setBounds(320,20,90,26);
		butSearch.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butSearch);
		
		JButton butDelete = new JButton("Delete");
		butDelete.setBounds(437,685,100,25);
		butDelete.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butDelete);
		
		JButton butLogOut = new JButton("Log out");
		butLogOut.setBounds(430,20,100,26);
		butLogOut.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butLogOut);
			butLogOut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
				}
			});		
		student.setSize(555,750);
		student.setVisible(true);
		student.setResizable(false);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		butAddStudent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new AddTeacher();
				}
			});	
			
			butViewStudens.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewTeacher();
				}
			});	
			
			butLogOut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
				}
			});	
			
			
	butDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:Student");
				st = con.createStatement();
				sql = "delete * from TeacherTable where ID = " +txtTeacherID.getText()+"" ;
				
				JOptionPane.showMessageDialog(null,"Record is deleted.");
				st.executeUpdate(sql);
				txtTeacherID.setText("");
				}catch(Exception ex){
					System.out.print(ex);
				} 
				finally {
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
				removeRecord();
			}
		});
		
			butSearch.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				JTextField txtID = new JTextField(){
				  public void processKeyEvent(KeyEvent ev) {
					char c = ev.getKeyChar();
					try {
					  if (c > 31 && c < 127) {
						Integer.parseInt(c + "");
					  }
					  super.processKeyEvent(ev);
					}
					catch (NumberFormatException nfe) {
					}
				  }
				};
					
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
	}
	private void removeRecord()
	{	
		while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }
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
                model.addRow(new Object[]{ID,firstName,lastName});
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
                model.addRow(new Object[]{ID,firstName, lastName});
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
        }
		finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
	}

	public static void main(String args[])
	{
		new DeleteTeacher();
	}
}