package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objects.Teacher;

public class FindTeacherService {
	
	public Teacher find(String name, String password){
		Teacher teacher = new Teacher();
    	  Connection conn = null;
    	    Statement stmt = null;
    	    try{
    	    	Class.forName("org.sqlite.JDBC");
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE"
    	        		+ " Name = \"" + name + 
    	        		"\" AND Password=\"" +password + "\"");

    	        while (rs.next()){
    	        	teacher.id = rs.getInt("_id");
    	            teacher.email = rs.getString("Email");
    	        	teacher.name = rs.getString("Name");
    	        	teacher.gender = rs.getString("Gender");
    	        	teacher.password = rs.getString("Password");
    	        	teacher.usertypeid = rs.getString("UserTypeID");
    	        	return teacher;
    	        }  
    	    }
    	    catch(Exception e){
    	    	e.printStackTrace();
    	    } finally {
    			try {
    				stmt.close();
    				conn.close();
    			} catch (SQLException e) {
    				e.printStackTrace();
    			}
    		}
    	    System.out.println(teacher.toString());
		return null;
	}

}
