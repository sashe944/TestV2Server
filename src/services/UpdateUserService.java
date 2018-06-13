package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import objects.User;

public class UpdateUserService {
	
public User update(Long id, String Name,String Password, String Gender){
		
		Connection connection = null;
		User user = null;
		PreparedStatement statement = null;
		
		try {
			
			final String sql = "UPDATE User SET Name = ? , Password = ? , Gender = ? WHERE _id =?";
			
			Class.forName("org.sqlite.JDBC");
			 connection = DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
			
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