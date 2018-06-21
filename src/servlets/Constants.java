package servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Constants {
	// Servlet URL 
	public static final String USER_URL_LOGIN = "UserLogInServlet";
	public static final String USER_URL_REGISTER = "UserRegisterServlet";
	public static final String USER_URL_FIND = "UserFindServlet";
	
	public static final String SUBJECT_URL_FIND = "SubjectFindServlet";
	public static final String SUBJECT_URL_REGISTRATION = "SubjectRegisterServlet";
	
	public static final String QUESTION_URL_REGISTER = "QuestionRegisterServlet";
	public static final String QUESTION_URL_FIND = "QuestionFindServlet";
	public static final String QUESTION_TYPE_URL_FIND = "QuestionTypeFindServlet";

	public static final String TEACHER_URL_LOGIN="TeacherLogInServlet";
	public static final String TEACHER_URL_REGISTER = "TeacherRegister";
	
	public static final String TEST_URL_FIND = "TestFindServlet";
	public static final String TEST_URL_REGISTER = "TestRegisterServlet";

    
	//USER COLUMN NAMES 
	public static final String USER_ID = "_id";
	// SUBJECT COLUMN NAMES
	public static final String SUBJECT_ID = "_id";
	public static final String USER_PASSWORD= "Password";
	public static final String USER_FACULTY_NUMBER= "FacultyNumber";
	
	//TESTHEADER COLUMN NAMES
	public static final String SUBJECT_ID_FOREIGN_KEY = "subjectID";
	public static final String USER_TYPE_ID_FOREIGN_KEY ="_id";
	
	//QUESTION COLUMN NAMES
	public static final String QUESTION_ID = "_id";
	public static final String QUESTION_NAME="Name";
	public static final String QUESTION_TYPE_ID="QuestionTypeID";
	public static final String TEST_HEADER_ID="TestHeaderID";
	
	// Possible Answers
	public static final String POSSIBLE_ANSWER_ID = "_id";
	public static final String QUESTION_ID_FK = "QuestionID";
	
	// Request params
}

