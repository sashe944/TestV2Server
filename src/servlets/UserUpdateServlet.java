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
import services.UpdateUserService;

/**
 * Servlet implementation class UserUpdateServlet
 */
@WebServlet("/UserUpdateServlet")
public class UserUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private UpdateUserService updateUserService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
        updateUserService = new UpdateUserService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String jsonRequest = UserLogInServlet.getBody(request);
		   
			User reqCredentials = new GsonBuilder().create().fromJson(jsonRequest, User.class);
			
		  User user = new User();
		 Long id = reqCredentials.id;
		 String Name = reqCredentials.name;
		 String Password = reqCredentials.password;
		 String Gender = reqCredentials.gender;
   
		  
	     user = updateUserService.update(id, Name, Password, Gender);
	  
	    if (user != null) {
		  response.setContentType("application/json;charset=UTF-8");
	      Gson gson = gson_builder.create();
	      response.getWriter().write(gson.toJson(user));
	    }
	  else {
	      request.setAttribute("error", "Update was unsuccessful!");
	  }
	 
	}

}
