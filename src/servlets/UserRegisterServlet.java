package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import objects.User;
import services.RegisterUserService;

/**
 * Servlet implementation class UserRegisterServlet
 */
@WebServlet("/"+Constants.USER_URL_REGISTER)
public class UserRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private RegisterUserService registerUserService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        registerUserService = new RegisterUserService();
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/* User user = new User();
		
		 String FacultyNumber = request.getParameter("FacultyNumber");
		 String Name = request.getParameter("Name");
		 String Password = request.getParameter("Password");
		 String Gender = request.getParameter("Gender");
         String UserTypeID = request.getParameter("UserTypeID");
		  
	     user = registerUserService.register(FacultyNumber, Name, Password, Gender, UserTypeID);
	  
	    if (user != null) {
		  response.setContentType("application/json;charset=UTF-8");
	      Gson gson = gson_builder.create();
	      response.getWriter().write(gson.toJson(user));
    }
	  else {
	      request.setAttribute("error", "Registration was unsuccessful!");
	  }	*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		   String jsonRequest = UserLogInServlet.getBody(request);
		   
			User reqCredentials = new GsonBuilder().create().fromJson(jsonRequest, User.class);
			
		  User user = new User();
			
		 String FacultyNumber = reqCredentials.facultyNumber;
		 String Name = reqCredentials.name;
		 String Password = reqCredentials.password;
		 String Gender = reqCredentials.gender;
         Long UserTypeID = reqCredentials.userTypeID;
		  
	     user = registerUserService.register(FacultyNumber, Name, Password, Gender, UserTypeID);
	  
	    if (user != null) {
		  response.setContentType("application/json;charset=UTF-8");
	      Gson gson = gson_builder.create();
	      response.getWriter().write(gson.toJson(user));
	    }
	  else {
	      request.setAttribute("error", "Registration was unsuccessful!");
	  }
	 
	}

}
