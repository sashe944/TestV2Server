package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objects.TestHeader;

public class RegisterTestService {
public TestHeader register(String testName,String fromDate,String toDate,long SubjectID,long UserID){
		
		Connection connection = null;
		TestHeader registeredTest = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "INSERT INTO TestHeader (testName,fromDate,toDate,subjectID,userID) "
					+ "VALUES (?,?,?,?,?)";
			
			Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
			
				statement = connection.prepareStatement(sql);
				
				statement.setString(1,testName);
				statement.setString(2, fromDate);
				statement.setString(3, toDate);
				statement.setLong(4,SubjectID);
				statement.setLong(5,UserID);
				
				
				
				statement.executeUpdate();
				
				FindTestService findTestService = new FindTestService();
				registeredTest =  findTestService.find(SubjectID, UserID);
				
				System.out.println("registered test: " + registeredTest);

			}catch(Exception e){
    	    	e.printStackTrace();
    	    } finally {
    			try {
    				statement.close();
    				connection.close();
    			} catch (SQLException e) {
    				
    				e.printStackTrace();
    			}
    		}
			return registeredTest;
		}
}
