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

class ViewStudents extends JFrame implements ItemListener{
	
    JFrame student;
    Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
	
	Checkbox fy,sy,ty,all;
	CheckboxGroup checkGroup;
	
    static JTable table;
	int ID;
	int rollNo;
    DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	String firstName,lastName,fatherName,address,gender,dateOfBirth,yearFySyTy,semester,contactNo,parentContactNo,email;
    String[] columnNames = {"ID","Roll no", "First Name", "Last Name", "Father Name","Address","Gender","D.O.B","Year","Semester","Contact No","Parent No","Email"};
	TableColumn IDcolumn = null;
	TableColumn Yearcolumn = null;
	TableColumn Emailcolumn = null;
	TableColumn RollNocolumn = null;
	ViewStudents(){	
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
		
		RollNocolumn = table.getColumnModel().getColumn(1);
		RollNocolumn.setPreferredWidth(35);
		
		Yearcolumn = table.getColumnModel().getColumn(8);
		Yearcolumn.setPreferredWidth(25);
		
		
		Emailcolumn = table.getColumnModel().getColumn(12);
		Emailcolumn.setPreferredWidth(210);
		
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.setBounds(40,100,1200,600);
				
		
		fy =new Checkbox("First Year",true);
		fy.setBounds(250,65,120,25);
		fy.setFont(new Font("Serif", Font.PLAIN, 20));
		
		sy =new Checkbox("Second Year");
		sy.setBounds(466,65,120,25);
		sy.setFont(new Font("Serif", Font.PLAIN, 20));
		
		ty =new Checkbox("Third Year");
		ty.setBounds(679,65,120,25);
		ty.setFont(new Font("Serif", Font.PLAIN, 20));
		
		all =new Checkbox("ALL");
		all.setBounds(892,65,50,25);
		all.setFont(new Font("Serif", Font.PLAIN, 20));
		
		
		all.addItemListener(this);
        fy.addItemListener(this);
        sy.addItemListener(this);
        ty.addItemListener(this);
		
		checkGroup = new CheckboxGroup();
		
		all.setCheckboxGroup(checkGroup);
        fy.setCheckboxGroup(checkGroup);
        sy.setCheckboxGroup(checkGroup);
        ty.setCheckboxGroup(checkGroup);
		
		student.add(all);
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
                fatherName = rs.getString(5);
				address = rs.getString(6);
                gender = rs.getString(7);
                dateOfBirth = rs.getString(8);
                yearFySyTy = rs.getString(9);
                semester = rs.getString(10);
				contactNo = rs.getString(11);
                parentContactNo = rs.getString(12);
                email = rs.getString(13);
                model.addRow(new Object[]{ID,rollNo, firstName, lastName, fatherName,address,gender,dateOfBirth,yearFySyTy,semester,contactNo,parentContactNo,email});
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
     
				
		JButton butAddStudent = new JButton("Add");
		butAddStudent.setBounds(40,20,116,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);
	
		JButton butDeletetStudent = new JButton("Delete");
		butDeletetStudent.setBounds(191,20,116,26);
		butDeletetStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butDeletetStudent);
		
		JButton butSetTermWork = new JButton("Set Term Work");
		butSetTermWork.setBounds(342,20,125,26);
		butSetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetTermWork);
		
		JButton butGetTermWork = new JButton("Get Term Work");
		butGetTermWork.setBounds(493,20,125,26);
		butGetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetTermWork);
			
		JButton butViewAttendance = new JButton("Get Attendance");
		butViewAttendance.setBounds(644,20,121,26);
		butViewAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewAttendance);
		
		JButton butSetAttendance = new JButton("Set Attendance");
		butSetAttendance.setBounds(795,20,121,26);
		butSetAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetAttendance);
		
		JButton butSetInternalMarks = new JButton("Set Internal Marks");
		butSetInternalMarks.setBounds(940,20,141,26);
		butSetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetInternalMarks);
		
		JButton butGetInternalMarks = new JButton("Get Internal Marks");
		butGetInternalMarks.setBounds(1095,20,141,26);
		butGetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetInternalMarks);
		
	
		JButton butSearch = new JButton("Search");
		butSearch.setBounds(1050,65,80,26);
		butSearch.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butSearch);
				
		butAddStudent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new AddStudent();
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
	public void itemStateChanged(ItemEvent ie)
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
			if(selectedItem.equals("ALL"))
			{
				pst = con.prepareStatement("select * from StudentTable");
			}
			else if(selectedItem.equals("First Year"))
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
                fatherName = rs.getString(5);
				address = rs.getString(6);
                gender = rs.getString(7);
                dateOfBirth = rs.getString(8);
                yearFySyTy = rs.getString(9);
                semester = rs.getString(10);
				contactNo = rs.getString(11);
                parentContactNo = rs.getString(12);
                email = rs.getString(13);
                model.addRow(new Object[]{ID,rollNo, firstName, lastName, fatherName,address,gender,dateOfBirth,yearFySyTy,semester,contactNo,parentContactNo,email});
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
                fatherName = rs.getString(5);
				address = rs.getString(6);
                gender = rs.getString(7);
                dateOfBirth = rs.getString(8);
                yearFySyTy = rs.getString(9);
                semester = rs.getString(10);
				contactNo = rs.getString(11);
                parentContactNo = rs.getString(12);
                email = rs.getString(13);
                model.addRow(new Object[]{ID,rollNo, firstName, lastName, fatherName,address,gender,dateOfBirth,yearFySyTy,semester,contactNo,parentContactNo,email});
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
		new ViewStudents();
	}
}