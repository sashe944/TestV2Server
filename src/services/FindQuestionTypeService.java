package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import objects.Question;
import objects.QuestionType;
import servlets.Constants;

public class FindQuestionTypeService {

		public List<QuestionType> findAll() {
			List<QuestionType> questionTypes = new ArrayList();
			 
	  	  	Connection conn = null;
	  	    Statement stmt = null;
	  	    try{
	  	    	Class.forName("org.sqlite.JDBC");
	  	    	conn=DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/TestV2.db");
	  	        conn.setAutoCommit(false);
	  	        stmt = conn.createStatement();
	  	        ResultSet rs = stmt.executeQuery("SELECT * FROM QuestionType");

	  	        while (rs.next()){
	  	        	QuestionType testType = new QuestionType();
	  	        	testType.name = rs.getString("Name");
	  	        	testType.id = rs.getInt("_id");
	  	        	testType.points = rs.getInt("Points");
	  	      	    questionTypes.add(testType);
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
	  	    System.out.println(questionTypes.toString());
			return questionTypes;
		}

}