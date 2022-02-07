package com.vijaytraining;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class PhaseTwoEndProject {
	PreparedStatement thePreparedStatement;
	ResultSet theResultSet,secondResultSet;
	String qry;
	Connection dbCon;
	public PhaseTwoEndProject() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			dbCon=DriverManager.getConnection("jdbc:mysql://localhost:3306/phaseendproject","root","");
			System.out.println("Connection Established");
			
			
		} catch (ClassNotFoundException e) {
			System.out.println("Class issue:"+e.getMessage());
		} catch (SQLException e) {
			System.out.println("Connection issue:"+e.getMessage());
		}
		
	}

	public static void main(String[] args) {
		
		PhaseTwoEndProject ref= new PhaseTwoEndProject();
		try {
			ref.optionSelect();
		} catch (SQLException e) {
			System.out.println("Connection issue:"+e.getMessage());
		}
		
		
	}
	
	private void optionSelect() throws SQLException {
		System.out.println("Select An Option:\n1.View all Students\n2.View all teachers\n3.View all Classes\n"
				+ "4.View all Subjects\n5.Assign Class for a subject\n6.Assign teachers to a class for a subject\n7.View Class Report\n"
				+ "8.Exit\nEnter your Choice:");
		Scanner scan=new Scanner(System.in);
		int choice=scan.nextInt();
		scan.nextLine();
		boolean flag;
		switch(choice) {
			
		case 1:
			studentList();
			optionSelect();
			break;
			
		case 2:
			teacherList();
			optionSelect();
			break;
			
			
		case 3:
			classList();
			optionSelect();
			break;
		
			
		case 4:
			subjectList();
			optionSelect();
			break;
		
			
			
		case 5:
			System.out.println("Assign Class for a Subject\n");
			classList();
			System.out.println("Enter the class id from the above list to which you want to assign a class to:");
			int classId= scan.nextInt();
			scan.nextLine();
			System.out.println();
			subjectList();
			System.out.println("Enter the subject id from the above list to assign to the class:");
			int subId=scan.nextInt();
			scan.nextLine();
			qry="insert into class_subject values(?,?)";
			thePreparedStatement=dbCon.prepareStatement(qry);
			thePreparedStatement.setInt(1, classId);
			thePreparedStatement.setInt(2, subId);
			try{
				thePreparedStatement.executeUpdate();
				System.out.println("The insertion is successful");
			}catch (SQLException e) {
				System.out.println("You have entered a wrong id");
				System.out.println();
				
			}
			
			optionSelect();
			break;
			
		case 6:
			System.out.println("Assign teachers to a class for a subject\n");
			teacherList();
			System.out.println("Enter the id of the teacher to be assigned:");
			int teaId=scan.nextInt();
			scan.nextLine();
			classList();
			System.out.println("Enter the Class id to which you want to assign the teacher to from the above list:");
			int clId=scan.nextInt();
			scan.nextLine();
			qry="select s.subject_id AS subject_id,s.subject_name AS subject_name from subject AS s,class_subject AS cs where (s.subject_id=cs.subject_id) AND (cs.class_id=?)";
			thePreparedStatement=dbCon.prepareStatement(qry);
			thePreparedStatement.setInt(1, clId);
			theResultSet = thePreparedStatement.executeQuery();
			flag=false;
			while(theResultSet.next()) {
				System.out.println("The Subject Id : "+theResultSet.getInt("subject_id")+" and the Subject name is "+theResultSet.getString("subject_name"));
				flag=true;
			}
			if(!flag) {
				System.out.println("No subjects assigned to the class yet\n assign a subject first to assign a teacher");
				optionSelect();
				break;}
			System.out.println("Select the SubjectId from the Above list:");
			int subjId=scan.nextInt();
			flag=false;
			theResultSet = thePreparedStatement.executeQuery();
			while(theResultSet.next()) {
				if(theResultSet.getInt("subject_id")==subjId)
					flag=true;
			}
			if(!flag) {
				System.out.println("You have entered the wrong subject code\n Try again");
				optionSelect();
				break;}
			
			qry="insert into teacher_class_subject values(?,?,?)";
			thePreparedStatement=dbCon.prepareStatement(qry);
			thePreparedStatement.setInt(1, teaId);
			thePreparedStatement.setInt(2, clId);
			thePreparedStatement.setInt(3, subjId);
			if(thePreparedStatement.executeUpdate()>0) {
				System.out.println("The insertion is successful");
			}
			optionSelect();
			break;
			
			
			
		case 7:
			System.out.println("Class Report\n");
			flag=false;
			qry="select * from class";
			thePreparedStatement=dbCon.prepareStatement(qry);
			theResultSet = thePreparedStatement.executeQuery();
			while(theResultSet.next())
			{System.out.println("Class:"+theResultSet.getString("class_name"));
				int i=theResultSet.getInt("class_id");
			qry="select student_name from students where class_id=?";
			thePreparedStatement=dbCon.prepareStatement(qry);
			thePreparedStatement.setInt(1, i);
			secondResultSet=thePreparedStatement.executeQuery();
			System.out.println("The students list:");
			while(secondResultSet.next()) {
				System.out.println(secondResultSet.getString("student_name"));
				flag=true;
			}
			if(!flag)
				System.out.println("No students are there in this class");
			
			flag=false;
			System.out.println();
			
			qry="select s.subject_name from subject AS s, class_subject AS cs where (s.subject_id = cs.subject_id) AND (cs.class_id=?)";
			thePreparedStatement=dbCon.prepareStatement(qry);
			thePreparedStatement.setInt(1, i);
			secondResultSet=thePreparedStatement.executeQuery();
			System.out.println("The subject list is:");
			while(secondResultSet.next()) {
				System.out.println(secondResultSet.getString("subject_name"));
				flag=true;
			}
			if(!flag)
				System.out.println("No subjects are assigned to this class");
			
			flag=false;
			System.out.println();
			
			
			
			qry="select t.teacher_name AS teacher,s.subject_name AS subject from teacher t , teacher_class_subject tc,subject s where (t.teacher_id = tc.teacher_id) AND (s.subject_id = tc.sub_id) AND (tc.class_id = ?)";
			thePreparedStatement=dbCon.prepareStatement(qry);
			thePreparedStatement.setInt(1, i);
			secondResultSet=thePreparedStatement.executeQuery();
			System.out.println("The teacher list is :");
			while(secondResultSet.next()) {
				System.out.println(secondResultSet.getString("teacher")+" teaches "+secondResultSet.getString("subject"));
				flag=true;
			}
			if(!flag)
				System.out.println("No subjects are assigned to this class");
			
			flag=false;
			System.out.println();
			}
			
			
			optionSelect();
			break;
			
		case 8:
			System.out.println("Thankyou");
			break;
			
		default:
			System.out.println("Wrong choice\n Try Again");
			optionSelect();
			break;
			
			
			
			
		}
	}

	private void subjectList() throws SQLException {
		System.out.println("The Subjects are :");
		qry = "select * from subject";
		thePreparedStatement = dbCon.prepareStatement(qry);
		theResultSet = thePreparedStatement.executeQuery();
		while(theResultSet.next()) {
			System.out.println("The Subject Id : "+theResultSet.getInt("subject_id")+" and the Subject name is "+theResultSet.getString("subject_name"));
		}
	}

	private void studentList() throws SQLException {
		System.out.println("The Students are:");
		qry = "select * from students";
		thePreparedStatement = dbCon.prepareStatement(qry);
		theResultSet = thePreparedStatement.executeQuery();
		while(theResultSet.next()) {
			System.out.println("The Student Id : "+theResultSet.getInt("student_id")+" and the name of the student is "+theResultSet.getString("student_name"));
		}
	}

	private void classList() throws SQLException {
		System.out.println("The classes are :");
		qry = "select * from class";
		thePreparedStatement = dbCon.prepareStatement(qry);
		theResultSet = thePreparedStatement.executeQuery();
		while(theResultSet.next()) {
			System.out.println("The Class Id : "+theResultSet.getInt("Class_id")+" and the Class name is "+theResultSet.getString("class_name"));
		}
	}

	private void teacherList() throws SQLException {
		System.out.println("The teachers are :");
		qry = "select * from teacher";
		thePreparedStatement = dbCon.prepareStatement(qry);
		theResultSet = thePreparedStatement.executeQuery();
		while(theResultSet.next()) {
			System.out.println("The Teacher Id : "+theResultSet.getInt("teacher_id")+" and the name of the Teacher is "+theResultSet.getString("teacher_name"));
		}
	}

}