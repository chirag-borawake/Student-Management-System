package student;
import student.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

class TermWork extends JFrame {
	Connection con= null;
	PreparedStatement pst = null;
	ResultSet rs;
    Statement st;
	PreparedStatement ps = null;
	String sql,rollNo,firstName,lastName,year;
	JFrame student;
	JLabel labSubFirst,labSubSecond,labSubThird,labSubFourth,labSubFifth;
	JTextField txtSubOne,txtSubTwo,txtSubThree,txtSubFour,txtSubFive;
	JCheckBox chkAssignmentSubOne,chkAssignmentSubTwo,chkAssignmentSubThree,chkAssignmentSubFour,chkAssignmentSubFive;
	TermWork(String exam,String semester,int ID){
		final  String  damyExam = exam;
		final String  damySemester = semester;
		final int  damyID = ID;
		student =  new JFrame("Student Management System");
		student.setLayout(null);
		
		JButton butViewStudens = new JButton("View");
		butViewStudens.setBounds(10,20,116,26);
		butViewStudens.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewStudens);
		
		JButton butDeletetStudent = new JButton("Delete");
		butDeletetStudent.setBounds(10,60,116,26);
		butDeletetStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butDeletetStudent);
		
		JButton butAddStudent = new JButton("Add");
		butAddStudent.setBounds(135,20,125,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);
		
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
		
		JButton butChangeSubjectName = new JButton("Change Subject Name");
		butChangeSubjectName.setBounds(60,670,200,26);
		butChangeSubjectName.setFont(new Font("Serif", Font.PLAIN, 18));
		student.add(butChangeSubjectName);
		
		JButton butSubmit = new JButton("Submit");
		butSubmit.setBounds(380,670,110,25);
		butSubmit.setFont(new Font("Serif", Font.PLAIN, 18));
		student.add(butSubmit);
		
		JLabel labExam= new JLabel(exam);
		labExam.setBounds(215,110,300,25);
		labExam.setFont(new Font("Serif", Font.PLAIN, 30));
		student.add(labExam);
		
		JLabel labRollNoTitle= new JLabel("Roll No",SwingConstants.RIGHT);
		labRollNoTitle.setBounds(170,160,111,25);
		labRollNoTitle.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labRollNoTitle);

		JLabel labFirstNameTitle= new JLabel("First Name",SwingConstants.RIGHT);
		labFirstNameTitle.setBounds(170,210,111,25);
		labFirstNameTitle.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labFirstNameTitle);
		
		
		JLabel labLastNameTitle= new JLabel("Last Name",SwingConstants.RIGHT);
		labLastNameTitle.setBounds(170,260,111,25);
		labLastNameTitle.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labLastNameTitle);
		
		JLabel labYearTitle= new JLabel("Year",SwingConstants.RIGHT);
		labYearTitle.setBounds(170,310,111,25);
		labYearTitle.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labYearTitle);
	
		JLabel labSubjectName= new JLabel("Subject names");
		labSubjectName.setBounds(90,370,180,25);
		labSubjectName.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labSubjectName);
		
		JLabel labMarks= new JLabel("Marks");
		labMarks.setBounds(280,370,80,25);
		labMarks.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labMarks);
		
		JLabel labAssignment= new JLabel("Assignment");
		labAssignment.setBounds(380,370,130,25);
		labAssignment.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labAssignment);
		
		labSubFirst = new JLabel();
		labSubFirst.setBounds(20,420,200,25);
		labSubFirst.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubFirst.setHorizontalAlignment(SwingConstants.RIGHT);
		labSubFirst.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubFirst);
		
		labSubSecond = new JLabel();
		labSubSecond.setBounds(20,470,200,25);
		labSubSecond.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubSecond.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubSecond);

		labSubThird = new JLabel();
		labSubThird.setBounds(20,520,200,25);
		labSubThird.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubThird.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubThird);
		
		labSubFourth = new JLabel();
		labSubFourth.setBounds(20,570,200,25);
		labSubFourth.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubFourth.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubFourth);
		
		labSubFifth = new JLabel();
		labSubFifth.setBounds(20,620,200,25);
		labSubFifth.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubFifth.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubFifth);
		
		JLabel labRollNo= new JLabel();
		labRollNo.setBounds(300,160,111,25);
		labRollNo.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labRollNo);

		JLabel labFirstName= new JLabel();
		labFirstName.setBounds(300,210,111,25);
		labFirstName.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labFirstName);
		
		JLabel labLastName= new JLabel();
		labLastName.setBounds(300,260,111,25);
		labLastName.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labLastName);
		
		JLabel labYear= new JLabel();
		labYear.setBounds(300,310,111,25);
		labYear.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(labYear);
		
		txtSubOne= new JTextField(){
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
		txtSubOne.setBounds(280,420,70,25);
		student.add(txtSubOne);
		
		txtSubTwo= new JTextField(){
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
		txtSubTwo.setBounds(280,470,70,25);
		student.add(txtSubTwo);
		
		txtSubThree= new JTextField(){
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
		txtSubThree.setBounds(280,520,70,25);
		student.add(txtSubThree);
		
		txtSubFour= new JTextField(){
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
		txtSubFour.setBounds(280,570,70,25);
		student.add(txtSubFour);
		
		txtSubFive= new JTextField(){
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
		txtSubFive.setBounds(280,620,70,25);
		student.add(txtSubFive);
		
		chkAssignmentSubOne = new JCheckBox();
		chkAssignmentSubOne.setBounds(420,420,20,25);
		chkAssignmentSubOne.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(chkAssignmentSubOne);
		
		chkAssignmentSubTwo = new JCheckBox();
		chkAssignmentSubTwo.setBounds(420,470,20,25);
		chkAssignmentSubTwo.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(chkAssignmentSubTwo);
		
		chkAssignmentSubThree = new JCheckBox();
		chkAssignmentSubThree.setBounds(420,520,20,25);
		chkAssignmentSubThree.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(chkAssignmentSubThree);
		
		chkAssignmentSubFour = new JCheckBox();
		chkAssignmentSubFour.setBounds(420,570,20,25);
		chkAssignmentSubFour.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(chkAssignmentSubFour);
		
		chkAssignmentSubFive = new JCheckBox();
		chkAssignmentSubFive.setBounds(420,620,20,25);
		chkAssignmentSubFive.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(chkAssignmentSubFive);
		
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
		
			butGetTermWork.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ShowTermWork();
				}
			});	
			butDeletetStudent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new DeleteStudent();
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
			butChangeSubjectName.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae)
					{
						JTextField subNoOne = new JTextField();
						JTextField SubNoTow = new JTextField();
						JTextField subNoThree = new JTextField();
						JTextField subNoFour = new JTextField();
						JTextField subNoFive = new JTextField();
						Object[] message = {
							"First Subject:", subNoOne,
							"Second Subject:", SubNoTow,
							"Third Subject:", subNoThree,
							"Fourth Subject:", subNoFour,
							"Fifth Subject:", subNoFive
							};
						int option = JOptionPane.showConfirmDialog(null, message, "Change Subject Name ", JOptionPane.OK_CANCEL_OPTION);
						if (option == JOptionPane.OK_OPTION) {

						String subOne=subNoOne.getText();
						String subTwo=SubNoTow.getText();
						String subThree=subNoThree.getText();
						String subFour=subNoFour.getText();
						String subFive=subNoFive.getText();
						changeSubjectName(damySemester,subOne,subTwo,subThree,subFour,subFive);
						setSubjectName(subOne,subTwo,subThree,subFour,subFive);
						}
					}
			});	
			
		butSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				int semNumber = getSemNumber(damySemester);
				String firstName=null,lastName=null,year=null;
				int rollNo=-1;
				try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:Student");
				st = con.createStatement();
				pst = con.prepareStatement("select * from StudentTable where studentID ="+damyID);
				ResultSet rs = pst.executeQuery();
				int i = 0;	
				while (rs.next()) {
					rollNo = rs.getInt(2);
					firstName = rs.getString(3);
					lastName = rs.getString(4);
					year = rs.getString(9);
					i++;
				}
				if (i < 1) {
					JOptionPane.showMessageDialog(null,"No records present.");
				}
				if (i == 1) {
					System.out.println(i + " Record Found");
				} 
				else {
					System.out.println(i + " Records Found");
				}
				
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
				}finally {
						try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
						try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
		
				System.out.println( " Records Found"+semNumber );
				if(semNumber == 1 || semNumber == 2)
				{
					saveFirstYearData(damyID,damyExam,rollNo,firstName,lastName,year,damySemester);
					student.dispose();
					new SetTermWork();
				}
				else if(semNumber == 3 || semNumber == 4)
				{
					saveSecondYearData(damyID,damyExam,rollNo,firstName,lastName,year,damySemester);
					student.dispose();
					new SetTermWork();
				}
				else if(semNumber == 5 || semNumber == 6)
				{
					saveThirdYearData(damyID,damyExam,rollNo,firstName,lastName,year,damySemester);		
					student.dispose();
					new SetTermWork();
				}
			}
		});
	
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("select * from StudentTable where studentID ="+ID);
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                rollNo = rs.getString(2);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				year = rs.getString(9);
                i++;
            }
			labRollNo.setText(rollNo);
			labFirstName.setText(firstName);
			labLastName.setText(lastName);
			labYear.setText(year);
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
		getSubName(damySemester);
		student.setSize(555,750);
		student.setVisible(true);
		student.setResizable(false);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void changeSubjectName(String semester,String subOne,String subTwo,String subThree,String subFour,String subFive)
	{
		int semNumber = getSemNumber(semester);
			try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con = DriverManager.getConnection("jdbc:odbc:Student");
			st = con.createStatement();
			String sql = "update SubjectNameTable set subOne = '"+subOne+"', subTwo = '"+subTwo+"', subThree = '"+subThree+"' ,subFour = '"+subFour+"' , subFive = '"+subFive+"' where ID = "+semNumber;
			st.executeUpdate(sql);
			getSubName(semester);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }finally {
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
	}
	private void getSubName(String semester)
	{
		int semNumber = getSemNumber(semester);
		// semNumber = 2;
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
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }
			con.close();
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
		labSubFirst.setText(subOne);
		labSubFirst.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labSubSecond.setText(subTwo);
		labSubSecond.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labSubThird.setText(subThree);
		labSubThird.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labSubFourth.setText(subFour);
		labSubFourth.setHorizontalAlignment(SwingConstants.RIGHT);
		
		labSubFifth.setText(subFive);
		labSubFifth.setHorizontalAlignment(SwingConstants.RIGHT);
		if(subFive != null)
		{
			txtSubFive.setVisible(true);
			chkAssignmentSubFive.setVisible(true);
			if(subFive.length() == 0){
				System.out.println(subFive.length());
				txtSubFive.setVisible(false);
				chkAssignmentSubFive.setVisible(false);
			}
		}
		else{
			txtSubFive.setVisible(false);
			chkAssignmentSubFive.setVisible(false);
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
	private void saveFirstYearData(int damyID,String damyExam ,int rollNo,String firstName,String lastName,String year,String semester){
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:Student");
				ps = con.prepareStatement("insert into      FY_TermWorkTable(studentID,rollNo,firstName,lastName,exam,yearFySyTy,semester,subOne,subTwo,subThree,subFour,subFive,subOneAssignment,subTwoAssignment,subThreeAssignment,subFourAssignment,subFiveAssignment)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1,damyID);
					ps.setInt(2,rollNo);
					ps.setString(3,firstName);
					ps.setString(4,lastName);
					ps.setString(5,damyExam);
					ps.setString(6,year);
					ps.setString(7,semester);
					ps.setString(8,txtSubOne.getText());
					ps.setString(9,txtSubTwo.getText());
					ps.setString(10,txtSubThree.getText());
					ps.setString(11,txtSubFour.getText());
					ps.setString(12,txtSubFive.getText());
					if( chkAssignmentSubOne.isSelected()){
						ps.setString(13,"YES");
					}
					else{
						ps.setString(13,"NO");
					}
					if( chkAssignmentSubTwo.isSelected()){
						ps.setString(14,"YES");
					}
					else{
						ps.setString(14,"NO");
					}
					if( chkAssignmentSubThree.isSelected()){
						ps.setString(15,"YES");
					}
					else{
						ps.setString(15,"NO");
					}
					if( chkAssignmentSubFour.isSelected()){
						ps.setString(16,"YES");
					}
					else{
						ps.setString(16,"NO");
					}
					if( chkAssignmentSubFive.isSelected()){
						ps.setString(17,"YES");
					}
					else{
						ps.setString(17,"NO");
					}
					JOptionPane.showMessageDialog(null,"Record inserted.");
				ps.executeUpdate();
				}catch(Exception ex){
					System.out.println("\n"+ex);
				} finally {
					try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
						
			}
		private void saveSecondYearData(int damyID,String damyExam ,int rollNo,String firstName, String lastName,String year,String semester){
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:Student");
				ps = con.prepareStatement("insert into      SY_TermWorkTable(studentID,rollNo,firstName,lastName,exam,yearFySyTy,semester,subOne,subTwo, subThree,subFour,subFive,subOneAssignment,subTwoAssignment,subThreeAssignment,subFourAssignment,subFiveAssignment)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1,damyID);
					ps.setInt(2,rollNo);
					ps.setString(3,firstName);
					ps.setString(4,lastName);
					ps.setString(5,damyExam);
					ps.setString(6,year);
					ps.setString(7,semester);
					ps.setString(8,txtSubOne.getText());
					ps.setString(9,txtSubTwo.getText());
					ps.setString(10,txtSubThree.getText());
					ps.setString(11,txtSubFour.getText());
					ps.setString(12,txtSubFive.getText());
					if( chkAssignmentSubOne.isSelected()){
						ps.setString(13,"YES");
					}
					else{
						ps.setString(13,"NO");
					}
					if( chkAssignmentSubTwo.isSelected()){
						ps.setString(14,"YES");
					}
					else{
						ps.setString(14,"NO");
					}
					if( chkAssignmentSubThree.isSelected()){
						ps.setString(15,"YES");
					}
					else{
						ps.setString(15,"NO");
					}
					if( chkAssignmentSubFour.isSelected()){
						ps.setString(16,"YES");
					}
					else{
						ps.setString(16,"NO");
					}
					if( chkAssignmentSubFive.isSelected()){
						ps.setString(17,"YES");
					}
					else{
						ps.setString(17,"NO");
					}
					JOptionPane.showMessageDialog(null,"Record inserted.");
				ps.executeUpdate();
				}catch(Exception ex){
					System.out.println("\n"+ex);
				} finally {
					try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
						
			}
			
		private void saveThirdYearData(int damyID,String damyExam ,int rollNo,String firstName, String lastName,String year,String semester){
		try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:Student");
				ps = con.prepareStatement("insert into      TY_TermWorkTable(studentID,rollNo,firstName,lastName,exam,yearFySyTy,semester,subOne,subTwo, subThree,subFour,subOneAssignment,subTwoAssignment,subThreeAssignment,subFourAssignment)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1,damyID);
					ps.setInt(2,rollNo);
					ps.setString(3,firstName);
					ps.setString(4,lastName);
					ps.setString(5,damyExam);
					ps.setString(6,year);
					ps.setString(7,semester);
					ps.setString(8,txtSubOne.getText());
					ps.setString(9,txtSubTwo.getText());
					ps.setString(10,txtSubThree.getText());
					ps.setString(11,txtSubFour.getText());
					if( chkAssignmentSubOne.isSelected()){
						ps.setString(12,"YES");
					}
					else{
						ps.setString(12,"NO");
					}
					if( chkAssignmentSubTwo.isSelected()){
						ps.setString(13,"YES");
					}
					else{
						ps.setString(13,"NO");
					}
					if( chkAssignmentSubThree.isSelected()){
						ps.setString(14,"YES");
					}
					else{
						ps.setString(14,"NO");
					}
					if( chkAssignmentSubFour.isSelected()){
						ps.setString(15,"YES");
					}
					else{
						ps.setString(15,"NO");
					}
					JOptionPane.showMessageDialog(null,"Record inserted.");
				ps.executeUpdate();
				}catch(Exception ex){
					System.out.println("\n"+ex);
				} finally {
					try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
						
			}

	public static void main(String args[])
	{
		new TermWork("String exam","Semester 1",28);
	}
}


