package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import objects.Subject;

public class RegisterSubjectService {
	
public Subject register(String Name,String Description){
		
		Connection connection = null;
		Subject discipline = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "INSERT INTO Subject (Name,Description) VALUES (?,?)";
			
			 Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
			
				statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				statement.setString(1,Name);
				statement.setString(2,Description);

				int affectedRows = statement.executeUpdate();
				
				  if (affectedRows == 0) {
			            throw new SQLException("Creating subject failed, no rows affected.");
			        }

			        try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
			            if (generatedKeys.next()) {
			                Long id = generatedKeys.getLong(1);
			                FindSubjectService findDisciplineService = new FindSubjectService();
							discipline =  findDisciplineService.find(String.valueOf(id));
							
							System.out.println("register discipline: " + discipline);
			            }
			            else {
			                throw new SQLException("Creating user failed, no ID obtained.");
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
			return discipline;
		
	}
}
