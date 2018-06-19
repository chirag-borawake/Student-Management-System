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

class DeleteStudent extends JFrame  implements ItemListener{
	Connection con = null;
	Statement st = null;
	String sql;
	JFrame student;
	JTextField txtStudentID;
	
	Checkbox fy,sy,ty;
	CheckboxGroup checkGroup;
	
    ResultSet rs;
    PreparedStatement pst;
    static JTable table;
	int ID;
	int rollNo;
	DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	String firstName,lastName,year;
    String[] columnNames = {"ID","Roll No", "First Name", "Last Name","Year"};
	
	DeleteStudent(){	
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
				scroll.setBounds(10,130,530,510);
		student.add(scroll);
		
		fy =new Checkbox("First Year",true);
		fy.setBounds(20,100,120,20);
		fy.setFont(new Font("Serif", Font.PLAIN, 20));
        fy.addItemListener(this);
		student.add(fy);
		
		sy =new Checkbox("Second Year");
		sy.setBounds(150,100,120,20);
		sy.setFont(new Font("Serif", Font.PLAIN, 20));
        sy.addItemListener(this);
		student.add(sy);
		
		
		ty =new Checkbox("Third Year");
		ty.setBounds(300,100,120,20);
		ty.setFont(new Font("Serif", Font.PLAIN, 20));
        ty.addItemListener(this);
		student.add(ty);
		
		
		checkGroup = new CheckboxGroup();
		
        fy.setCheckboxGroup(checkGroup);
        sy.setCheckboxGroup(checkGroup);
        ty.setCheckboxGroup(checkGroup);
		
		student.add(fy);
		student.add(sy);
		student.add(ty);
        try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("select * from StudentTable where yearFySyTy = 'FY'");
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				year = rs.getString(9);
                model.addRow(new Object[]{ID, rollNo,firstName, lastName,year});
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
		
		txtStudentID= new JTextField(){
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
		txtStudentID.setBounds(437,655,100,20);
		txtStudentID.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(txtStudentID);
		
				

		JButton butViewStudens = new JButton("View");
		butViewStudens.setBounds(10,20,116,26);
		butViewStudens.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewStudens);
		
		JButton butAddStudent = new JButton("Add");
		butAddStudent.setBounds(10,60,116,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);
		
		JButton butSetTermWork = new JButton("Set Term Work");
		butSetTermWork.setBounds(135,20,125,26);
		butSetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetTermWork);
		
		JButton butGetTermWork = new JButton("Get Term Work");
		butGetTermWork.setBounds(135,60,125,26);
		butGetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetTermWork);
			
		JButton butViewAttendance = new JButton("Get Attendance");
		butViewAttendance.setBounds(270,60,121,26);
		butViewAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewAttendance);
		
		JButton butSetAttendance = new JButton("Set Attendance");
		butSetAttendance.setBounds(270,20,121,26);
		butSetAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetAttendance);
		
		JButton butSetInternalMarks = new JButton("Set Internal Marks");
		butSetInternalMarks.setBounds(400,20,141,26);
		butSetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetInternalMarks);
		
		JButton butGetInternalMarks = new JButton("Get Internal Marks");
		butGetInternalMarks.setBounds(400,60,141,26);
		butGetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetInternalMarks);
		
		
		JButton butSearch = new JButton("Search");
		butSearch.setBounds(450,100,90,23);
		butSearch.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butSearch);
		
		
		JButton butDelete = new JButton("Delete");
		butDelete.setBounds(437,685,100,25);
		butDelete.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butDelete);
		
				
		student.setSize(555,750);
		student.setVisible(true);
		student.setResizable(false);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		butAddStudent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new AddStudent();
				}
			});	
			
			butViewStudens.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewStudents();
				}
			});	
			
			
			butSetTermWork.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new SetTermWork();
				}
			});	
			
			butGetTermWork.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ShowTermWork();
				}
			});	
			
			butViewAttendance.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewAttendance();
				}
			});	
			
			butSetAttendance.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new SetAttendance();
				}
			});	
			
	butDelete.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
			String internalMarks = null,termWork = null;
			Checkbox chk = checkGroup.getSelectedCheckbox();
			String selectedItem=chk.getLabel();
			try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:Student");
				st = con.createStatement();
				if(selectedItem.equals("First Year"))
				{
				internalMarks = "delete  * from FY_InternalMarksTable where studentID = " +txtStudentID.getText()+"" ;
				
				termWork = "delete  * from FY_TermWorkTable where studentID = " +txtStudentID.getText()+"" ;
				}
				else if(selectedItem.equals("Second Year"))
				{
				internalMarks = "delete  * from SY_InternalMarksTable where studentID = " +txtStudentID.getText()+"" ;
				
				termWork = "delete  * from SY_TermWorkTable where studentID = " +txtStudentID.getText()+"" ;
				}
				else if(selectedItem.equals("Third Year"))
				{
				internalMarks = "delete  * from TY_InternalMarksTable where studentID = " +txtStudentID.getText()+"" ;
				
				termWork = "delete  * from TY_TermWorkTable where studentID = " +txtStudentID.getText()+"" ;
				}
				st.executeUpdate(internalMarks);
				st.executeUpdate(termWork);
				st = con.createStatement();
				sql = "delete  * from AttendanceTable where studentID = " +txtStudentID.getText()+"" ;
				st.executeUpdate(sql);
				sql = "delete  * from StudentTable where studentID = " +txtStudentID.getText()+"" ;
				JOptionPane.showMessageDialog(null,"Record is deleted.");
				st.executeUpdate(sql);
				txtStudentID.setText("");
				}catch(Exception ex){
					System.out.print(ex);
				} 
				finally {
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
				getSelectedYear();		
				}
		});
		butSetInternalMarks.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new SetInternalMarks();			
				}
			});	
			
			butGetInternalMarks.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewStudensInternalMarks();
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
	private void searchID(int id)
	{	
		while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }
		Checkbox chk = checkGroup.getSelectedCheckbox();
		String selectedItem=chk.getLabel();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
			st = con.createStatement();
			pst = con.prepareStatement("select * from StudentTable where studentID = "+id);
			 ResultSet rs = pst.executeQuery();
		int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                year = rs.getString(9);
                model.addRow(new Object[]{ID, rollNo,firstName, lastName,year});
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
	
	public void itemStateChanged(ItemEvent ie)
    {
		getSelectedYear();
    }
	private void getSelectedYear()
	{
		while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }

		Checkbox chk = checkGroup.getSelectedCheckbox();
		String selectedItem=chk.getLabel();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
		if(selectedItem.equals("First Year"))
		{
			pst = con.prepareStatement("select * from StudentTable where yearFySyTy = 'FY'");
		}
		else if(selectedItem.equals("Second Year"))
		{
			pst = con.prepareStatement("select * from StudentTable where yearFySyTy = 'SY'");
		}
		else if(selectedItem.equals("Third Year"))
		{
			pst = con.prepareStatement("select * from StudentTable where yearFySyTy = 'TY'");
		}	
		 ResultSet rs = pst.executeQuery();
		int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                year = rs.getString(9);
                model.addRow(new Object[]{ID, rollNo,firstName, lastName,year});
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
		new DeleteStudent();
	}
}