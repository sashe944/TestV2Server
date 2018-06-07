package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import objects.User;
import servlets.Constants;

public class UpdateUserService {
	
public User update(Long id, String Name,String Password, String Gender){
		
		Connection connection = null;
		User user = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "UPDATE User SET Name = ? , Password = ? , Gender = ? WHERE _id =?";
			
			Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/Kokosa/Downloads/TestV2.db");
			
				statement = connection.prepareStatement(sql);
				
				statement.setString(1, Name);
				statement.setString(2, Password);
				statement.setString(3, Gender);
				statement.setLong(4, id);
				
				
				
				statement.executeUpdate();
				
				FindUserService findUserService = new FindUserService();
				user =  findUserService.findUser(id);
				
				System.out.println("update user: " + user);

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