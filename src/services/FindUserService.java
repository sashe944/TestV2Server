package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.User;
import servlets.Constants;

public class FindUserService {


	public User find(String Password,String FacultyNumber){
		User user = new User();
    	  Connection conn = null;
    	    Statement stmt = null;
    	    try{
    	    	Class.forName("org.sqlite.JDBC");
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/TestV2.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE"
    	        		+ " "+Constants.USER_PASSWORD+" = \"" + Password + 
    	        		"\" AND "+Constants.USER_FACULTY_NUMBER+"=\"" +FacultyNumber + "\"");

    	        while (rs.next()){
    	        	user.id = rs.getInt("_id");
    	        	user.FacultyNumber = rs.getString("FacultyNumber");
    	        	user.Name = rs.getString("Name");
    	        	user.Gender = rs.getString("Gender");
    	        	user.Password = rs.getString("Password");
    	        	user.UserTypeID = rs.getString("UserTypeID");
    	        	
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
    	    System.out.println(user.toString());
		return user;
	}
}
