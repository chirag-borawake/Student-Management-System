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

class ShowTermWork extends JFrame implements ItemListener{	
    JFrame student;
    Connection con;
    ResultSet rs;
    Statement st;
    PreparedStatement pst;
	
	Checkbox sem1,sem2,sem3,sem4,sem5,sem6;
	Checkbox marks,assignment;
	CheckboxGroup checkGroupSem ,marksAssignmentGroup;
    static JTable tableFySyMarks,tableTyMarks;
	JScrollPane scrollFySyMarks,scrollTyMarks; 
	
	static JTable tableFySyAssignment,tableTyAssignment;
	JScrollPane scrollFySyAssignment,scrollTyAssignment; 
	int ID;
	int rollNo;
    DefaultTableModel modelFySyMarks = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
    DefaultTableModel modeTyMarks = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};

	DefaultTableModel modelFySyAssignment = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
    DefaultTableModel modeTyAssignment = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	String firstName,lastName,year,exam,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks, subFiveMarks;
    String[] columnNames = {"ID","Roll no", "First Name", "Last Name","Exam","Year","subOneMarks","subTwoMarks","subThreeMarks","subFourMarks","subFiveMarks"};
	
	String[] thirdYearcolumnNames = {"ID","Roll no", "First Name", "Last Name","Exam","Year","subOneMarks","subTwoMarks","subThreeMarks","subFourMarks"};
	
	String[] columnNamesAssignment = {"ID","Roll no", "First Name", "Last Name","Year","subOneMarks","subTwoMarks","subThreeMarks","subFourMarks","subFiveMarks"};
	
	String[] thirdYearColumnNamesAssignment = {"ID","Roll no", "First Name", "Last Name","Year","subOneMarks","subTwoMarks","subThreeMarks","subFourMarks"};
	
	TableColumn subOne = null;
	TableColumn subTwo = null;
	TableColumn subThree = null;
	TableColumn subFour = null;
	TableColumn subFive = null;
	ShowTermWork(){	
		student = new JFrame("Student Management System");
        student.setLayout(null);
        modelFySyMarks.setColumnIdentifiers(columnNames);
        modeTyMarks.setColumnIdentifiers(thirdYearcolumnNames);
        tableFySyMarks = new JTable();
        tableFySyMarks.setModel(modelFySyMarks);
		tableFySyMarks.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableFySyMarks.setFillsViewportHeight(true);
		
		tableFySyMarks.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 15 ));
		tableFySyMarks.setFont(new Font("Rockwell", Font.PLAIN, 14));
		

		tableTyMarks = new JTable();
        tableTyMarks.setModel(modeTyMarks);
		tableTyMarks.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableTyMarks.setFillsViewportHeight(true);
		
		tableTyMarks.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 15 ));
		tableTyMarks.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		subOne = tableTyMarks.getColumnModel().getColumn(6);
		subOne.setPreferredWidth(140);
		
		subTwo = tableTyMarks.getColumnModel().getColumn(7);
		subTwo.setPreferredWidth(140);
		
		subThree = tableTyMarks.getColumnModel().getColumn(8);
		subThree.setPreferredWidth(140);
		
		subFour = tableTyMarks.getColumnModel().getColumn(9);
		subFour.setPreferredWidth(140);
		
		subOne = tableFySyMarks.getColumnModel().getColumn(6);
		subOne.setPreferredWidth(140);
		
		subTwo = tableFySyMarks.getColumnModel().getColumn(7);
		subTwo.setPreferredWidth(140);
		
		subThree = tableFySyMarks.getColumnModel().getColumn(8);
		subThree.setPreferredWidth(140);
		
		subFour = tableFySyMarks.getColumnModel().getColumn(9);
		subFour.setPreferredWidth(140);
			
		subFive = tableFySyMarks.getColumnModel().getColumn(10);
		subFive.setPreferredWidth(140);
			
        scrollFySyMarks = new JScrollPane(tableFySyMarks);
        scrollFySyMarks.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollFySyMarks.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollFySyMarks.setBounds(40,100,1200,600);
				
		scrollTyMarks = new JScrollPane(tableTyMarks);
        scrollTyMarks.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTyMarks.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollTyMarks.setBounds(40,100,1200,600);
		
		
		modelFySyAssignment.setColumnIdentifiers(columnNamesAssignment);
        modeTyAssignment.setColumnIdentifiers(thirdYearColumnNamesAssignment);
        tableFySyAssignment = new JTable();
        tableFySyAssignment.setModel(modelFySyAssignment);
		tableFySyAssignment.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableFySyAssignment.setFillsViewportHeight(true);
		
		tableFySyAssignment.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 15 ));
		tableFySyAssignment.setFont(new Font("Rockwell", Font.PLAIN, 14));
		

		tableTyAssignment = new JTable();
        tableTyAssignment.setModel(modeTyAssignment);
		tableTyAssignment.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableTyAssignment.setFillsViewportHeight(true);
		
		tableTyAssignment.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 15 ));
		tableTyAssignment.setFont(new Font("Rockwell", Font.PLAIN, 14));
		
		subOne = tableTyAssignment.getColumnModel().getColumn(5);
		subOne.setPreferredWidth(140);
		
		subTwo = tableTyAssignment.getColumnModel().getColumn(6);
		subTwo.setPreferredWidth(140);
		
		subThree = tableTyAssignment.getColumnModel().getColumn(7);
		subThree.setPreferredWidth(140);
		
		subFour = tableTyAssignment.getColumnModel().getColumn(8);
		subFour.setPreferredWidth(140);
		
		subOne = tableFySyAssignment.getColumnModel().getColumn(5);
		subOne.setPreferredWidth(140);
		
		subTwo = tableFySyAssignment.getColumnModel().getColumn(6);
		subTwo.setPreferredWidth(140);
		
		subThree = tableFySyAssignment.getColumnModel().getColumn(7);
		subThree.setPreferredWidth(140);
		
		subFour = tableFySyAssignment.getColumnModel().getColumn(8);
		subFour.setPreferredWidth(140);
			
		subFive = tableFySyAssignment.getColumnModel().getColumn(9);
		subFive.setPreferredWidth(140);
			
        scrollFySyAssignment = new JScrollPane(tableFySyAssignment);
        scrollFySyAssignment.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollFySyAssignment.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollFySyAssignment.setBounds(40,100,1200,600);
				
		scrollTyAssignment = new JScrollPane(tableTyAssignment);
        scrollTyAssignment.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollTyAssignment.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scrollTyAssignment.setBounds(40,100,1200,600);
		
		
		
		marks = new Checkbox("Marks",true);
		marks.setBounds(40,57,100,28);
		marks.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(marks);
		
		
		assignment = new Checkbox("Assignment");
		assignment.setBounds(180,57,130,28);
		assignment.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(assignment);
		
		
		sem1 = new Checkbox("Semester 1",true);
		sem1.setBounds(346,60,100,26);
		sem1.setFont(new Font("Serif", Font.PLAIN, 17));
		student.add(sem1);
		
		sem2 = new Checkbox("Semester 2");
		sem2.setBounds(482,60,100,26);
		sem2.setFont(new Font("Serif", Font.PLAIN, 17));
		student.add(sem2);
		
		sem3 = new Checkbox("Semester 3");
		sem3.setBounds(618,60,100,26);
		sem3.setFont(new Font("Serif", Font.PLAIN, 17));
		student.add(sem3);
		
		sem4 = new Checkbox("Semester 4");
		sem4.setBounds(754,60,100,26);
		sem4.setFont(new Font("Serif", Font.PLAIN, 17));
		student.add(sem4);
		
		sem5 = new Checkbox("Semester 5");
		sem5.setBounds(893,60,100,26);
		sem5.setFont(new Font("Serif", Font.PLAIN, 17));
		student.add(sem5);
		
		sem6 = new Checkbox("Semester 6");
		sem6.setBounds(1030,60,100,26);
		sem6.setFont(new Font("Serif", Font.PLAIN, 17));
		student.add(sem6);
		
		
		marksAssignmentGroup = new CheckboxGroup();
		
		marks.setCheckboxGroup(marksAssignmentGroup);
		assignment.setCheckboxGroup(marksAssignmentGroup);
		
		marks.addItemListener(this);
        assignment.addItemListener(this);
		
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
			pst = con.prepareStatement("select * from FY_TermWorkTable where yearFySyTy = 'FY' and semester = 'Semester 1'");
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				exam = rs.getString(5);
                year = rs.getString(6);
				subOneMarks = rs.getString(8);
                subTwoMarks = rs.getString(9);
                subThreeMarks = rs.getString(10);
                subFourMarks = rs.getString(11);
                subFiveMarks = rs.getString(12);
                modelFySyMarks.addRow(new Object[]{ID,rollNo, firstName, lastName,exam,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks,subFiveMarks});
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
		butAddStudent.setBounds(40,15,116,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);
	
		JButton butViewStudens = new JButton("View");
		butViewStudens.setBounds(191,15,116,26);
		butViewStudens.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewStudens);
		
		JButton butDeletetStudent = new JButton("Delete");
		butDeletetStudent.setBounds(344,15,116,26);
		butDeletetStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butDeletetStudent);
		
		JButton butSetTermWork = new JButton("Set Term Work");
		butSetTermWork.setBounds(493,15,125,26);
		butSetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetTermWork);
		
			
		JButton butViewAttendance = new JButton("Get Attendance");
		butViewAttendance.setBounds(644,15,125,26);
		butViewAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewAttendance);
		
		JButton butSetAttendance = new JButton("Set Attendance");
		butSetAttendance.setBounds(795,15,125,26);
		butSetAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetAttendance);
		
		JButton butSetInternalMarks = new JButton("Set Internal Marks");
		butSetInternalMarks.setBounds(940,15,141,26);
		butSetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetInternalMarks);
		
		JButton butGetInternalMarks = new JButton("Get Internal Marks");
		butGetInternalMarks.setBounds(1097,15,141,26);
		butGetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetInternalMarks);
		
		
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
			
		butGetInternalMarks.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewStudensInternalMarks();
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
				student.dispose();
				new GetTermWorkByID();
				}
			});
		student.add(scrollFySyMarks);
		student.add(scrollTyMarks);
		student.add(scrollFySyAssignment);
		student.add(scrollTyAssignment);
        student.setVisible(true);
		student.setResizable(false);
        student.setSize(1280, 750);
        student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void itemStateChanged(ItemEvent ie)
    {
		Checkbox chkSemester = checkGroupSem.getSelectedCheckbox();
		String semester=chkSemester.getLabel();
		
		Checkbox chkMarksAssignment = marksAssignmentGroup.getSelectedCheckbox();
		String marksAssignment = chkMarksAssignment.getLabel();
		if(marksAssignment.equals("Marks"))
		{
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
		else{
			if(semester.equals("Semester 1"))
			{
				showAssignment(semester,"FY");
			}
			else if(semester.equals("Semester 2"))
			{
				showAssignment(semester,"FY");
			}
			else if(semester.equals("Semester 3"))
			{
				showAssignment(semester,"SY");
			}
			else if(semester.equals("Semester 4"))
			{
				showAssignment(semester,"SY");
			}
			else if(semester.equals("Semester 5"))
			{
				showAssignment(semester,"TY");
			}
			else if(semester.equals("Semester 6"))
			{
				showAssignment(semester,"TY");
			}
		}
    }
	private void showInternalMarks(String semester,String year )
	{	
		while (modelFySyMarks.getRowCount()>0)
          {
             modelFySyMarks.removeRow(0);
          }
		  while (modeTyMarks.getRowCount()>0)
          {
             modeTyMarks.removeRow(0);
          }
		getSubName(semester);
        try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			if(year.equals("FY"))
			{
				pst = con.prepareStatement("select * from FY_TermWorkTable where semester = '"+semester+"'");
			}
			else if(year.equals("SY"))
			{
				pst = con.prepareStatement("select * from SY_TermWorkTable where semester = '"+semester+"'");
			}
			else 
			{
				pst = con.prepareStatement("select * from TY_TermWorkTable where  semester = '"+semester+"'");
				
			}
		
            ResultSet rs = pst.executeQuery();
            int i = 0;	
           while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				exam = rs.getString(5);
                year = rs.getString(6);
				subOneMarks = rs.getString(8);
                subTwoMarks = rs.getString(9);
                subThreeMarks = rs.getString(10);
                subFourMarks = rs.getString(11);
                subFiveMarks = rs.getString(12);
               
				if(!year.equals("TY")){
					scrollFySyMarks.setVisible(true);
					scrollTyMarks.setVisible(false);
					scrollTyAssignment.setVisible(false);
					scrollFySyAssignment.setVisible(false);
					modelFySyMarks.addRow(new Object[]{ID,rollNo, firstName, lastName,exam,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks,subFiveMarks});
				}
				else{
					scrollTyMarks.setVisible(true);
					scrollFySyMarks.setVisible(false);
					scrollTyAssignment.setVisible(false);
					scrollFySyAssignment.setVisible(false);
					modeTyMarks.addRow(new Object[]{ID,rollNo, firstName, lastName,exam,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks});
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
		while (modelFySyMarks.getRowCount()>0)
          {
             modelFySyMarks.removeRow(0);
          }
		  while (modeTyMarks.getRowCount()>0)
          {
             modeTyMarks.removeRow(0);
          }
		getSubName(semester);
        try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			if(year.equals("FY"))
			{
				pst = con.prepareStatement("select * from FY_TermWorkTable where semester = '"+semester+"' and id = "+id+"");
			}
			else if(year.equals("SY"))
			{
				pst = con.prepareStatement("select * from SY_TermWorkTable where semester = '"+semester+"' and id = "+id+"");
			}
			else 
			{
				pst = con.prepareStatement("select * from TY_TermWorkTable where semester = '"+semester+"' and id = "+id+"");
				
			}
		
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				exam = rs.getString(5);
                year = rs.getString(6);
				subOneMarks = rs.getString(13);
                subTwoMarks = rs.getString(14);
                subThreeMarks = rs.getString(15);
                subFourMarks = rs.getString(16);
                subFiveMarks = rs.getString(17);
				if(!year.equals("TY")){
					scrollFySyMarks.setVisible(true);
					scrollTyMarks.setVisible(false);
					scrollTyAssignment.setVisible(false);
					scrollFySyAssignment.setVisible(false);
					modelFySyMarks.addRow(new Object[]{ID,rollNo, firstName, lastName,exam,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks,subFiveMarks});
				}
				else{
					scrollTyMarks.setVisible(true);
					scrollFySyMarks.setVisible(false);
					scrollTyAssignment.setVisible(false);
					scrollFySyAssignment.setVisible(false);
					modeTyMarks.addRow(new Object[]{ID,rollNo, firstName, lastName,exam,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks});
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
	private void showAssignment(String semester,String year )
	{	
		while (modelFySyAssignment.getRowCount()>0)
          {
             modelFySyAssignment.removeRow(0);
          }
		  while (modeTyAssignment.getRowCount()>0)
          {
             modeTyAssignment.removeRow(0);
          }
		getSubName(semester);
        try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			if(year.equals("FY"))
			{
				pst = con.prepareStatement("select * from FY_TermWorkTable where semester = '"+semester+"'");
			}
			else if(year.equals("SY"))
			{
				pst = con.prepareStatement("select * from SY_TermWorkTable where semester = '"+semester+"'");
			}
			else 
			{
				pst = con.prepareStatement("select * from TY_TermWorkTable where  semester = '"+semester+"'");
				
			}
		
            ResultSet rs = pst.executeQuery();
            int i = 0;	
           while (rs.next()) {
                ID = rs.getInt(1);
                rollNo = rs.getInt(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
                year = rs.getString(6);
				
				if(!year.equals("TY")){
					subOneMarks = rs.getString(13);
					subTwoMarks = rs.getString(14);
					subThreeMarks = rs.getString(15);
					subFourMarks = rs.getString(16);
					subFiveMarks = rs.getString(17);
					scrollFySyAssignment.setVisible(true);
					scrollTyAssignment.setVisible(false);
					scrollFySyMarks.setVisible(false);
					scrollTyMarks.setVisible(false);
					modelFySyAssignment.addRow(new Object[]{ID,rollNo, firstName, lastName,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks,subFiveMarks});
				}
				else{
					subOneMarks = rs.getString(12);
					subTwoMarks = rs.getString(13);
					subThreeMarks = rs.getString(14);
					subFourMarks = rs.getString(15);
					scrollTyAssignment.setVisible(true);
					scrollFySyAssignment.setVisible(false);
					scrollFySyMarks.setVisible(false);
					scrollTyMarks.setVisible(false);
					modeTyAssignment.addRow(new Object[]{ID,rollNo, firstName, lastName,year,subOneMarks,subTwoMarks,subThreeMarks,subFourMarks});
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
			tableFySyMarks.getColumnModel().getColumn(6).setHeaderValue(subOne);
			tableFySyMarks.getColumnModel().getColumn(7).setHeaderValue(subTwo);
			tableFySyMarks.getColumnModel().getColumn(8).setHeaderValue(subThree);
			tableFySyMarks.getColumnModel().getColumn(9).setHeaderValue(subFour);
			tableFySyMarks.getColumnModel().getColumn(10).setHeaderValue(subFive);
			
			tableFySyMarks.getTableHeader().repaint();
			
			tableFySyAssignment.getColumnModel().getColumn(5).setHeaderValue(subOne);
			tableFySyAssignment.getColumnModel().getColumn(6).setHeaderValue(subTwo);
			tableFySyAssignment.getColumnModel().getColumn(7).setHeaderValue(subThree);
			tableFySyAssignment.getColumnModel().getColumn(8).setHeaderValue(subFour);
			tableFySyAssignment.getColumnModel().getColumn(9).setHeaderValue(subFive);
			
			tableFySyAssignment.getTableHeader().repaint();
		}
		else
		{
			tableTyMarks.getColumnModel().getColumn(6).setHeaderValue(subOne);
			tableTyMarks.getColumnModel().getColumn(7).setHeaderValue(subTwo);
			tableTyMarks.getColumnModel().getColumn(8).setHeaderValue(subThree);
			tableTyMarks.getColumnModel().getColumn(9).setHeaderValue(subFour);
			tableTyMarks.getTableHeader().repaint();
			
			tableTyAssignment.getColumnModel().getColumn(5).setHeaderValue(subOne);
			tableTyAssignment.getColumnModel().getColumn(6).setHeaderValue(subTwo);
			tableTyAssignment.getColumnModel().getColumn(7).setHeaderValue(subThree);
			tableTyAssignment.getColumnModel().getColumn(8).setHeaderValue(subFour);
			tableTyAssignment.getTableHeader().repaint();
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
		new ShowTermWork();
	}
}