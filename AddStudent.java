package student;
import student.*;

import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.sql.*;

class AddStudent extends JFrame {
	Connection con= null;
	PreparedStatement ps = null;
	Checkbox male,female; 
	Checkbox fy,sy,ty; 
	Checkbox sem1,sem2,sem3,sem4,sem5,sem6;
	CheckboxGroup checkGroupGender,checkGroupYear,checkGroupSem;
	JTextField txtRollNo;
	JTextField txtAddress;
	JTextField txtFirstName;
	JTextField txtCast;
	JTextField txtEmail;
	JTextField txtParentsContactNo;
	JTextField txtContactNo;
	JTextField txtDateOfBirth;
	JTextField txtFatherName;
	JTextField txtLastName;
	JFrame student;
	AddStudent(){	
		student =  new JFrame("Student Management System");
		student.setLayout(null);
		// student.getContentPane().setBackground(Color.LIGHT_GRAY);
		JButton butViewStudens = new JButton("View ");
		butViewStudens.setBounds(10,20,116,26);
		butViewStudens.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewStudens);
		
		JButton butDeletetStudent = new JButton("Delete ");
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
		
		JButton butSetInternalMarks = new JButton("Set Internal Marks");
		butSetInternalMarks.setBounds(400,20,141,26);
		butSetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butSetInternalMarks);
		
		JButton butGetInternalMarks = new JButton("Get Internal Marks");
		butGetInternalMarks.setBounds(400,60,141,26);
		butGetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butGetInternalMarks);
		
		JButton butSubmit = new JButton("Submit");
		butSubmit.setBounds(370,680,100,26);
		butSubmit.setFont(new Font("Serif", Font.PLAIN, 18));
		student.add(butSubmit);

		
		JLabel labRollNO= new JLabel("Roll No",SwingConstants.RIGHT);
		labRollNO.setBounds(80,110,145,22);
		labRollNO.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labRollNO);
		
		JLabel labFirstName= new JLabel("First name",SwingConstants.RIGHT);
		labFirstName.setBounds(80,152,145,22);
		labFirstName.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labFirstName);
		
	
		JLabel labLastName= new JLabel("Last name",SwingConstants.RIGHT);
		labLastName.setBounds(80,194,145,22);
		labLastName.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labLastName);
		
		JLabel labFatherName= new JLabel("Fathers name",SwingConstants.RIGHT);
		labFatherName.setBounds(80,236,145,22);
		labFatherName.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labFatherName);
		
		JLabel labAddress= new JLabel("Address",SwingConstants.RIGHT);
		labAddress.setBounds(80,278,145,22);
		labAddress.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labAddress);
		
		JLabel labDateOfBirth= new JLabel("Date of birth",SwingConstants.RIGHT);
		labDateOfBirth.setBounds(80,320,145,22);
		labDateOfBirth.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labDateOfBirth);
		
		JLabel labGender= new JLabel("Gender",SwingConstants.RIGHT);
		labGender.setBounds(80,363,145,22);
		labGender.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labGender);
		
		
		JLabel labYear= new JLabel("Year",SwingConstants.RIGHT);
		labYear.setBounds(80,404,140,22);
		labYear.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labYear);
		
		JLabel labSemester= new JLabel("Semester",SwingConstants.RIGHT);
		labSemester.setBounds(80,446,145,22);
		labSemester.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labSemester);
		
		JLabel labContactNo= new JLabel("Contact no",SwingConstants.RIGHT);
		labContactNo.setBounds(80,514,145,22);
		labContactNo.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labContactNo);
		
		JLabel labParentsContactNo= new JLabel("Parents contact no",SwingConstants.RIGHT);
		labParentsContactNo.setBounds(51,560,175,22);
		labParentsContactNo.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labParentsContactNo);
		
		
		JLabel labEmail= new JLabel("Email",SwingConstants.RIGHT);
		labEmail.setBounds(80,602,145,22);
		labEmail.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labEmail);
		
		JLabel labCast= new JLabel("Cast",SwingConstants.RIGHT);
		labCast.setBounds(80,644,145,22);
		labCast.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labCast);
				
		
		txtRollNo = new JTextField() {
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
		txtRollNo.setBounds(260,114,213,20);
		txtRollNo.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtRollNo);

		txtFirstName= new JTextField(""){
		  public void processKeyEvent(KeyEvent ev) {
			char c = ev.getKeyChar();
			try {
			  if( (c > 63 && c < 123) || (c == '\b' || c == 32) ) {
				super.processKeyEvent(ev);
				}
			}
			catch (Exception nfe) {
			}
		  }
		};
		txtFirstName.setBounds(260,156,213,20);
		txtFirstName.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtFirstName);
		
		// txtFirstName.setBackground(Color.OPAL);
		txtLastName= new JTextField(""){
		  public void processKeyEvent(KeyEvent ev) {
			char c = ev.getKeyChar();
			try {
			  if( (c > 63 && c < 123) || (c == '\b' || c == 32) ) {
				super.processKeyEvent(ev);
				}
			}
			catch (Exception nfe) {
			}
		  }
		};
		txtLastName.setBounds(260,198,213,20);
		txtLastName.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtLastName);
		
		txtFatherName= new JTextField(""){
		  public void processKeyEvent(KeyEvent ev) {
			char c = ev.getKeyChar();
			try {
			  if( (c > 63 && c < 123) || (c == '\b' || c == 32) ) {
				super.processKeyEvent(ev);
				}
			}
			catch (Exception nfe) {
			}
		  }
		};
		
		txtFatherName.setBounds(260,240,213,20);
		txtFatherName.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtFatherName);
		
		txtAddress= new JTextField(""){
		  public void processKeyEvent(KeyEvent ev) {
			char c = ev.getKeyChar();
			try {
			  if( (c > 31 && c < 123) || (c == '\b' || c == 32) ) {
				super.processKeyEvent(ev);
				}
			}
			catch (Exception nfe) {
			}
		  }
		};
		txtAddress.setBounds(260,282,213,20);
		txtAddress.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtAddress);
		
		txtDateOfBirth= new JTextField(""){
		  public void processKeyEvent(KeyEvent ev) {
			char c = ev.getKeyChar();
			try {
			  if ((c > 46 && c < 59) || (c == '\b' || c == 32)) {
				super.processKeyEvent(ev);
			  }
			   
			}
			catch (NumberFormatException nfe) {
			}
		  }
		};
		txtDateOfBirth.setBounds(260,324,213,20);
		txtDateOfBirth.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtDateOfBirth);
		
		txtContactNo= new JTextField(""){
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
		txtContactNo.setBounds(260,520,213,20);
		txtContactNo.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtContactNo);
		
		txtParentsContactNo= new JTextField(""){
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
		txtParentsContactNo.setBounds(260,561,213,20);
		txtParentsContactNo.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtParentsContactNo);
		
		
		txtEmail= new JTextField(""){
		  public void processKeyEvent(KeyEvent ev) {
			char c = ev.getKeyChar();
			try {
			  if( (c > 31 && c < 123) || (c == '\b' || c == 32) ) {
				super.processKeyEvent(ev);
				}
			}
			catch (Exception nfe) {
			}
		  }
		};
		txtEmail.setBounds(260,603,213,20);
		txtEmail.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtEmail);
		
		txtCast= new JTextField(){
		  public void processKeyEvent(KeyEvent ev) {
			char c = ev.getKeyChar();
			try {
			  if( (c > 64 && c < 123) || (c == '\b' || c == 32) ) {
				super.processKeyEvent(ev);
				}
			}
			catch (Exception nfe) {
			}
		  }
		};
		txtCast.setBounds(260,645,213,20);
		txtCast.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtCast);
		
		male =new Checkbox("Male",true);
		male.setBounds(260,366,60,20);
		male.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(male);
		
		
		female =new Checkbox("Female");
		female.setBounds(350,366,90,20);
		female.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(female);
		
		checkGroupGender = new CheckboxGroup();
        male.setCheckboxGroup(checkGroupGender);
        female.setCheckboxGroup(checkGroupGender);
		
		fy =new Checkbox("FY",true);
		fy.setBounds(260,410,50,20);
		fy.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(fy);
		
		sy =new Checkbox("SY");
		sy.setBounds(350,410,50,20);
		sy.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sy);
		
		ty =new Checkbox("TY");
		ty.setBounds(430,410,50,20);
		ty.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(ty);
		
		checkGroupYear = new CheckboxGroup();
		
        fy.setCheckboxGroup(checkGroupYear);
        sy.setCheckboxGroup(checkGroupYear);
        ty.setCheckboxGroup(checkGroupYear);
		
		sem1 = new Checkbox("Sem 1",true);
		sem1.setBounds(260,445,70,25);
		sem1.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem1);
		
		sem2 = new Checkbox("Sem 2");
		sem2.setBounds(335,445,70,25);
		sem2.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem2);
		
		sem3 = new Checkbox("Sem 3");
		sem3.setBounds(410,445,70,25);
		sem3.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem3);
		
		sem4 = new Checkbox("Sem 4");
		sem4.setBounds(260,480,70,25);
		sem4.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem4);
		
		sem5 = new Checkbox("Sem 5");
		sem5.setBounds(335,480,70,25);
		sem5.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem5);
		
		sem6 = new Checkbox("Sem 6");
		sem6.setBounds(410,480,70,25);
		sem6.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem6);
		
		
		checkGroupSem = new CheckboxGroup();
		
		sem1.setCheckboxGroup(checkGroupSem);
        sem2.setCheckboxGroup(checkGroupSem);
        sem3.setCheckboxGroup(checkGroupSem);
        sem4.setCheckboxGroup(checkGroupSem);
        sem5.setCheckboxGroup(checkGroupSem);
        sem6.setCheckboxGroup(checkGroupSem);
		
		student.setSize(555,750);
		student.setVisible(true);
		student.setResizable(false);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
			
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
	
		butSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				boolean flag = true;
				if (txtRollNo.getText().equals(""))
				flag = false;
				if(txtFirstName.getText().equals(""))
				flag = false;
				if(txtLastName.getText().equals(""))
				flag = false;
				if(txtFatherName.getText().equals(""))
				flag = false;
				if(txtAddress.getText().equals(""))
				flag = false;
				if(txtContactNo.getText().equals(""))
				flag = false;
				if(txtParentsContactNo.getText().equals(""))
				flag = false;
				if(txtEmail.getText().equals(""))
				flag = false;
				if(txtCast.getText().equals(""))
				flag = false;
				if(flag == true){
					Checkbox checkboxYear = checkGroupYear.getSelectedCheckbox();
					String year = checkboxYear.getLabel();
					
					Checkbox checkboxGender= checkGroupGender.getSelectedCheckbox();
					String gender = checkboxGender.getLabel();
					
					Checkbox checkboxSemester= checkGroupSem.getSelectedCheckbox();
					String semester = checkboxSemester.getLabel();
					System.out.print(semester);
					try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con=DriverManager.getConnection("jdbc:odbc:Student");
					ps = con.prepareStatement("insert into StudentTable(rollNo,firstName,lastName,fatherName,address,gender,dateOfBirth, yearFySyTy,semester,contactNo,parentContactNo,Email,cast)values(?,?,?,?,?,?,?,?,?,?,?,?,?)");
					ps.setString(1,txtRollNo.getText());
					ps.setString(2,txtFirstName.getText());
					ps.setString(3,txtLastName.getText());
					ps.setString(4,txtFatherName.getText());
					ps.setString(5,txtAddress.getText());
					if(gender.equals("Male")){
						ps.setString(6,"Male");
					}
					else{
						ps.setString(6,"Female");
					}
					ps.setString(7,txtDateOfBirth.getText());
					if(year.equals("FY")){
						ps.setString(8,"FY");
					}
					else if(year.equals("SY")){
						ps.setString(8,"SY");
					}
					else{
						ps.setString(8,"TY");
					}
					
					if(semester.equals("Sem 1")){
						ps.setString(9,"Semester 1");
					}
					else if(semester.equals("Sem 2")){
						ps.setString(9,"Semester 2");
					}
					else if(semester.equals("Sem 3")){
						ps.setString(9,"Semester 3");
					}
					else if(semester.equals("Sem 4")){
						ps.setString(9,"Semester 4");
					}
					else if(semester.equals("Sem 5")){
						ps.setString(9,"Semester 5");
					}
					else{
						ps.setString(9,"Semester 6");
					}
					ps.setString(10,txtContactNo.getText());
					ps.setString(11,txtParentsContactNo.getText());
					ps.setString(12,txtEmail.getText());
					ps.setString(13,txtCast.getText());
					JOptionPane.showMessageDialog(null,"Record inserted.");
					ps.execute();
					}catch(Exception ex){
						System.out.println("\n"+ex);
					} finally {
						try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
						try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
					}
					student.dispose();
					new AddStudent();
				}
				else{
					JOptionPane.showMessageDialog(null,"Enter all records");
				}
			}
		});
	}
	public static void main(String args[])
	{
		new AddStudent();
	}
}