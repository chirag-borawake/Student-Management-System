package student;
import student.*;

import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.sql.*;

class AddTeacher extends JFrame {
	Connection con= null;
	PreparedStatement ps = null;
	Checkbox male,female; 
	
	CheckboxGroup checkGroupGender;
	JTextField txtAddress;
	JTextField txtFirstName;
	JTextField txtEmail;
	JTextField txtContactNo;
	JTextField txtDateOfBirth;
	JTextField txtFatherName;
	JTextField txtLastName;
	JFrame student;
	AddTeacher(){	
		student =  new JFrame("Student Management System");
		student.setLayout(null);
		
		JButton butViewStudens = new JButton("View Teacher");
		butViewStudens.setBounds(80,30,130,26);
		butViewStudens.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butViewStudens);
		
		JButton butDeletetStudent = new JButton("Delete Teacher");
		butDeletetStudent.setBounds(260,30,130,26);
		butDeletetStudent.setFont(new Font("Serif", Font.PLAIN, 14));
		student.add(butDeletetStudent);
		
		
		JButton butSubmit = new JButton("Submit");
		butSubmit.setBounds(370,680,100,26);
		butSubmit.setFont(new Font("Serif", Font.PLAIN, 18));
		student.add(butSubmit);

		JButton butLogOut = new JButton("Log out");
		butLogOut.setBounds(430,30,100,26);
		butLogOut.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butLogOut);
		
		
		JLabel labFirstName= new JLabel("First name",SwingConstants.RIGHT);
		labFirstName.setBounds(80,100,145,22);
		labFirstName.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labFirstName);
		
	
		JLabel labLastName= new JLabel("Last name",SwingConstants.RIGHT);
		labLastName.setBounds(80,176,145,22);
		labLastName.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labLastName);
		
		JLabel labFatherName= new JLabel("Fathers name",SwingConstants.RIGHT);
		labFatherName.setBounds(80,252,145,22);
		labFatherName.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labFatherName);
		
		JLabel labAddress= new JLabel("Address",SwingConstants.RIGHT);
		labAddress.setBounds(80,328,145,22);
		labAddress.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labAddress);
		
		JLabel labDateOfBirth= new JLabel("Date of birth",SwingConstants.RIGHT);
		labDateOfBirth.setBounds(80,404,145,22);
		labDateOfBirth.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labDateOfBirth);
		
		JLabel labGender= new JLabel("Gender",SwingConstants.RIGHT);
		labGender.setBounds(80,480,145,22);
		labGender.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labGender);
		


		JLabel labContactNo= new JLabel("Contact no",SwingConstants.RIGHT);
		labContactNo.setBounds(80,556,145,22);
		labContactNo.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labContactNo);
		
	
		
		
		JLabel labEmail= new JLabel("Email",SwingConstants.RIGHT);
		labEmail.setBounds(80,620,145,22);
		labEmail.setFont(new Font("Serif", Font.PLAIN, 23));
		student.add(labEmail);
		

				
		

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
		txtFirstName.setBounds(260,105,213,20);
		txtFirstName.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtFirstName);
		
	
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
		txtLastName.setBounds(260,180,213,20);
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
		
		txtFatherName.setBounds(260,255,213,20);
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
		txtAddress.setBounds(260,335,213,20);
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
		txtDateOfBirth.setBounds(260,410,213,20);
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
		txtContactNo.setBounds(260,560,213,20);
		txtContactNo.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtContactNo);
		
		
		
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
		txtEmail.setBounds(260,625,213,20);
		txtEmail.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(txtEmail);
		

		male =new Checkbox("Male",true);
		male.setBounds(260,485,60,20);
		male.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(male);
		
		
		female =new Checkbox("Female");
		female.setBounds(350,485,90,20);
		female.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(female);
		
		checkGroupGender = new CheckboxGroup();
        male.setCheckboxGroup(checkGroupGender);
        female.setCheckboxGroup(checkGroupGender);
		
		
		student.setSize(555,750);
		student.setVisible(true);
		student.setResizable(false);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
			
			butViewStudens.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewTeacher();
				}
			});	
			
			butDeletetStudent.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new DeleteTeacher();
				}
			});	
			
			butLogOut.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
				}
			});		
		butSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae)
			{
				boolean flag = true;
			
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
				
				if(txtEmail.getText().equals(""))
				flag = false;
			
				if(flag == true){
					
					Checkbox checkboxGender= checkGroupGender.getSelectedCheckbox();
					String gender = checkboxGender.getLabel();
					
	
					try{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
					con=DriverManager.getConnection("jdbc:odbc:Student");
					ps = con.prepareStatement("insert into TeacherTable(firstName,lastName,fatherName,address,gender,dateOfBirth ,contactNo,Email)values(?,?,?,?,?,?,?,?)");
					ps.setString(1,txtFirstName.getText());
					ps.setString(2,txtLastName.getText());
					ps.setString(3,txtFatherName.getText());
					ps.setString(4,txtAddress.getText());
					if(gender.equals("Male")){
						ps.setString(5,"Male");
					}
					else{
						ps.setString(5,"Female");
					}
					ps.setString(6,txtDateOfBirth.getText());
					ps.setString(7,txtContactNo.getText());
					ps.setString(8,txtEmail.getText());
					JOptionPane.showMessageDialog(null,"Record inserted.");
					ps.execute();
					}catch(Exception ex){
						System.out.println("\n"+ex);
					} finally {
						try { ps.close(); } catch (Exception e) { System.out.println("\n"+e); }
						try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
					}
					student.dispose();
					new AddTeacher();
				}
				else{
					JOptionPane.showMessageDialog(null,"Enter all records");
				}
			}
		});
	}
	public static void main(String args[])
	{
		new AddTeacher();
	}
}