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


class GetTermWorkByID extends JFrame implements ItemListener{
	Connection con = null;
	Statement st = null;
    ResultSet rs;
	String sql;
    PreparedStatement pst;
	
	JTextField txtStudentID;
	JFrame student;
 
	Checkbox fy,sy,ty;
	Checkbox sem1,sem2,sem3,sem4,sem5,sem6;
	Checkbox unitTestOne,unitTestTwo,Prelime; 
	CheckboxGroup checkGroupYear,checkGroupSem,checkGroupExam;
	
    static JTable table;
	int ID;
	String firstName,lastName,semester,exam;
    String[] columnNames = {"ID", "First Name", "Last Name","Exam","Semester"};
	
	DefaultTableModel model = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	TableColumn IDcolumn = null;
	// TableColumn Yearcolumn = null;
	GetTermWorkByID(){	
		student =  new JFrame("Student Management System");
		student.setLayout(null);
		
        model.setColumnIdentifiers(columnNames);
        table = new JTable();
        table.setModel(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
		
		table.getTableHeader().setFont( new Font( "Rockwell" , Font.PLAIN, 16 ));
		table.setFont(new Font("Rockwell", Font.PLAIN, 16));
		
		IDcolumn = table.getColumnModel().getColumn(0);
		IDcolumn.setPreferredWidth(25);
		
		// Yearcolumn = table.getColumnModel().getColumn(3);
		// Yearcolumn.setPreferredWidth(25);
		
		 JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				scroll.setBounds(20,130,510,400);
		student.add(scroll);
        // table.getColumnModel().getColumn(2).setHeaderValue("newHeader");
		
		
		fy =new Checkbox("First Year",true);
		fy.setBounds(20,100,120,20);
		fy.setFont(new Font("Serif", Font.PLAIN, 20));
        fy.addItemListener(this);
		student.add(fy);
		
		sy =new Checkbox("Second Year");
		sy.setBounds(150,100,120,20);
		sy.setFont(new Font("Serif", Font.PLAIN, 20));
        sy.addItemListener(this);
		student.add(sy);
		
		
		ty =new Checkbox("Third Year");
		ty.setBounds(300,100,120,20);
		ty.setFont(new Font("Serif", Font.PLAIN, 20));
        ty.addItemListener(this);
		student.add(ty);
		
		checkGroupYear = new CheckboxGroup();
		
        fy.setCheckboxGroup(checkGroupYear);
        sy.setCheckboxGroup(checkGroupYear);
        ty.setCheckboxGroup(checkGroupYear);
		
		sem1 = new Checkbox("Semester 1",true);
		sem1.setBounds(20,615,120,25);
		sem1.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem1);
		
		sem2 = new Checkbox("Semester 2");
		sem2.setBounds(240,615,120,25);
		sem2.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem2);
		
