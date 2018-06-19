package student;
import student.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.sql.*;

class ShowTermWorkByID extends JFrame {
	Connection con= null;
	PreparedStatement pst = null;
	ResultSet rs;
    Statement st;
	PreparedStatement ps = null;
	int rollNo;
	String sql,firstName,lastName,year;
	JFrame student;
	JLabel labSubFirst,labSubSecond,labSubThird,labSubFourth,labSubFifth;
	JLabel labSubOne,labSubTwo,labSubThree,labSubFour,labSubFive;
	JCheckBox chkAssignmentSubOne,chkAssignmentSubTwo,chkAssignmentSubThree,chkAssignmentSubFour,chkAssignmentSubFive;
	ShowTermWorkByID(String exam,String semester,int ID){
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
		
		JButton butSetTermWork = new JButton("Set Term Work");
		butSetTermWork.setBounds(135,20,125,26);
		butSetTermWork.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetTermWork);
		
		JButton butAddStudent = new JButton("Add");
		butAddStudent.setBounds(135,60,125,26);
		butAddStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butAddStudent);
	
			
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
		
		
		
		JButton butOk = new JButton("OK");
		butOk.setBounds(360,660,80,25);
		butOk.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butOk);
		
		JLabel labExam= new JLabel(exam);
		labExam.setBounds(210,110,300,25);
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
		labMarks.setBounds(260,370,130,25);
		labMarks.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labMarks);
		
		JLabel labAssignment= new JLabel("Assignment");
		labAssignment.setBounds(350,370,130,25);
		labAssignment.setFont(new Font("Serif", Font.PLAIN, 22));
		student.add(labAssignment);
		
		labSubFirst = new JLabel();
		labSubFirst.setBounds(20,420,200,25);
		labSubFirst.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubFirst.setHorizontalAlignment(SwingConstants.RIGHT);
		labSubFirst.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubFirst);
		
		labSubSecond = new JLabel();
		labSubSecond.setBounds(20,470,180,25);
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
		
		labSubOne = new JLabel();
		labSubOne.setBounds(260,420,50,25);
		labSubOne.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubOne.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubOne);
		
		labSubTwo= new JLabel();
		labSubTwo.setBounds(260,470,50,20);
		labSubTwo.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubTwo.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubTwo);
		
		labSubThree= new JLabel();
		labSubThree.setBounds(260,520,50,20);
		labSubThree.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubThree.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubThree);
		
		labSubFour= new JLabel();
		labSubFour.setBounds(260,570,50,20);
		labSubFour.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubFour.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubFour);
		
		labSubFive= new JLabel();
		labSubFive.setBounds(260,620,50,20);
		labSubFive.setFont(new Font("Serif", Font.PLAIN, 21));
		labSubFive.setHorizontalAlignment(JLabel.CENTER);
		student.add(labSubFive);
		
		chkAssignmentSubOne = new JCheckBox();
		chkAssignmentSubOne.setBounds(380,420,110,20);
		chkAssignmentSubOne.setFont(new Font("Serif", Font.PLAIN, 22));
		chkAssignmentSubOne.setEnabled(false);
		student.add(chkAssignmentSubOne);
		
		chkAssignmentSubTwo = new JCheckBox();
		chkAssignmentSubTwo.setBounds(380,470,110,20);
		chkAssignmentSubTwo.setFont(new Font("Serif", Font.PLAIN, 22));
		chkAssignmentSubTwo.setEnabled(false);
		student.add(chkAssignmentSubTwo);
		
		chkAssignmentSubThree = new JCheckBox();
		chkAssignmentSubThree.setBounds(380,520,110,20);
		chkAssignmentSubThree.setFont(new Font("Serif", Font.PLAIN, 22));
		chkAssignmentSubThree.setEnabled(false);
		student.add(chkAssignmentSubThree);
		
		chkAssignmentSubFour = new JCheckBox();
		chkAssignmentSubFour.setBounds(380,570,110,20);
		chkAssignmentSubFour.setFont(new Font("Serif", Font.PLAIN, 22));
		chkAssignmentSubFour.setEnabled(false);
		student.add(chkAssignmentSubFour);
		
		chkAssignmentSubFive = new JCheckBox();
		chkAssignmentSubFive.setBounds(380,620,110,20);
		chkAssignmentSubFive.setFont(new Font("Serif", Font.PLAIN, 22));
		chkAssignmentSubFive.setEnabled(false);
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
		
			butSetTermWork.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new SetTermWork();
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
		butOk.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				student.dispose();
					new ShowTermWork();
			}
		});
	
		try {
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
			String tempRollNo = String.valueOf(rollNo);
			labRollNo.setText(tempRollNo);
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
		getStudentTearWork(damySemester,damyID,damyExam);
		student.setSize(555,750);
		student.setVisible(true);
		student.setResizable(false);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	private void getStudentTearWork(String semester ,int ID,String exam)
	{
		int semNumber = getSemNumber(semester);
		System.out.println("semester"+semNumber);
		if(semNumber == 1 || semNumber == 2)
		{
			showFirstYearData(ID,exam,semester.trim());
			// student.dispose();
			// new SetTermWork();
		}
		else if(semNumber == 3 || semNumber == 4)
		{
			showSecondYearData(ID,exam,semester);
			// student.dispose();
			// new SetTermWork();
		}
		else if(semNumber == 5 || semNumber == 6)
		{
			showThirdYearData(ID,exam,semester);		
			// student.dispose();
			// new SetTermWork();
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
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
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
			labSubFive.setVisible(true);
			chkAssignmentSubFive.setVisible(true);
			if(subFive.length() == 0){
				System.out.println(subFive.length());
				labSubFive.setVisible(false);
				chkAssignmentSubFive.setVisible(false);
			}
		}
		else{
			labSubFive.setVisible(false);
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
private void showFirstYearData(int damyID,String damyExam,String damySemester){
try {
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con=DriverManager.getConnection("jdbc:odbc:Student");
    st = con.createStatement();
	pst = con.prepareStatement("select * from FY_TermWorkTable where studentID ="+damyID+ " and exam='"+damyExam+"' and semester  ='"+damySemester+"'");
	ResultSet rs = pst.executeQuery();
	int i = 0;	
	String subOne =null ,subTwo = null,subThree = null ,subFour=null ,subFive =null ,subOneAssignment =null ,subTwoAssignment = null ,subThreeAssignment = null,   subFourAssignment=null ,subFiveAssignment = null;
	while (rs.next()) {
		subOne = rs.getString(8);
		subTwo = rs.getString(9);
		subThree = rs.getString(10);
		subFour = rs.getString(11);
		subFive = rs.getString(12);
		subOneAssignment = rs.getString(13);
		subTwoAssignment = rs.getString(14);
		subThreeAssignment = rs.getString(15);
		subFourAssignment = rs.getString(16);
		subFiveAssignment = rs.getString(17);
		i++;
	}
	labSubOne.setText(subOne);
	labSubTwo.setText(subTwo);
	labSubThree.setText(subThree);
	labSubFour.setText(subFour);
	labSubFive.setText(subFive);
	if(subOneAssignment.equals("YES")){
		chkAssignmentSubOne.setSelected(true);
	}
	else{
		chkAssignmentSubOne.setSelected(false);
	}
	if(subTwoAssignment.equals("YES")){
		chkAssignmentSubTwo.setSelected(true);
	}
	else{
		chkAssignmentSubTwo.setSelected(false);
	}
	if(subThreeAssignment.equals("YES")){
		chkAssignmentSubThree.setSelected(true);
	}
	else{
		chkAssignmentSubThree.setSelected(false);
	}
	if(subFourAssignment.equals("YES")){
		chkAssignmentSubFour.setSelected(true);
	}
	else{
		chkAssignmentSubFour.setSelected(false);
	}
	if(subFiveAssignment.equals("YES")){
		chkAssignmentSubFive.setSelected(true);
	}
	else{
		chkAssignmentSubFive.setSelected(false);
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
private void showSecondYearData(int damyID,String damyExam,String damySemester){
			 try {
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con=DriverManager.getConnection("jdbc:odbc:Student");
	st = con.createStatement();
	pst = con.prepareStatement("select * from SY_TermWorkTable where studentID ="+damyID+ " and exam='"+damyExam+"' and semester  ='"+damySemester+"'");
	ResultSet rs = pst.executeQuery();
	int i = 0;	
	String subOne =null ,subTwo = null,subThree = null ,subFour=null ,subFive =null ,subOneAssignment =null ,subTwoAssignment = null ,subThreeAssignment = null,   subFourAssignment=null ,subFiveAssignment = null;
	while (rs.next()) {
		subOne = rs.getString(8);
		subTwo = rs.getString(9);
		subThree = rs.getString(10);
		subFour = rs.getString(11);
		subFive = rs.getString(12);
		subOneAssignment = rs.getString(13);
		subTwoAssignment = rs.getString(14);
		subThreeAssignment = rs.getString(15);
		subFourAssignment = rs.getString(16);
		subFiveAssignment = rs.getString(17);
		i++;
	}
	labSubOne.setText(subOne);
	labSubTwo.setText(subTwo);
	labSubThree.setText(subThree);
	labSubFour.setText(subFour);
	labSubFive.setText(subFive);
	if(subOneAssignment.equals("YES")){
		chkAssignmentSubOne.setSelected(true);
	}else{
		chkAssignmentSubOne.setSelected(false);
	}
	if(subTwoAssignment.equals("YES")){
		chkAssignmentSubTwo.setSelected(true);
	}
	else{
		chkAssignmentSubTwo.setSelected(false);
	}
	if(subThreeAssignment.equals("YES")){
		chkAssignmentSubThree.setSelected(true);
	}
	else{
		chkAssignmentSubThree.setSelected(false);
	}
	if(subFourAssignment.equals("YES")){
		chkAssignmentSubFour.setSelected(true);
	}
	else{
		chkAssignmentSubFour.setSelected(false);
	}
	if(subFiveAssignment.equals("YES")){
		chkAssignmentSubFive.setSelected(true);
	}
	else{
		chkAssignmentSubFive.setSelected(false);
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
			
private void showThirdYearData(int damyID,String damyExam,String damySemester){
	try {
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	con=DriverManager.getConnection("jdbc:odbc:Student");
	st = con.createStatement();
	pst = con.prepareStatement("select * from TY_TermWorkTable where studentID ="+damyID+ " and exam='"+damyExam+"'and semester = '"+damySemester+"'");
	ResultSet rs = pst.executeQuery();
	int i = 0;	
	String subOne =null ,subTwo = null,subThree = null ,subFour=null  ,subOneAssignment =null ,subTwoAssignment = null ,subThreeAssignment = null,   subFourAssignment=null ;
	while (rs.next()) {
		subOne = rs.getString(8);
		subTwo = rs.getString(9);
		subThree = rs.getString(10);
		subFour = rs.getString(11);
		subOneAssignment = rs.getString(12);
		subTwoAssignment = rs.getString(13);
		subThreeAssignment = rs.getString(14);
		subFourAssignment = rs.getString(15);
		i++;
	}
	labSubOne.setText(subOne);
	labSubTwo.setText(subTwo);
	labSubThree.setText(subThree);
	labSubFour.setText(subFour);
	if(subOneAssignment.equals("YES")){
		chkAssignmentSubOne.setSelected(true);
	}
	else{
		chkAssignmentSubOne.setSelected(false);
	}
	if(subTwoAssignment.equals("YES")){
		chkAssignmentSubTwo.setSelected(true);
	}
	else{
		chkAssignmentSubTwo.setSelected(false);
	}
	if(subThreeAssignment.equals("YES")){
		chkAssignmentSubThree.setSelected(true);
	}
	else{
		chkAssignmentSubThree.setSelected(false);
	}
	if(subFourAssignment.equals("YES")){
		chkAssignmentSubFour.setSelected(true);
	}
	else{
		chkAssignmentSubFour.setSelected(false);
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
		new ShowTermWorkByID("String exam","Semester 1",28);
	}
}


