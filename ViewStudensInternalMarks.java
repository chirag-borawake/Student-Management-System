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

class ViewStudensInternalMarks extends JFrame implements ItemListener{	
    JFrame student;
    Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
	
	Checkbox sem1,sem2,sem3,sem4,sem5,sem6;
	CheckboxGroup checkGroupSem;
    static JTable tableFySy,tableTy;
	JScrollPane scrollFySy,scrollTy; 
	int ID;
	int rollNo;
    DefaultTableModel modelFySy = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
    DefaultTableModel modeTy = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	String firstName,lastName,semester,yearFySyTy,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks, subFiveMarks;
    String[] columnNames = {"ID","Roll no", "First Name", "Last Name","Year","subOneMarks","subTwoMarks","subThreeMarks","subFourMarks","subFiveMarks"};
	
	String[] thirdYearcolumnNames = {"ID","Roll no", "First Name", "Last Name","Year","subOneMarks","subTwoMarks","subThreeMarks","subFourMarks"};
	
	TableColumn subOne = null;
	TableColumn subTwo = null;
	TableColumn subThree = null;
	TableColumn subFour = null;
	ViewStudensInternalMarks(){	
		student = new JFrame("Student Management System");
        student.setLayout(null);
        modelFySy.setColumnIdentifiers(columnNames);
        modeTy.setColumnIdentifiers(thirdYearcolumnNames);
        tableFySy = new JTable();
        tableFySy.setModel(modelFySy);
		tableFySy.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableFySy.setFillsViewportHeight(true);
		
		tableFySy.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 16 ));
		tableFySy.setFont(new Font("Rockwell", Font.PLAIN, 16));
		

		tableTy = new JTable();
        tableTy.setModel(modeTy);
		tableTy.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableTy.setFillsViewportHeight(true);
		
		tableTy.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 15 ));
		tableTy.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		subOne = tableTy.getColumnModel().getColumn(5);
		subOne.setPreferredWidth(150);
		
		subTwo = tableTy.getColumnModel().getColumn(6);
		subTwo.setPreferredWidth(150);
		
		subThree = tableTy.getColumnModel().getColumn(7);
		subThree.setPreferredWidth(150);
		
		subFour = tableTy.getColumnModel().getColumn(8);
		subFour.setPreferredWidth(150);
			
        scrollFySy = new JScrollPane(tableFySy);
        scrollFySy.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollFySy.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollFySy.setBounds(40,100,1200,600);
				
		scrollTy = new JScrollPane(tableTy);
        scrollTy.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTy.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollTy.setBounds(40,100,1200,600);
		
		sem1 = new Checkbox("Semester 1",true);
		sem1.setBounds(40,60,110,26);
		sem1.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem1);
		
		sem2 = new Checkbox("Semester 2");
		sem2.setBounds(232,60,110,26);
		sem2.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem2);
		
		sem3 = new Checkbox("Semester 3");
		sem3.setBounds(424,60,110,26);
		sem3.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem3);
		
		sem4 = new Checkbox("Semester 4");
		sem4.setBounds(616,60,110,26);
		sem4.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem4);
		
		sem5 = new Checkbox("Semester 5");
		sem5.setBounds(808,60,110,26);
		sem5.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem5);
		
		sem6 = new Checkbox("Semester 6");
		sem6.setBounds(1000,60,120,26);
		sem6.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem6);
		
		
		checkGroupSem = new CheckboxGroup();
		
		sem1.setCheckboxGroup(checkGroupSem);
        sem2.setCheckboxGroup(checkGroupSem);
        sem3.setCheckboxGroup(checkGroupSem);
        sem4.setCheckboxGroup(checkGroupSem);
        sem5.setCheckboxGroup(checkGroupSem);
        sem6.setCheckboxGroup(checkGroupSem);
		
		sem1.addItemListener(this);
        sem2.addItemListener(this);
        sem3.addItemListener(this);
        sem4.addItemListener(this);
        sem5.addItemListener(this);
        sem6.addItemListener(this);
		
		
		getSubName("Semester 1");
		
        try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("select * from FY_InternalMarksTable where yearFySyTy = 'FY' and semester = 'Semester 1'");
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                semester = rs.getString(5);
				yearFySyTy = rs.getString(6);
				subOneMarks = rs.getString(7);
                subTwoMarks = rs.getString(8);
                subThreeMarks = rs.getString(9);
                subFourMarks = rs.getString(10);
                subFiveMarks = rs.getString(11);
                modelFySy.addRow(new Object[]{ID,rollNo, firstName, lastName,yearFySyTy,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks,subFiveMarks});
                i++;
            }
            if (i < 1) {
               JOptionPane.showMessageDialog(null,"No records present.");
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
	
		JButton butViewStudens = new JButton("View");
		butViewStudens.setBounds(191,20,116,26);
		butViewStudens.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewStudens);
		
		JButton butDeletetStudent = new JButton("Delete");
		butDeletetStudent.setBounds(342,20,116,26);
		butDeletetStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butDeletetStudent);
		
		JButton butSetTermWork = new JButton("Set Term Work");
		butSetTermWork.setBounds(493,20,125,26);
		butSetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetTermWork);
		
		JButton butGetTermWork = new JButton("Get Term Work");
		butGetTermWork.setBounds(644,20,125,26);
		butGetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetTermWork);
			
		JButton butViewAttendance = new JButton("Get Attendance");
		butViewAttendance.setBounds(795,20,125,26);
		butViewAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewAttendance);
		
		JButton butSetAttendance = new JButton("Set Attendance");
		butSetAttendance.setBounds(940,20,125,26);
		butSetAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetAttendance);
		
		JButton butSetInternalMarks = new JButton("Set Internal Marks");
		butSetInternalMarks.setBounds(1097,20,141,26);
		butSetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetInternalMarks);
		
		
		
		
		JButton butSearch = new JButton("Search");
		butSearch.setBounds(1156,60,80,26);
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
			
			butViewStudens.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewStudents();
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
		student.add(scrollFySy);
		student.add(scrollTy);
        student.setVisible(true);
		student.setResizable(false);
        student.setSize(1280, 750);
        student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void itemStateChanged(ItemEvent ie)
    {
		Checkbox chk = checkGroupSem.getSelectedCheckbox();
		String semester=chk.getLabel();
		if(semester.equals("Semester 1"))
		{
			showInternalMarks(semester,"FY");
		}
		else if(semester.equals("Semester 2"))
		{
			showInternalMarks(semester,"FY");
		}
		else if(semester.equals("Semester 3"))
		{
			showInternalMarks(semester,"SY");
		}
		else if(semester.equals("Semester 4"))
		{
			showInternalMarks(semester,"SY");
		}
		else if(semester.equals("Semester 5"))
		{
			showInternalMarks(semester,"TY");
		}
		else if(semester.equals("Semester 6"))
		{
			showInternalMarks(semester,"TY");
		}
    }
	private void showInternalMarks(String semester,String year )
	{	
		while (modelFySy.getRowCount()>0)
          {
             modelFySy.removeRow(0);
          }
		  while (modeTy.getRowCount()>0)
          {
             modeTy.removeRow(0);
          }
		getSubName(semester);
        try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			if(year.equals("FY"))
			{
				pst = con.prepareStatement("select * from FY_InternalMarksTable where yearFySyTy = '"+year+"' and semester = '"+semester+"'");
			}
			else if(year.equals("SY"))
			{
				pst = con.prepareStatement("select * from SY_InternalMarksTable where yearFySyTy = '"+year+"' and semester = '"+semester+"'");
			}
			else 
			{
				pst = con.prepareStatement("select * from TY_InternalMarksTable where yearFySyTy = '"+year+"' and semester = '"+semester+"'");
				
			}
		
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                semester = rs.getString(5);
				yearFySyTy = rs.getString(6);
				subOneMarks = rs.getString(7);
                subTwoMarks = rs.getString(8);
                subThreeMarks = rs.getString(9);
                subFourMarks = rs.getString(10);
				if(!year.equals("TY")){
					scrollFySy.setVisible(true);
					scrollTy.setVisible(false);
					subFiveMarks = rs.getString(11);
					modelFySy.addRow(new Object[]{ID,rollNo, firstName, lastName,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks,subFiveMarks});
				}
				else{
					scrollTy.setVisible(true);
					scrollFySy.setVisible(false);
					modeTy.addRow(new Object[]{ID,rollNo, firstName, lastName,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks});
				}
                i++;
            }
            if (i < 1) {
               JOptionPane.showMessageDialog(null,"No records present.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}     
	}
	private void showInternalMarksByID(String semester,String year, int id)
	{	
		while (modelFySy.getRowCount()>0)
          {
             modelFySy.removeRow(0);
          }
		  while (modeTy.getRowCount()>0)
          {
             modeTy.removeRow(0);
          }
		getSubName(semester);
        try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			if(year.equals("FY"))
			{
				pst = con.prepareStatement("select * from FY_InternalMarksTable where yearFySyTy = '"+year+"' and semester = '"+semester+"' and studentID = "+id+"");
			}
			else if(year.equals("SY"))
			{
				pst = con.prepareStatement("select * from SY_InternalMarksTable where yearFySyTy = '"+year+"' and semester = '"+semester+"' and studentID = "+id+"");
			}
			else 
			{
				pst = con.prepareStatement("select * from TY_InternalMarksTable where yearFySyTy = '"+year+"' and semester = '"+semester+"' and studentID = "+id+"");
				
			}
		
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                semester = rs.getString(5);
				yearFySyTy = rs.getString(6);
				subOneMarks = rs.getString(7);
                subTwoMarks = rs.getString(8);
                subThreeMarks = rs.getString(9);
                subFourMarks = rs.getString(10);
				if(!year.equals("TY")){
					scrollFySy.setVisible(true);
					scrollTy.setVisible(false);
					subFiveMarks = rs.getString(11);
					modelFySy.addRow(new Object[]{ID,rollNo, firstName, lastName,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks,subFiveMarks});
				}
				else{
					scrollTy.setVisible(true);
					scrollFySy.setVisible(false);
					modeTy.addRow(new Object[]{ID,rollNo, firstName, lastName,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks});
				}
                i++;
            }
            if (i < 1) {
               JOptionPane.showMessageDialog(null,"No records present.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}     
	}
	void searchID(int id){
		Checkbox chk = checkGroupSem.getSelectedCheckbox();
		String semester=chk.getLabel();
		if(semester.equals("Semester 1"))
		{
			showInternalMarksByID(semester,"FY",id);
		}
		else if(semester.equals("Semester 2"))
		{
			showInternalMarksByID(semester,"FY",id);
		}
		else if(semester.equals("Semester 3"))
		{
			showInternalMarksByID(semester,"SY",id);
		}
		else if(semester.equals("Semester 4"))
		{
			showInternalMarksByID(semester,"SY",id);
		}
		else if(semester.equals("Semester 5"))
		{
			showInternalMarksByID(semester,"TY",id);
		}
		else if(semester.equals("Semester 6"))
		{
			showInternalMarksByID(semester,"TY",id);
		}
	}
	private void getSubName(String semester)
	{
		int semNumber = getSemNumber(semester);
		String subOne=null,subTwo=null,subThree=null,subFour=null,subFive=null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("select * from SubjectNameTable where ID ="+semNumber);
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                subOne = rs.getString(2);
                subTwo = rs.getString(3);
                subThree = rs.getString(4);
				subFour = rs.getString(5);
				subFive = rs.getString(6);
                i++;
            }

            if (i < 1) {
               JOptionPane.showMessageDialog(null,"No records present.");
            }
			setSubjectName(subOne,subTwo,subThree,subFour,subFive);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
		
	}
	private void setSubjectName(String subOne,String subTwo,String subThree,String subFour,String subFive)
	{
		if(subFive != null){
			tableFySy.getColumnModel().getColumn(5).setHeaderValue(subOne);
			tableFySy.getColumnModel().getColumn(6).setHeaderValue(subTwo);
			tableFySy.getColumnModel().getColumn(7).setHeaderValue(subThree);
			tableFySy.getColumnModel().getColumn(8).setHeaderValue(subFour);
			tableFySy.getColumnModel().getColumn(9).setHeaderValue(subFive);
			tableFySy.getTableHeader().repaint();
		}
		else
		{
			tableTy.getColumnModel().getColumn(5).setHeaderValue(subOne);
			tableTy.getColumnModel().getColumn(6).setHeaderValue(subTwo);
			tableTy.getColumnModel().getColumn(7).setHeaderValue(subThree);
			tableTy.getColumnModel().getColumn(8).setHeaderValue(subFour);
			tableTy.getTableHeader().repaint();
		}
	}	
	private int getSemNumber(String semester)
	{ 
		int semNumber = -1;
		if(semester.equals("Semester 1"))
		{	
			semNumber = 1;	
		}
		else if(semester.equals("Semester 2"))
		{
			semNumber = 2;	
		}
		else if(semester.equals("Semester 3"))
		{
			semNumber = 3;	
		}
		else if(semester.equals("Semester 4"))
		{
			semNumber = 4;	
		}
		else if(semester.equals("Semester 5"))
		{
			semNumber = 5;	
		}
		else if(semester.equals("Semester 6"))
		{
			semNumber = 6;	
		}
		return semNumber;
	}
	public static void main(String args[])
	{
		new ViewStudensInternalMarks();
	}
}