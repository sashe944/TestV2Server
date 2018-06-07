package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.PossibleAnswer;
import objects.Question;


public class RegisterPossibleAnswersService {

public PossibleAnswer register(PossibleAnswer possibleAnswer){
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "INSERT INTO PossibleAnswers (Name,QuestionID,isCorrect, Points) "
					+ "VALUES (?,?,?,?)";
			
			 Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/TestV2.db");
			 
				statement = connection.prepareStatement(sql);
				
				statement.setString(1,possibleAnswer.name);
				statement.setLong(2, possibleAnswer.questionId);
				statement.setLong(3, possibleAnswer.isCorrect);
				statement.setLong(4, possibleAnswer.points);
				
				int affectedRows = statement.executeUpdate();
				
				  if (affectedRows == 0) {
			            throw new SQLException("Creating subject failed, no rows affected.");
			        }

			        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                Long id = generatedKeys.getLong(1);
			                possibleAnswer.id = id;
			                			            	
							System.out.println("register possibel answer: " + possibleAnswer);
			            }
			            else {
			                throw new SQLException("Creating possible answer failed, no ID obtained.");
			            }
			        }
			    
				System.out.println("registered possible answer: " + possibleAnswer);

			} catch(Exception e){
    	    	e.printStackTrace();
    	    } finally {
    			try {
    				statement.close();
    				connection.close();
    			} catch (SQLException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
			return possibleAnswer;
		}
}
