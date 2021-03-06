package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objects.User;

public class RegisterUserService {

	public User register(String FacultyNumber,String Name,String Password, String Gender,long UserTypeID){
		
		Connection connection = null;
		User user = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "INSERT INTO User (FacultyNumber,Name,Password,Gender,UserTypeID) VALUES (?,?,?,?,?)";
			
			Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
			
				statement = connection.prepareStatement(sql);
				
				statement.setString(1,FacultyNumber );
				statement.setString(2, Name);
				statement.setString(3, Password);
				statement.setString(4, Gender);
				statement.setLong(5,UserTypeID);
				
				
				statement.executeUpdate();
				
				FindUserService findUserService = new FindUserService();
				user =  findUserService.find(Password, FacultyNumber);
				
				System.out.println("register user: " + user);

			} catch(Exception e){
    	    	e.printStackTrace();
    	    } finally {
    			try {
    				statement.close();
    				connection.close();
    			} catch (SQLException e) {
    				
    				e.printStackTrace();
    			}
    		}
			return user;
		
	}
}
