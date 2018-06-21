package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import objects.PossibleAnswer;
import objects.Question;
import servlets.Constants;

public class FindQuestionService {

	public Question findByQuestionId(String questionId) {
		
	  	  Connection conn = null;
	  	    Statement stmt = null;
	  	    try{
	  	    	Class.forName("org.sqlite.JDBC");
	  	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
	  	        conn.setAutoCommit(false);
	  	        stmt = conn.createStatement();
	  	        ResultSet rs = stmt.executeQuery("SELECT * FROM Question WHERE "+Constants.QUESTION_ID +" = \"" + questionId + "\"");

	  	        while (rs.next()){
	  	        	Question testQuestion = new Question();
	  	        	testQuestion.name = rs.getString("Name");
	  	        	testQuestion.id = rs.getLong("_id");
	  	        	testQuestion.questionTypeId = rs.getLong("QuestionTypeID");
	  	      	    testQuestion.testHeaderId = rs.getLong("TestHeaderID");
	  	      	    System.out.println(testQuestion.toString());
	  	      	    return testQuestion;
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
			return null;
	}
	
		public List<Question> findByTestHeaderId(String testId){
			 List<Question> questions = new ArrayList<>();
	  	  Connection conn = null;
	  	    Statement stmt = null;
	  	    try{
	  	    	Class.forName("org.sqlite.JDBC");
	  	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
	  	        conn.setAutoCommit(false);
	  	        stmt = conn.createStatement();
	  	        ResultSet rs = stmt.executeQuery("SELECT * FROM Question WHERE "+Constants.TEST_HEADER_ID +" = \"" + testId + "\"");

	  	        while (rs.next()){
	  	        	Question testQuestion = new Question();
	  	        	testQuestion.name = rs.getString("Name");
	  	        	testQuestion.id = rs.getLong("_id");
	  	        	testQuestion.questionTypeId = rs.getLong("QuestionTypeID");
	  	      	    testQuestion.testHeaderId = rs.getLong("TestHeaderID");
	  	        	questions.add(testQuestion);
	  	        }  
	  	        
	  	        for (Question q : questions) {
	  	        	ResultSet rs1 = stmt.executeQuery("SELECT * FROM PossibleAnswers WHERE "+Constants.QUESTION_ID_FK +" = \"" + q.id + "\"");
	  	        		  	        	 	        	
	  	        	while (rs1.next()) {
	  	        		PossibleAnswer pa = new PossibleAnswer();
	  	        		pa.id = rs1.getLong("_id");
	  	        		pa.isCorrect = rs1.getLong("isCorrect");
	  	        		pa.name = rs1.getString("Name");
	  	        		pa.points = rs1.getLong("Points");
	  	        		pa.questionId = rs1.getLong("QuestionID");
	  	        		
	  	        		if (q.possibleAnswers == null) q.possibleAnswers = new ArrayList<>();
	  	        		
	  	        		q.possibleAnswers.add(pa);
	  	        	}
	  	        	
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
	  	    System.out.println(questions.toString());
			return questions;
		}
}
