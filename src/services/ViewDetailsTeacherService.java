package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Teacher;
import objects.User;

public class ViewDetailsTeacherService {
	
	public Teacher findTeacher(int id){
		  Teacher teacher = new Teacher();
	  Connection conn = null;
	    Statement stmt = null;
	    try{
	    	Class.forName("org.sqlite.JDBC");
	    	conn=DriverManager.getConnection("jdbc:sqlite:C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
	        conn.setAutoCommit(false);
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE _id = \"" + id + "\"");

	        while (rs.next()){
              
	        	teacher.id = rs.getInt("_id");
	        	teacher.name = rs.getString("name");
	        	teacher.email = rs.getString("email");
	            
	        	
	        }  
	    }
	    catch(Exception e){
	    	e.printStackTrace();
	    } finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	    System.out.println(teacher.toString());
		return teacher;
	}

}
