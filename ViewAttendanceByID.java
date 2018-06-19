package student;
import student.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.JTableHeader;
import javax.swing.table.AbstractTableModel;
import java.util.Date;
import java.util.*;

class ViewAttendanceByID extends JFrame {
  Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst;
	JFrame student;
	Checkbox fy,sy,ty;
	CheckboxGroup checkGroup;
	 JComboBox cmbYear,cmbDay,cmbMonth;
	 String date = null,day = null,month = null ,year = null;
	 String studentName;
    static JTable table;
	int ID;
	int rollNo;
    DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	String firstName,lastName,attendance;
    String[] columnNames = {"Date","Attendance"};

	ViewAttendanceByID(int id,String date){	
		student =  new JFrame("Student Management System");
		student.setLayout(null);
				
		 model.setColumnIdentifiers(columnNames);
    
		 table = new JTable(model) {

            // private static final long serialVersionUID = 1L;

            /*@Override
            public Class getColumnClass(int column) {
            return getValueAt(0, column).getClass();
            }*/
            @Override
            public Class getColumnClass(int column) {
                switch (column) {
                    case 0:
                        return Integer.class;
                    case 1:
                        return Boolean.class;
                    default:
                        return Boolean.class;
                }
            }
        };
        // table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
		
		table.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 16 ));
		table.setFont(new Font("Rockwell", Font.PLAIN, 15));
		
		
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.setBounds(140,165,300,520);
		student.add(scroll);		
				
		
		JButton butViewStudens = new JButton("View");
		butViewStudens.setBounds(10,20,116,26);
		butViewStudens.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewStudens);
		
		JButton butDeletetStudent = new JButton("Delete");
		butDeletetStudent.setBounds(10,60,116,26);
		butDeletetStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butDeletetStudent);
		
		JButton butSetTermWork = new JButton("Set Term Work");
		butSetTermWork.setBounds(135,20,125,26);
		butSetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetTermWork);
		
		JButton butGetTermWork = new JButton("Get Term Work");
		butGetTermWork.setBounds(135,60,125,26);
		butGetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetTermWork);
			
		JButton butAddStudent = new JButton("Add");
		butAddStudent.setBounds(270,60,121,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);
	
		
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
		
		
		JButton butOk = new JButton("OK");
		butOk.setBounds(270,690,70,25);
		butOk.setFont(new Font("Serif", Font.PLAIN, 17));
		student.add(butOk);
			
		JLabel labID= new JLabel("ID",SwingConstants.RIGHT);
		labID.setBounds(245,100,40,20);
		labID.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(labID);
		
		JLabel StudentId= new JLabel();
		StudentId.setBounds(305,100,40,20);
		StudentId.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(StudentId);
		
		JLabel labName= new JLabel("Name",SwingConstants.RIGHT);
		labName.setBounds(235,130,50,23);
		labName.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(labName);
		
		JLabel name= new JLabel();
		name.setBounds(305,130,100,23);
		name.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(name);
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("select firstName from StudentTable where studentID = "+id);
			
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                studentName = rs.getString("firstName");
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
		String tempID = String.valueOf(id);
		StudentId.setText(tempID);
		name.setText(studentName);
		viewAttendance(id,date);
		
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
			
			butDeletetStudent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new DeleteStudent();
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
			
		
			butSetAttendance.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new SetAttendance();
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
		
	
			butOk.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					student.dispose();
					new ViewAttendance();
				}
			});		
	}
	void viewAttendance(int id,String date){
		while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }
		 try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("select * from AttendanceTable where studentID = "+id);
			
			
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                date = rs.getString("attendanceDate");
				attendance = rs.getString("attendance");
				if(attendance.equals("true")){
					model.addRow(new Object[]{date,Boolean.TRUE});
				}
				else{
					model.addRow(new Object[]{date,Boolean.FALSE});
				}
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
		new ViewAttendanceByID(70,"22/2/2015");
	}
}