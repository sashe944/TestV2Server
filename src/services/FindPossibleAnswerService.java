package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objects.PossibleAnswer;
import servlets.Constants;

public class FindPossibleAnswerService {

		public PossibleAnswer find(String possibleAnswerId){
			 PossibleAnswer possibleAnswer = new PossibleAnswer();
	  	  Connection conn = null;
	  	    Statement stmt = null;
	  	    try{
	  	    	Class.forName("org.sqlite.JDBC");
	  	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
	  	        conn.setAutoCommit(false);
	  	        stmt = conn.createStatement();
	  	        ResultSet rs = stmt.executeQuery("SELECT * FROM PossibleAnswer WHERE "+Constants.QUESTION_ID +" = \"" + possibleAnswerId + "\"");

	  	        while (rs.next()){

	  	        	possibleAnswer.name = rs.getString("Name");
	  	        	possibleAnswer.id = rs.getLong("_id");
	  	        	possibleAnswer.isCorrect = rs.getLong("isCorrect");
	  	      	    possibleAnswer.questionId = rs.getLong("QuestionID");
	  	        	possibleAnswer.points = rs.getLong("Points");
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
	  	    System.out.println(possibleAnswer.toString());
			return possibleAnswer;
		}
}
