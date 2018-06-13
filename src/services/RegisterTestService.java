package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objects.TestHeader;

public class RegisterTestService {
public TestHeader register(String testName,String fromDate,String toDate,long gradeSingleAnswer,long gradeMultipleAnswer,long gradeFreeTextAnswer,long SubjectID,long UserID){
		
		Connection connection = null;
		TestHeader registeredTest = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "INSERT INTO TestHeader (testName,fromDate,toDate,gradeSingleAnswer,gradeMultipleAnswer,gradeFreeTextAnswer,subjectID,userID) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			
			Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
			
				statement = connection.prepareStatement(sql);
				
				statement.setString(1,testName);
				statement.setString(2, fromDate);
				statement.setString(3, toDate);
				statement.setLong(4, gradeSingleAnswer);
				statement.setLong(5, gradeMultipleAnswer);
				statement.setLong(6, gradeFreeTextAnswer);
				statement.setLong(7,SubjectID);
				statement.setLong(8,UserID);
				
				
				
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
