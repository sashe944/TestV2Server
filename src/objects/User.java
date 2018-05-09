package objects;

public class User {
  public long id;
  public String FacultyNumber,Name,Gender,Password,UserTypeID;;
  
  public String toString() {
		return "Student: " + Name;
	}
}
