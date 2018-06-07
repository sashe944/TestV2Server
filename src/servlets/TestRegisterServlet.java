package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.TestHeader;
import objects.User;
import services.RegisterTestService;
import services.RegisterUserService;

/**
 * Servlet implementation class TestRegisterServlet
 */
@WebServlet("/TestRegisterServlet")
public class TestRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private RegisterTestService registerTestService;
    
    public TestRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        registerTestService = new RegisterTestService();
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
		   
		TestHeader reqCredentials = new GsonBuilder().create().fromJson(jsonRequest, TestHeader.class);
		
	  TestHeader registeredTest = new TestHeader();
		
	 String testName = reqCredentials.testName;
	 String fromDate = reqCredentials.fromDate;
	 String toDate = reqCredentials.toDate;
	 Long gradeSingleAnswer = reqCredentials.gradeSingleAnswer;
	 Long gradeMultipleAnswer = reqCredentials.gradeMultipleAnswer;
	 Long gradeFreeTextAnswer = reqCredentials.gradeFreeTextAnswer;
     Long subjectID = reqCredentials.subjectID;
     Long userID = reqCredentials.userID;
	  
     registeredTest = registerTestService.register(testName, fromDate, toDate, gradeSingleAnswer, gradeMultipleAnswer, gradeFreeTextAnswer, subjectID, userID);
  
    if (registeredTest != null) {
	  response.setContentType("application/json;charset=UTF-8");
      Gson gson = gson_builder.create();
      response.getWriter().write(gson.toJson(registeredTest));
    }
  else {
      request.setAttribute("error", "Registration was unsuccessful!");
  }
 
	}

}
