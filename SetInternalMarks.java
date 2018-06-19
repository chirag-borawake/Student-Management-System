package student;
import student.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;
class SetInternalMarks extends JFrame {
	Connection con= null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	Statement st =null;
	String sql;
	JFrame student;
	JProgressBar pb;
	int attendace90AndAbove;
	int attendace50to79 ;
	int attendaceBelow50;
	int UTMarks20AndAbove;
	int UTMarks12to19;
	int UTMarksBelow12;
	int PMarks60AndAbove;
	int PMarks33to59 ;
	int PMarksBelow33;
	int assignmentMarks;
	int attendanceMarks = -1;
	JTextField txt90AndAbove,txt50to79,txtBelow50,txt20AndAbove,txt12to19,txtBelow12,txt60AndAbove,txt33to59,txtBelow33,txtAssignment;
	SetInternalMarks(){	
		student =  new JFrame("Student Management System");
		student.setLayout(null);
		
		pb=new JProgressBar(1,100);
		pb.setValue(0);
		pb.setStringPainted(true);
		pb.setForeground(Color.blue);   
		pb.setBackground(Color.white); 
		pb.setBounds(50,670,270,25);
		pb.setVisible(true);
		student.add(pb);             

				 
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
			
		JButton butViewAttendance = new JButton("Get Attendance");
		butViewAttendance.setBounds(270,60,121,26);
		butViewAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewAttendance);
		
