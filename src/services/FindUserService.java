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
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE"
    	        		+ " "+Constants.USER_PASSWORD+" = \"" + Password + 
    	        		"\" AND "+Constants.USER_FACULTY_NUMBER+"=\"" +FacultyNumber + "\"");

    	        while (rs.next()){
    	        	user.id = rs.getInt("_id");
    	        	user.facultyNumber = rs.getString("facultyNumber");
    	        	user.name = rs.getString("name");
    	        	user.gender = rs.getString("gender");
    	        	user.password = rs.getString("password");
    	        	user.userTypeID = rs.getString("userTypeID");
    	        	
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
	
	public User findUser(Long id){
		  User user = new User();
	  Connection conn = null;
	    Statement stmt = null;
	    try{
	    	Class.forName("org.sqlite.JDBC");
	    	conn=DriverManager.getConnection("jdbc:sqlite:C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
	        conn.setAutoCommit(false);
	        stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE _id = \"" + id + "\"");

	        while (rs.next()){
                
	        	user.id = rs.getLong("_id");
	        	user.name = rs.getString("name");
	        	user.password = rs.getString("password");
	        	user.gender = rs.getString("gender");
	        	
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
