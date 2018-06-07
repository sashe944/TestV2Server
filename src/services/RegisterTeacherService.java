package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import objects.Teacher;

public class RegisterTeacherService {

public Teacher register(String name,String password, String email, String gender,long usertypeid){
		
		Connection connection = null;
		Teacher teacher = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "INSERT INTO User (Name, Password, Email, Gender, UserTypeID) VALUES (?,?,?,?,?)";
			
			 Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/Home/Desktop/TestV2.db");
			
				statement = connection.prepareStatement(sql);
				
				statement.setString(1, name );
				statement.setString(2, password);
				statement.setString(3, email);
				statement.setString(4, gender);
				statement.setLong(5, usertypeid);
				
			
				
				statement.executeUpdate();
				
				FindTeacherService findTeacherService = new FindTeacherService();
				teacher =  findTeacherService.find(name, password);
				
				System.out.println("register teacher: " + teacher);

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
		return teacher;
    }
}