		JButton butSetAttendance = new JButton("Set Attendance");
		butSetAttendance.setBounds(270,20,121,26);
		butSetAttendance.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetAttendance);
		
		JButton butAddStudent = new JButton("Add");
		butAddStudent.setBounds(400,20,141,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);
		
		JButton butGetInternalMarks = new JButton("Get Internal Marks");
		butGetInternalMarks.setBounds(400,60,141,26);
		butGetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetInternalMarks);
		
		JButton butSubmit = new JButton("Submit");
		butSubmit.setBounds(350,670,100,25);
		butSubmit.setFont(new Font("Serif", Font.PLAIN, 18));
		student.add(butSubmit);
		
				
		
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
			
			butViewAttendance.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewAttendance();
				}
			});	
			
			butGetInternalMarks.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewStudensInternalMarks();
				}
			});
	
			butSubmit.addActionListener(new ActionListener(){
			int i =0;
					public void actionPerformed(ActionEvent ae){
						attendace90AndAbove = Integer.parseInt(txt90AndAbove.getText());
						attendace50to79 = Integer.parseInt(txt50to79.getText());
						attendaceBelow50 = Integer.parseInt(txtBelow50.getText());
						UTMarks20AndAbove = Integer.parseInt(txt20AndAbove.getText());
						UTMarks12to19 = Integer.parseInt(txt12to19.getText());
						UTMarksBelow12 = Integer.parseInt(txtBelow12.getText());
						PMarks60AndAbove = Integer.parseInt(txt60AndAbove.getText());
						PMarks33to59 = Integer.parseInt(txt33to59.getText());
						PMarksBelow33 = Integer.parseInt(txtBelow33.getText());
						assignmentMarks = Integer.parseInt(txtAssignment.getText());
						calculateInternalMarks();
						student.dispose();
						new ViewStudensInternalMarks();
					}
			});
			
		JLabel labAttendance= new JLabel("Marks for attendance");
		labAttendance.setBounds(70,90,200,50);
		labAttendance.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labAttendance);

		JLabel lab90AndAbove= new JLabel("80% and above ",SwingConstants.RIGHT);
		lab90AndAbove.setBounds(120,136,200,50);
		lab90AndAbove.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(lab90AndAbove);
		
		
		JLabel lab50to79= new JLabel("50% to 79%",SwingConstants.RIGHT);
		lab50to79.setBounds(120,180,200,50);
		lab50to79.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(lab50to79);
		
		JLabel labBelow50= new JLabel("Below 50%",SwingConstants.RIGHT);
		labBelow50.setBounds(120,223,200,50);
		labBelow50.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labBelow50);
	
		
		JLabel labUnitTest= new JLabel("Marks for Unit Test");
		labUnitTest.setBounds(70,268,230,50);
		labUnitTest.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labUnitTest);
		
		JLabel lab20AndAbove= new JLabel("20 and above ",SwingConstants.RIGHT);
		lab20AndAbove.setBounds(120,310,200,50);
		lab20AndAbove.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(lab20AndAbove);
		
		
		JLabel lab12to19= new JLabel("12  to 19",SwingConstants.RIGHT);
		lab12to19.setBounds(120,352,200,50);
		lab12to19.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(lab12to19);
		
		JLabel labBelow12= new JLabel("Below 12",SwingConstants.RIGHT);
		labBelow12.setBounds(120,394,200,50);
		labBelow12.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labBelow12);
		
		JLabel labprelime= new JLabel("Marks for prelime exam");
		labprelime.setBounds(70,436,230,50);
		labprelime.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labprelime);
		
		JLabel lab60AndAbove= new JLabel("60 and above ",SwingConstants.RIGHT);
		lab60AndAbove.setBounds(120,481,200,50);
		lab60AndAbove.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(lab60AndAbove);
		
		
		JLabel lab33to59= new JLabel("33  to 59",SwingConstants.RIGHT);
		lab33to59.setBounds(120,525,200,50);
		lab33to59.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(lab33to59);
		
		JLabel labBelow33= new JLabel("Below 33",SwingConstants.RIGHT);
		labBelow33.setBounds(120,568,200,50);
		labBelow33.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labBelow33);
		
		JLabel labAssignment= new JLabel("Marks  for  per  Assignment");
		labAssignment.setBounds(70,610,250,50);
		labAssignment.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labAssignment);
		
		
		txt90AndAbove = new JTextField("7"){
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
		txt90AndAbove.setBounds(350,155,100,20);
		txt90AndAbove.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txt90AndAbove);
		
		txt50to79= new JTextField("5"){
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
		txt50to79.setBounds(350,195,100,20);
		txt50to79.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txt50to79);
		
		txtBelow50= new JTextField("2"){
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
		txtBelow50.setBounds(350,238,100,20);
		txtBelow50.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtBelow50);
		
		txt20AndAbove= new JTextField("3"){
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
		txt20AndAbove.setBounds(350,330,100,20);
		txt20AndAbove.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txt20AndAbove);
		
		txt12to19= new JTextField("2"){
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
		txt12to19.setBounds(350,370,100,20);
		txt12to19.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txt12to19);
		
		txtBelow12= new JTextField("1"){
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
		txtBelow12.setBounds(350,410,100,20);
		txtBelow12.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtBelow12);
		
		txt60AndAbove= new JTextField("4"){
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
		txt60AndAbove.setBounds(350,500,100,20);
		txt60AndAbove.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txt60AndAbove);
		
		txt33to59= new JTextField("2"){
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
		txt33to59.setBounds(350,540,100,20);
		txt33to59.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txt33to59);
		
		txtBelow33= new JTextField("1"){
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
		txtBelow33.setBounds(350,580,100,20);
		txtBelow33.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtBelow33);
	
		txtAssignment= new JTextField("1"){
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
		txtAssignment.setBounds(350,627,100,20);
		txtAssignment.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtAssignment);
						
		student.setSize(555,750);
		student.setVisible(true);
		student.setResizable(false);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void calculateInternalMarks(){
	int i = 0,id = -1,rollNo = -1;
	String firstName = null,lastName= null,semester = null,year = null,sql = null;
	int subOneMarks = -1,subTwoMarks = -1,subThreeMarks = -1,subFourMarks = -1,subFiveMarks = -1;

	try {
			int count = 5;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
	
			st = con.createStatement();
			sql = "delete * from FY_InternalMarksTable";
			st.executeUpdate(sql);
			sql = "delete * from SY_InternalMarksTable";
			st.executeUpdate(sql);
			sql = "delete * from TY_InternalMarksTable";
			st.executeUpdate(sql);
            st = con.createStatement();
			ps = con.prepareStatement("SELECT COUNT(studentID) as count FROM StudentTable");
			ResultSet rs = ps.executeQuery();
			if(rs.next())
			{
				count = rs.getInt("count");
			}
			int progressBarCount = 100 / count;
			int temp = progressBarCount;
			ps = con.prepareStatement("select studentID,rollNo,firstName,lastName,yearFySyTy,semester from StudentTable");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt("studentID");
                rollNo = rs.getInt("rollNo");
                firstName = rs.getString("firstName");
                lastName = rs.getString("lastName");
                year = rs.getString("yearFySyTy");
                semester = rs.getString("semester");
				attendanceMarks = getAttendanceMarks(id);
				subOneMarks = getSubjectOneMark(id,semester,year,"subOne","subOneAssignment");
				subTwoMarks = getSubjectTwoMark(id,semester,year,"subTwo","subTwoAssignment");
				subThreeMarks = getSubjectThreeMark(id,semester,year,"subThree","subThreeAssignment");
				subFourMarks = getSubjectFourMark(id,semester,year,"subFour","subFourAssignment");
				if(!year.equals("TY")){
					subFiveMarks = getSubjectFiveMark(id,semester,year,"subFive","subFiveAssignment");
				}
				saveInternalMarks(id,rollNo,firstName,lastName,semester,year,subOneMarks,subTwoMarks ,subThreeMarks ,subFourMarks, subFiveMarks);
                i++;
				pb.paintImmediately(0, 0, 200, 25);
				pb.setValue(progressBarCount);
				progressBarCount = progressBarCount + temp;
            }
            if (i < 1) {
               // JOptionPane.showMessageDialog(null,"No records present.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }finally {
					try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
	}
	private int getSubjectOneMark(int id,String semester,String year,String subject,String assignment){
		int subjectMarks = getTestMarks(id,semester,year,subject);
		int assignmentMarks = getAssignmentMarks(id,semester,year,assignment);
		return  subjectMarks + assignmentMarks + attendanceMarks;
	}	
	private int getSubjectTwoMark(int id,String semester,String year,String subject,String assignment){
		int subjectMarks = getTestMarks(id,semester,year,subject);
		int assignmentMarks = getAssignmentMarks(id,semester,year,assignment);
		return   subjectMarks + assignmentMarks + attendanceMarks;
		
	}	
	private int getSubjectThreeMark(int id,String semester,String year,String subject,String assignment){
		
		int subjectMarks = getTestMarks(id,semester,year,subject);
		int assignmentMarks = getAssignmentMarks(id,semester,year,assignment);
		return  subjectMarks + assignmentMarks + attendanceMarks;
	}	
	private int getSubjectFourMark(int id,String semester,String year,String subject,String assignment){
		
		int subjectMarks = getTestMarks(id,semester,year,subject);
		int assignmentMarks = getAssignmentMarks(id,semester,year,assignment);
		return  subjectMarks + assignmentMarks + attendanceMarks;
	}	
	private int getSubjectFiveMark(int id,String semester,String year,String subject,String assignment){
		int subjectMarks = getTestMarks(id,semester,year,subject);
		int assignmentMarks = getAssignmentMarks(id,semester,year,assignment);
		return  subjectMarks + assignmentMarks + attendanceMarks;
	}
	private int getTestMarks(int id ,String semester ,String year,String subject){
		int marks = 0;
		String exam =null;
			try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			if(year.equals("FY")){
				ps = con.prepareStatement("select * from FY_TermWorkTable where studentID ="+id+" and semester = '"+semester+"'");
			}
			else if(year.equals("SY")){
				ps = con.prepareStatement("select * from SY_TermWorkTable where studentID ="+id+" and semester = '"+semester+"'");
			}
			else
			{
				ps = con.prepareStatement("select * from TY_TermWorkTable where studentID ="+id+" and semester = '"+semester+"'");
			}
			ResultSet rs = ps.executeQuery(); 
            while (rs.next()) {
                exam = rs.getString(5);
				int temp = getMarks(id,exam,semester ,year,subject);
				 marks =  marks + temp;
            }
			} catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
			}finally {
					try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
			}
			return marks;
		}
	private int getMarks(int id ,String exam,String semester ,String year,String subject){
	int marks = -1;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
			st = con.createStatement();
			
			if(year.equals("FY")){
				ps = con.prepareStatement("select * from FY_TermWorkTable where studentID ="+id+" and semester = '"+semester+ "' and exam = '"+exam+"'");
			}
			else if(year.equals("SY")){
				ps = con.prepareStatement("select * from SY_TermWorkTable where studentID ="+id+" and semester = '"+semester+ "' and exam = '"+exam+"'");
			}
			else
			{
				ps = con.prepareStatement("select * from TY_TermWorkTable where studentID ="+id+" and semester = '"+semester+ "' and exam = '"+exam+"'");
			}
			ResultSet rs = ps.executeQuery();
			int i = 0;	
			while (rs.next()) {
				 marks = Integer.parseInt(rs.getString(subject));
				i++;
			}

			if (i < 1) {
				// JOptionPane.showMessageDialog(null,"No records present.");
			}
			if (exam.equals("Unit Test 1")){
				marks = getUnitTestMarks(marks);
			}
			else if (exam.equals("Unit Test 2")){
				marks = getUnitTestMarks(marks);
			}
			else if (exam.equals("Prelime")){
				marks = getPrelime(marks);
			}
			else
			{
				marks = 0;
			}
			
	} catch (Exception ex) {
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
	}finally {
				try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
				try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
			}
		return marks;
	}
	int getUnitTestMarks(int marks)
	{
		if(marks >= 20)
		{
			marks = UTMarks20AndAbove;
		}
		else if (marks > 12 && marks < 20)
		{
			marks = UTMarks12to19;
		}
		else if (marks != 0 )
		{
			marks = UTMarksBelow12;
		}	
		else
		{
			marks = 0;
		}
		return marks;
	}
	int getPrelime(int marks)
	{
		if(marks >= 60)
		{
			marks = PMarks60AndAbove;
		}
		else if (marks > 32 && marks < 60)
		{
			marks = PMarks33to59;
		}
		else if (marks != 0 )
		{
			marks = PMarksBelow33 ;
		}
		else
		{
			marks = 0;
		}
		return marks;
	}
		
	private int getAssignmentMarks(int id ,String semester,String year,String assignment){
		int marks = 0;
		String isAssignment = null;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
			st = con.createStatement();
			if(year.equals("FY")){
				ps = con.prepareStatement("select * from FY_TermWorkTable where studentID ="+id+" and semester = '"+semester+ "'");
			}
			else if(year.equals("SY")){
				ps = con.prepareStatement("select * from SY_TermWorkTable where studentID ="+id+" and semester = '"+semester+ "'");
			}
			else
			{
				ps = con.prepareStatement("select * from TY_TermWorkTable where studentID ="+id+" and semester = '"+semester+ "'");
			}
			ResultSet rs = ps.executeQuery();
			int i = 0;	
			while (rs.next()) {
				isAssignment = rs.getString(assignment);
				if(isAssignment.equals("YES")){
					marks = marks + assignmentMarks;
				}
				i++;
			}

			if (i < 1) {
				// JOptionPane.showMessageDialog(null,"No records present.");
			}
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
		}finally {
					try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
			return marks;
	}
	private void saveInternalMarks(int ID,int rollNo,String firstName,String lastName,String semester,String year,int subOne,int subTwo,int subThree,int subFour,int subFive){
		try{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:Student");
				if(year.equals("FY")){
					ps = con.prepareStatement("insert into FY_InternalMarksTable(studentID,rollNo,firstName,lastName,semester,yearFySyTy,subOne,subTwo,subThree, subFour,subFive) values(?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1,ID);
					ps.setInt(2,rollNo);
					ps.setString(3,firstName);
					ps.setString(4,lastName);
					ps.setString(5,semester);
					ps.setString(6,year);
					ps.setInt(7,subOne);
					ps.setInt(8,subTwo);
					ps.setInt(9,subThree);
					ps.setInt(10,subFour);
					ps.setInt(11,subFive);
					ps.execute();
				}
				else if(year.equals("SY")){
					ps = con.prepareStatement("insert into SY_InternalMarksTable(studentID,rollNo,firstName,lastName,semester,yearFySyTy,subOne,subTwo,subThree, subFour,subFive) values(?,?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1,ID);
					ps.setInt(2,rollNo);
					ps.setString(3,firstName);
					ps.setString(4,lastName);
					ps.setString(5,semester);
					ps.setString(6,year);
					ps.setInt(7,subOne);
					ps.setInt(8,subTwo);
					ps.setInt(9,subThree);
					ps.setInt(10,subFour);
					ps.setInt(11,subFive);
					ps.execute();
				}
				else {
					ps = con.prepareStatement("insert into TY_InternalMarksTable(studentID,rollNo,firstName,lastName,semester,yearFySyTy,subOne,subTwo,subThree, subFour) values(?,?,?,?,?,?,?,?,?,?)");
					ps.setInt(1,ID);
					ps.setInt(2,rollNo);
					ps.setString(3,firstName);
					ps.setString(4,lastName);
					ps.setString(5,semester);
					ps.setString(6,year);
					ps.setInt(7,subOne);
					ps.setInt(8,subTwo);
					ps.setInt(9,subThree);
					ps.setInt(10,subFour);
					ps.execute();
				}
			}catch(Exception ex){
				System.out.println("\n"+ex);
			} finally {
				try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
				try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
			}

	}
		private int getAttendanceMarks(int id){
			int marks = 0;
			int attendancePercentage = getAttendancePercentage(id);
			if(attendancePercentage >= 80)
			{
				marks = attendace90AndAbove;
			}
			else if (attendancePercentage >= 50 && attendancePercentage < 80)
			{
				marks = attendace50to79;
			}
			else if (attendancePercentage != 0 )
			{
				marks = attendaceBelow50;
			}	
			else
			{
				marks = 0;
			}
			return marks;
		}
		int getAttendancePercentage(int id){
			int totalNumberOfDays = 0,presentDays = 0 ,attendancePercentage = 0;
			try {
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				con=DriverManager.getConnection("jdbc:odbc:Student");
				st = con.createStatement();
				ps = con.prepareStatement("SELECT COUNT(attendanceDate) as totalNumberOfDays FROM AttendanceTable where studentID = "+id);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
				{
					totalNumberOfDays = rs.getInt("totalNumberOfDays");
				}
				ps = con.prepareStatement("SELECT COUNT(attendance) as presentDays FROM AttendanceTable where attendance = 'true' and studentID = "+id);
				 rs = ps.executeQuery();
				if(rs.next())
				{
					presentDays = rs.getInt("presentDays");
				}
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
			}finally {
						try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
						try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
				if(totalNumberOfDays != 0){
					attendancePercentage = ((100 * presentDays) / totalNumberOfDays);
				}
				
				return attendancePercentage;
			}
		public static void main(String args[])
		{
			new SetInternalMarks();
		}
}
