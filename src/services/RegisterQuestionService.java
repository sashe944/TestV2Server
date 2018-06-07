package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objects.Question;


public class RegisterQuestionService {

public Question register(String Name,long QuestionTypeID,long TestHeaderID){
		
		Connection connection = null;
		Question registeredQuestion = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "INSERT INTO Question (Name,QuestionTypeID,TestHeaderID) "
					+ "VALUES (?,?,?)";
			
			 Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/TestV2.db");
			 
				statement = connection.prepareStatement(sql);
				
				statement.setString(1,Name);
				statement.setLong(2, QuestionTypeID);
				statement.setLong(3, TestHeaderID);
				
				int affectedRows = statement.executeUpdate();
				
				  if (affectedRows == 0) {
			            throw new SQLException("Creating subject failed, no rows affected.");
			        }

			        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                Long id = generatedKeys.getLong(1);
			                FindQuestionService findQuestionService = new FindQuestionService();
			            	registeredQuestion =  findQuestionService.find(String.valueOf(id));
							
							System.out.println("register question: " + registeredQuestion);
			            }
			            else {
			                throw new SQLException("Creating question failed, no ID obtained.");
			            }
			        }
			        
				System.out.println("registered test: " + registeredQuestion);

			}  catch(Exception e){
    	    	e.printStackTrace();
    	    } finally {
    			try {
    				statement.close();
    				connection.close();
    			} catch (SQLException e) {
    				
    				e.printStackTrace();
    			}
    		}
			return registeredQuestion;
		}
}
