
package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objects.TestHeader;
import servlets.Constants;

public class FindTestService {

	
	public List<TestHeader> findAll(){
		  List<TestHeader> testHeaders = new ArrayList<TestHeader>();
    	  Connection conn = null;
    	    Statement stmt = null;
    	    try{
    	    	Class.forName("org.sqlite.JDBC");
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery("SELECT * FROM TestHeader");

    	      
    	        
    	        while (rs.next()){
    	        	TestHeader registeredTest = new TestHeader();
    	        	registeredTest.id = rs.getInt("_id");
    	        	registeredTest.testName = rs.getString("testName");
    	        	registeredTest.fromDate = rs.getString("fromDate");
    	        	registeredTest.toDate = rs.getString("toDate");
    	        	registeredTest.subjectID = rs.getLong("subjectID");
    	        	registeredTest.userID = rs.getLong("userID");
    	        	testHeaders.add(registeredTest);
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
    	    System.out.println(testHeaders.toString());
		return testHeaders;
	}

	public TestHeader find(long subjectID,long userID){
		TestHeader registeredTest = new TestHeader();
    	  Connection conn = null;
    	    Statement stmt = null;
    	    try{
    	    	Class.forName("org.sqlite.JDBC");
    	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
    	        conn.setAutoCommit(false);
    	        stmt = conn.createStatement();
    	        ResultSet rs = stmt.executeQuery("SELECT * FROM TestHeader WHERE"
    	        		+ " "+Constants.SUBJECT_ID_FOREIGN_KEY+" = \"" + subjectID + 
    	        		"\" AND userID=\"" +userID + "\"");

    	        while (rs.next()){
    	        	registeredTest.id = rs.getInt("_id");
    	        	registeredTest.testName = rs.getString("testName");
    	        	registeredTest.fromDate = rs.getString("fromDate");
    	        	registeredTest.toDate = rs.getString("toDate");
    	        	registeredTest.subjectID = rs.getLong("subjectID");
    	        	registeredTest.userID = rs.getLong("userID");
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
    	    System.out.println(registeredTest.toString());
		return registeredTest;
	}
}
