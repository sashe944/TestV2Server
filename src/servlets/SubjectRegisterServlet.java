package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.Subject;
import services.RegisterSubjectService;

/**
 * Servlet implementation class SubjectRegisterServlet
 */
@WebServlet("/"+Constants.SUBJECT_URL_REGISTRATION)
public class SubjectRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
	   private RegisterSubjectService registerSubjectService;
	       
    public SubjectRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        registerSubjectService = new RegisterSubjectService();
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
		 String jsonRequest = SubjectFindServlet.getBody(request);
		   
			Subject create = new GsonBuilder().create().fromJson(jsonRequest, Subject.class);
			
		  Subject discipline = new Subject();
		  
		  String Name = create.Name;
		  String Discipline = create.Description;
		
		  discipline = registerSubjectService.register(Name, Discipline);
		  
		    if (discipline != null) {
			  response.setContentType("application/json;charset=UTF-8");
		      Gson gson = gson_builder.create();
		      response.getWriter().write(gson.toJson(discipline));
		    }
		  else {
		      request.setAttribute("error", "Registration was unsuccessful!");
		  }
		 
	}

}
