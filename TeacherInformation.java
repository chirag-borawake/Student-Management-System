package student;
import student.*;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.io.*;
import java.sql.*;

class TeacherInformation extends JFrame{
		JFrame student=  new JFrame("Student Management System");
		TeacherInformation(){	
			student.setLayout(null);
			JLabel labTitle1= new JLabel("Student Management System");
			labTitle1.setBounds(50,100,450,50);
			labTitle1.setFont(new Font("Serif", Font.PLAIN, 38));
			student.add(labTitle1);
			
			JLabel labTitle2= new JLabel("Welcome Admin");
			labTitle2.setBounds(50,170,450,20);
			labTitle2.setFont(new Font("Serif", Font.PLAIN, 22));
			student.add(labTitle2);
		
			
			JButton butAddTeacher = new JButton("Add Teacher");
			butAddTeacher.setBounds(190,250,190,30);
			butAddTeacher.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butAddTeacher);
			
			JButton butViewTeacher = new JButton("View Teacher");
			butViewTeacher.setBounds(190,350,190,30);
			butViewTeacher.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butViewTeacher);
			
			JButton butDeletetTeacher = new JButton("Delete Teacher");
			butDeletetTeacher.setBounds(190,450,190,30);
			butDeletetTeacher.setFont(new Font("Serif", Font.PLAIN, 20));
			student.add(butDeletetTeacher);
			
			
			student.setSize(555,750);
			student.setVisible(true);
			student.setResizable(false);
			student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
			butAddTeacher.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new AddTeacher();
				}
			});	
			
			butViewTeacher.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new ViewTeacher();
				}
			});	
			
			butDeletetTeacher.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae)
				{
					student.dispose();
					new DeleteTeacher();
				}
			});	
			
	}
}