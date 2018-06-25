package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import objects.Subject;
import objects.User;

public class DBConnection {
	
	Connection DatabaseConnection = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public boolean DatabaseConnection(){
		
		  try{
			  
		      Class.forName("org.sqlite.JDBC");
		      DatabaseConnection=DriverManager.getConnection("jdbc:sqlite:/C:/Users/PC/eclipse/jee-oxygen/eclipse/TestV2.db");
		      return true;
		    }
		      catch(Exception e)
		      {
			    e.printStackTrace();
			    return false;
		      }finally{
			    	  try {
						DatabaseConnection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		      }
	  }
	 public User getUser(String Password, String FacultyNumber) {
    	 User user = new User();
		return user; 
    }
	 public Subject getDiscipline(String Name,String Description){
		 Subject discipline = new Subject();
		 return discipline;
	 }
}
