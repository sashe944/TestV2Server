package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import objects.StudentAnswer;
import services.RegisterStudentAnswerService;

@WebServlet("/" + Constants.ANSWER_URL_REGISTER)
public class AnswerRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private RegisterStudentAnswerService registerStudentAnswerService;
    
    public AnswerRegisterServlet() {
        super();
        registerStudentAnswerService = new RegisterStudentAnswerService();
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
		
		String jsonRequest = UserLogInServlet.getBody(request);
		System.out.println("jsn: " + jsonRequest);   
		
		List<StudentAnswer> studentAnswers = new GsonBuilder().create().fromJson(jsonRequest, new TypeToken<List<StudentAnswer>>(){}.getType());
		
		List<StudentAnswer> registeredAnswers = registerStudentAnswerService.register(studentAnswers);
        
	    if (registeredAnswers.get(0).id > 0 ) {
		  response.setContentType("application/json;charset=UTF-8");
	      Gson gson = gson_builder.create();
	      response.getWriter().write(gson.toJson(registeredAnswers));
	    } else {
	      request.setAttribute("error", "Registration was unsuccessful!");
	    }
	}

}
