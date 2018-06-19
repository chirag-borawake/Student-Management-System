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

class ViewAttendance extends JFrame implements ItemListener{
  Connection con = null;
    ResultSet rs = null;
    Statement st = null;
    PreparedStatement pst;
	JFrame student;
	Checkbox fy,sy,ty;
	CheckboxGroup checkGroup;
	 JComboBox cmbYear,cmbDay,cmbMonth;
	 String date = null,day = null,month = null ,year = null;
    static JTable table;
	int ID;
	int rollNo;
    DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	String firstName,lastName,attendance;
    String[] columnNames = {"ID","Roll no", "First Name", "Last Name","Attendance"};
	TableColumn IDcolumn = null;
	TableColumn RollNocolumn = null;
	TableColumn Yearcolumn = null;

	ViewAttendance(){	
		student =  new JFrame("Student Management System");
		student.setLayout(null);
				
		 model.setColumnIdentifiers(columnNames);
    
		 table = new JTable(model) {

            private static final long serialVersionUID = 1L;

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
                        return Integer.class;
                    case 2:
                        return String.class;
					case 3:
                        return String.class;
                    case 4:
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
		
		IDcolumn = table.getColumnModel().getColumn(0);
		IDcolumn.setPreferredWidth(18);
		
		RollNocolumn = table.getColumnModel().getColumn(1);
		RollNocolumn.setPreferredWidth(35);
		
		Yearcolumn = table.getColumnModel().getColumn(4);
		Yearcolumn.setPreferredWidth(25);
		
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.setBounds(10,190,530,520);
		student.add(scroll);		
				
		cmbDay = new JComboBox();
		cmbDay.addActionListener(new cmbDay_Action());
		student.add(cmbDay);
		cmbDay.setBounds(10,120,70,23);
		for (int i = 1; i<=31; i++){
			cmbDay.addItem(String.valueOf(i));
		}
		
		cmbMonth = new JComboBox();
		cmbMonth.addActionListener(new cmbMonth_Action());
		student.add(cmbMonth);

		cmbMonth.setBounds(110,120,70,23);
		for (int i=1; i<=12; i++){
			cmbMonth.addItem(String.valueOf(i));
		}
		cmbYear = new JComboBox();
		cmbYear.addActionListener(new cmbYear_Action());
		student.add(cmbYear);
		cmbYear.setBounds(210,120,70,23);
		GregorianCalendar cal = new GregorianCalendar(); 
		int realYear = cal.get(GregorianCalendar.YEAR); 

		for (int i=realYear-5; i<=realYear+10; i++){
			cmbYear.addItem(String.valueOf(i));
		}
		
		
		fy =new Checkbox("First Year",true);
		fy.setBounds(10,155,120,20);
		fy.setFont(new Font("Serif", Font.PLAIN, 20));
        fy.addItemListener(this);
		
		sy =new Checkbox("Second Year");
		sy.setBounds(150,155,120,20);
		sy.setFont(new Font("Serif", Font.PLAIN, 20));
        sy.addItemListener(this);
		
		ty =new Checkbox("Third Year");
		ty.setBounds(305,155,120,20);
		ty.setFont(new Font("Serif", Font.PLAIN, 20));
        ty.addItemListener(this);
		
		checkGroup = new CheckboxGroup();
		
        fy.setCheckboxGroup(checkGroup);
        sy.setCheckboxGroup(checkGroup);
        ty.setCheckboxGroup(checkGroup);
		
		student.add(fy);
		student.add(sy);
		student.add(ty);
			
		JLabel labDay= new JLabel("Day");
		labDay.setBounds(10,90,40,23);
		labDay.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(labDay);
		
		JLabel labMonth= new JLabel("Month");
		labMonth.setBounds(110,90,60,23);
		labMonth.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(labMonth);
		
		JLabel labYear= new JLabel("Year");
		labYear.setBounds(210,90,40,23);
		labYear.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(labYear);
		
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
		
		
		JButton butSearch = new JButton("Search");
		butSearch.setBounds(440,155,100,25);
		butSearch.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butSearch);
		
		
		JButton butView = new JButton("View");
		butView.setBounds(315,120,90,25);
		butView.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butView);
				