		sem3 = new Checkbox("Semester 3");
		sem3.setBounds(420,615,120,25);
		sem3.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem3);
		
		sem4 = new Checkbox("Semester 4");
		sem4.setBounds(20,650,120,25);
		sem4.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem4);
		
		sem5 = new Checkbox("Semester 5");
		sem5.setBounds(240,650,120,25);
		sem5.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem5);
		
		sem6 = new Checkbox("Semester 6");
		sem6.setBounds(420,650,120,25);
		sem6.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(sem6);
		
		
		checkGroupSem = new CheckboxGroup();
		
		sem1.setCheckboxGroup(checkGroupSem);
        sem2.setCheckboxGroup(checkGroupSem);
        sem3.setCheckboxGroup(checkGroupSem);
        sem4.setCheckboxGroup(checkGroupSem);
        sem5.setCheckboxGroup(checkGroupSem);
        sem6.setCheckboxGroup(checkGroupSem);
		
		unitTestOne = new Checkbox("Unit Test 1",true);
		unitTestOne.setBounds(20,580,120,25);
		unitTestOne.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(unitTestOne);
		
		unitTestTwo = new Checkbox("Unit Test 2");
		unitTestTwo.setBounds(240,580,120,25);
		unitTestTwo.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(unitTestTwo);
		
		Prelime = new Checkbox("Prelim");
		Prelime.setBounds(420,580,100,25);
		Prelime.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(Prelime);
		
		checkGroupExam = new CheckboxGroup();
		
		unitTestOne.setCheckboxGroup(checkGroupExam);
        unitTestTwo.setCheckboxGroup(checkGroupExam);
        Prelime.setCheckboxGroup(checkGroupExam);
		
		
		JLabel labMessage= new JLabel("Enter   student   ID   whose   result   to  be set");
		labMessage.setBounds(20,540,390,25);
		labMessage.setFont(new Font("Serif", Font.PLAIN, 21));
		student.add(labMessage);
		
		txtStudentID= new JTextField(){
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
		txtStudentID.setBounds(415,545,110,20);
		txtStudentID.setFont(new Font("Serif", Font.PLAIN, 20));
		student.add(txtStudentID);
		
				

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
		
		
		
		JButton butSubmit = new JButton("Submit");
		butSubmit.setBounds(425,685,100,25);
		butSubmit.setFont(new Font("Serif", Font.PLAIN, 18));
		student.add(butSubmit);
		
		JButton butSearch = new JButton("Search");
		butSearch.setBounds(437,100,90,23);
		butSearch.setFont(new Font("Serif", Font.PLAIN, 16));
		student.add(butSearch);
		

		
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
			butSubmit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae){
				String exam,semester;
				int studentID;
				Checkbox semesterCheckBox;
				
				semesterCheckBox = checkGroupSem.getSelectedCheckbox();
				semester = semesterCheckBox.getLabel();
				
				Checkbox examCheckBox = checkGroupExam.getSelectedCheckbox();
				exam = examCheckBox.getLabel();
				studentID = Integer.parseInt(txtStudentID.getText());
				student.dispose();
				new ShowTermWorkByID(exam,semester,studentID);
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
		
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			pst = con.prepareStatement("SELECT * from FY_TermWorkTable"); 
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				exam = rs.getString(5);
				semester = rs.getString(7);
                model.addRow(new Object[]{ID,firstName, lastName,exam,semester});
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
        }
		finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
		
		student.setSize(555,750);
		student.setVisible(true);
		student.setResizable(false);
		student.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void itemStateChanged(ItemEvent ie)
    {
		while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }

		Checkbox chk = checkGroupYear.getSelectedCheckbox();
		String selectedItem=chk.getLabel();
	
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
            st = con.createStatement();
			if(selectedItem.equals("First Year"))
			{
				pst = con.prepareStatement("SELECT * from FY_TermWorkTable"); 
			}
			else if(selectedItem.equals("Second Year"))
			{
				pst = con.prepareStatement("SELECT * from SY_TermWorkTable"); 
			}
			else if(selectedItem.equals("Third Year"))
			{
				pst = con.prepareStatement("SELECT * from TY_TermWorkTable"); 
			}
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				exam = rs.getString(5);
				semester = rs.getString(7);
                model.addRow(new Object[]{ID,firstName, lastName,exam,semester});
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
        }
		finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
		
    }
	private void searchID(int id)
	{	
		while (model.getRowCount()>0)
          {
             model.removeRow(0);
          }
		Checkbox chk = checkGroupYear.getSelectedCheckbox();
		String selectedItem=chk.getLabel();
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			con=DriverManager.getConnection("jdbc:odbc:Student");
			st = con.createStatement();
			if(selectedItem.equals("First Year"))
			{
				pst = con.prepareStatement("SELECT * from FY_TermWorkTable where studentID = "+id); 
			}
			else if(selectedItem.equals("Second Year"))
			{
				pst = con.prepareStatement("SELECT * from SY_TermWorkTable where studentID = "+id); 
			}
			else if(selectedItem.equals("Third Year"))
			{
				pst = con.prepareStatement("SELECT * from TY_TermWorkTable where studentID = "+id); 
			}
            ResultSet rs = pst.executeQuery();
            int i = 0;	
            while (rs.next()) {
                ID = rs.getInt(1);
                firstName = rs.getString(3);
                lastName = rs.getString(4);
				exam = rs.getString(5);
				semester = rs.getString(7);
                model.addRow(new Object[]{ID,firstName, lastName,exam,semester});
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
        }
		finally {
					try { pst.close(); } catch (Exception e) { System.out.println("\n"+e); }
					try { con.close(); } catch (Exception e) { System.out.println("\n"+e); }
				}
	}
	
	public static void main(String args[])
	{
		new GetTermWorkByID();
	}
}