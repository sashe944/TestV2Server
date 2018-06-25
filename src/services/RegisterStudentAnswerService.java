package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import objects.Question;
import objects.StudentAnswer;


public class RegisterStudentAnswerService {

public List<StudentAnswer> register(List<StudentAnswer> studentAnswers){
		
		Connection connection = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "INSERT INTO StudentAnsweredQuestions (QuestionID, AnswerID, Text, StudentID,TestHeaderID) "
					+ "VALUES (?,?,?,?,?)";
			
			 Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
			 
			 for (StudentAnswer sa: studentAnswers) {
				statement = connection.prepareStatement(sql);
				
				statement.setLong(1,sa.questioId);
				statement.setLong(2, sa.possibleAnswerId);
				statement.setString(3, sa.freeText);
				statement.setLong(4, sa.studentId);
				statement.setLong(5, sa.testHeaderId);
				
				int affectedRows = statement.executeUpdate();
				
				  if (affectedRows == 0) {
			            throw new SQLException("Creating subject failed, no rows affected.");
			        }

			        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                Long answerId = generatedKeys.getLong(1);
			                sa.id = answerId;
							System.out.println("register answer: " + sa);
			            }
			            else {
			                throw new SQLException("Creating question failed, no ID obtained.");
			            }
			        }
			 }
				

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
			return studentAnswers;
		}
}
