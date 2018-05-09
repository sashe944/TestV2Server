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
import services.FindUserService;


/**
 * Servlet implementation class UserLogInServlet
 */
@WebServlet("/UserLogInServlet")
public class UserLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
	    private FindUserService findUserService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserLogInServlet() {
        super();
        // TODO Auto-generated constructor stub
        findUserService = new FindUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		 String Password = request.getParameter("Password");
		 String FacultyNumber = request.getParameter("FacultyNumber");
		 
		 User user = findUserService.find(Password, FacultyNumber);
		 
		 if (user != null) {
			  response.setContentType("application/json;charset=UTF-8");
		      Gson gson = gson_builder.create();
		      response.getWriter().write(gson.toJson(user));
		  }
		  else {
		      request.setAttribute("error", "Unknown user, please try again");
		  }	
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
