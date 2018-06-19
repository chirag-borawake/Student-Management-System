package student;
import student.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.sql.*;

class addStudentInformation extends JFrame{
		JFrame student=  new JFrame("Student Management System");

		addStudentInformation(){	
			student.setLayout(null);
			JLabel labTitle1= new JLabel("Student Management System");
			labTitle1.setBounds(50,30,450,50);
			labTitle1.setFont(new Font("Serif", Font.PLAIN, 38));
			student.add(labTitle1);
		
			
			JButton butAddStudent = new JButton("Add");
			butAddStudent.setBounds(190,120,190,30);
			butAddStudent.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butAddStudent);
			
			JButton butViewStudens = new JButton("View");
			butViewStudens.setBounds(190,186,190,30);
			butViewStudens.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butViewStudens);
			
			JButton butDeletetStudent = new JButton("Delete");
			butDeletetStudent.setBounds(190,252,190,30);
			butDeletetStudent.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butDeletetStudent);
			
			JButton butSetTermWork = new JButton("Set Term Work");
			butSetTermWork.setBounds(190,318,190,30);
			butSetTermWork.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butSetTermWork);
			
			JButton butGetTermWork = new JButton("Get Term Work");
			butGetTermWork.setBounds(190,384,190,30);
			butGetTermWork.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butGetTermWork);
			
			JButton butViewAttendance = new JButton("View Attendance");
			butViewAttendance.setBounds(190,450,190,30);
			butViewAttendance.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butViewAttendance);
			
			JButton butSetAttendance = new JButton("Set Attendance");
			butSetAttendance.setBounds(190,516,190,30);
			butSetAttendance.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butSetAttendance);
			
			JButton butSetInternalMarks = new JButton("Set Internal Marks");
			butSetInternalMarks.setBounds(190,582,190,30);
			butSetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butSetInternalMarks);
			
			JButton butGetInternalMarks = new JButton("Get Internal Marks");
			butGetInternalMarks.setBounds(190,650,190,30);
			butGetInternalMarks.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butGetInternalMarks);
		
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
			
			
	}
}
public class Login extends JPanel {
	Connection con= null;
	ResultSet rs;
	PreparedStatement pst = null;
	Statement st = null;
	static JFrame loginFrame = new JFrame("Student Management System");
	static JLabel labMessage= new JLabel();
	static JTextField txtUsername= new JTextField();
	static JPasswordField passwordField = new JPasswordField();
	static JButton butLogin=new JButton("Sign in");	
	public Login(){
		setOpaque(false);
		setLayout(null);	
		butLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				if(checkUsernamePassword(txtUsername.getText(),passwordField.getText())){
					loginFrame.dispose();
					new addStudentInformation();
				}
				else if(txtUsername.getText().equals("admin") && passwordField.getText().equals("admin")){
				txtUsername.setText("");
				passwordField.setText("");
					// loginFrame.dispose();
					new TeacherInformation();
				}
				else{
					labMessage.setText(" Usename or password you entered is incorrect.");
					labMessage.setVisible(true);
				}
			}
		});
	}
	public static void main(String args[]){
		final Login login = new Login();
		
		labMessage.setBounds(105,350,360,25);
		labMessage.setFont(new Font("Serif", Font.PLAIN, 19));
		labMessage.setBackground(Color.BLACK);
		labMessage.setForeground(Color.RED);
		labMessage.setOpaque(true);
		labMessage.setVisible(false);
		login.add(labMessage);
		
		txtUsername.setBounds(170,175,200,25);
		txtUsername.setFont(new Font("Serif", Font.PLAIN, 20));
		login.add(txtUsername);
		
		passwordField.setBounds(170,255,200,25);
		passwordField.setFont(new Font("Serif", Font.PLAIN, 15));
		login.add(passwordField);
		
		JLabel labUsername= new JLabel("Username");
		labUsername.setBounds(170,140,220,20);
		labUsername.setFont(new Font("Serif", Font.PLAIN, 27));
		login.add(labUsername);
		
		JLabel labPassword= new JLabel("Password");
		labPassword.setBounds(170,220,220,20);
		labPassword.setFont(new Font("Serif", Font.PLAIN, 27));
		login.add(labPassword);
		
		JLabel labTitle1= new JLabel("SINHAGAD COLLEGE OF SCEINCE");
		labTitle1.setBounds(30,30,500,50);
		labTitle1.setFont(new Font("Serif", Font.PLAIN, 30));
		login.add(labTitle1);
		
		JLabel labTitle2= new JLabel("BCA Department");
		labTitle2.setBounds(170,70,400,50);
		labTitle2.setFont(new Font("Serif", Font.PLAIN, 30));
		login.add(labTitle2);
		
		butLogin.setBounds(270,300,100,25);
		butLogin.setFont(new Font("Serif", Font.PLAIN, 20));
		
		login.add(butLogin);
		loginFrame.add(login);
		loginFrame.setSize(555,750);
		loginFrame.setVisible(true);
		loginFrame.setResizable(false);
		loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	boolean checkUsernamePassword(String checkUsername,String checkPassword){
		boolean isUsernamePassword = false;
		try{
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
			st = con.createStatement();
			pst = con.prepareStatement("select * from UsernamePassworkTable");
			ResultSet rs = pst.executeQuery();
			int record = 0;	
			while (rs.next()) {
				record++;
				String username = rs.getString("username");
				String password = rs.getString("password");
				if(username.equals(checkUsername) && password.equals(checkPassword))
				{
					isUsernamePassword =  true;
					break;
				}
			}
			if (record < 1) {
				JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}finally {
				try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
				try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
			}
		return isUsernamePassword;	
	}
	public void paint(Graphics g){
		Image img = Toolkit.getDefaultToolkit().getImage("students.jpg");
		g.drawImage(img,0,0,getSize().width,getSize().height,this);
		super.paint(g);
	}
}
// D:\Chirag\Projects\StudentManagementSystem_JAVA>java student.Login





