package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Subject;
import servlets.Constants;

public class FindSubjectService {

	public Subject find(String subjectId){
		  Subject discipline = new Subject();
  	  Connection conn = null;
  	    Statement stmt = null;
  	    try{
  	    	Class.forName("org.sqlite.JDBC");
  	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/TestV2.db");
  	        conn.setAutoCommit(false);
  	        stmt = conn.createStatement();
  	        ResultSet rs = stmt.executeQuery("SELECT * FROM Subject WHERE "+Constants.SUBJECT_ID +" = \"" + subjectId + "\"");

  	        while (rs.next()){

  	        	discipline.id = rs.getLong("_id");
  	        	discipline.Name = rs.getString("Name");
  	        	discipline.Description = rs.getString("Description");
  	        	
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
  	    System.out.println(discipline.toString());
		return discipline;
	}
}