		JButton butCalendar = new JButton("Calendar");
		butCalendar.setBounds(440,120,100,25);
		butCalendar.setFont(new Font("Serif", Font.PLAIN, 17));
		student.add(butCalendar);
				
	
		
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
		
		butSearch.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
						JTextField txtID = new JTextField();
						Object[] message = {
							"Enter ID to search:", txtID
							};
						int option = JOptionPane.showConfirmDialog(null, message, "Search", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {
						try {
							Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
							con=DriverManager.getConnection("jdbc:odbc:Student");
							st = con.createStatement();
							pst = con.prepareStatement("select * from StudentTable");
							ResultSet rs = pst.executeQuery();
							int i = 0,studentId = -1;	
							int  id = Integer.parseInt(txtID.getText());
							while (rs.next()) {
								studentId = rs.getInt(1);
								if (id == studentId) {
								System.out.println("studentId:"+studentId);
								System.out.println("id:"+id);
									student.dispose();
									new ViewAttendanceByID(id,date);
									i++; 
									break;
								}
							}
							if (i < 1) {
								JOptionPane.showMessageDialog(null,"No records present.");
							}
						} catch (Exception ex) {
							JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
						}finally {
									try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
									try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
								}
						}
					}
				
				});
			butCalendar.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
					new Calendar();
				}
			});
		
			butView.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
					viewAttendance(date);
					System.out.print(date);
				}
			});		
	}
	void viewAttendance(String date){
		while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }
		 try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("select * from StudentTable,AttendanceTable where yearFySyTy = 'FY' and StudentTable.studentID = AttendanceTable.studentID and attendanceDate = '"+date+"'");
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				attendance = rs.getString("attendance");
				if(attendance.equals("true")){
					model.addRow(new Object[]{ID,rollNo, firstName,lastName,Boolean.TRUE});
				}
				else{
					model.addRow(new Object[]{ID,rollNo, firstName,lastName,Boolean.FALSE});
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
			if(selectedItem.equals("First Year"))
			{
				pst = con.prepareStatement("select * from StudentTable,AttendanceTable where yearFySyTy = 'FY' and StudentTable.studentID = AttendanceTable.studentID and attendanceDate = '"+date+"'");
			}
			else if(selectedItem.equals("Second Year"))
			{
				pst = con.prepareStatement("select * from StudentTable,AttendanceTable where yearFySyTy = 'SY' and StudentTable.studentID = AttendanceTable.studentID and attendanceDate = '"+date+"'");
			}
			else if(selectedItem.equals("Third Year"))
			{
				pst = con.prepareStatement("select * from StudentTable,AttendanceTable where yearFySyTy = 'TY' and StudentTable.studentID = AttendanceTable.studentID and attendanceDate = '"+date+"'");
			}
			 ResultSet rs = pst.executeQuery();
			int i = 0;	
             while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				attendance = rs.getString("attendance");
				if(attendance.equals("true")){
					model.addRow(new Object[]{ID,rollNo, firstName,lastName,Boolean.TRUE});
				}
				else{
					model.addRow(new Object[]{ID,rollNo, firstName,lastName,Boolean.FALSE});
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
	
	
		 class cmbDay_Action implements ActionListener{
			public void actionPerformed (ActionEvent e){
				if (cmbDay.getSelectedItem() != null){
					day = cmbDay.getSelectedItem().toString();
					date = day + "/" + month + "/" + year;
				}
			}
		}
		 class cmbMonth_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (cmbMonth.getSelectedItem() != null){
				month = cmbMonth.getSelectedItem().toString();
				date = day + "/" + month + "/" + year;
			}
		}
	}
	 class cmbYear_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (cmbYear.getSelectedItem() != null){
				year = cmbYear.getSelectedItem().toString();
				date = day + "/" + month + "/" + year;
			}
		}
	}
	public static void main(String args[])
	{
		new ViewAttendance();
	}
}