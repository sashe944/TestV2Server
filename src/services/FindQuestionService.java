package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objects.Question;
import servlets.Constants;

public class FindQuestionService {

		public Question find(String questionId){
			 Question testQuestion = new Question();
	  	  Connection conn = null;
	  	    Statement stmt = null;
	  	    try{
	  	    	Class.forName("org.sqlite.JDBC");
	  	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/TestV2.db");
	  	        conn.setAutoCommit(false);
	  	        stmt = conn.createStatement();
	  	        ResultSet rs = stmt.executeQuery("SELECT * FROM Question WHERE "+Constants.QUESTION_ID +" = \"" + questionId + "\"");

	  	        while (rs.next()){

	  	        	testQuestion.name = rs.getString("Name");
	  	        	testQuestion.id = rs.getLong("_id");
	  	        	testQuestion.questionTypeId = rs.getLong("QuestionTypeID");
	  	      	    testQuestion.testHeaderId = rs.getLong("TestHeaderID");
	  	        	
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
	  	    System.out.println(testQuestion.toString());
			return testQuestion;
		}
}
