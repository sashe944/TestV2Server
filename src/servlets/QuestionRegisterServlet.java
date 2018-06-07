package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.PossibleAnswer;
import objects.Question;
import objects.TestHeader;
import services.RegisterPossibleAnswersService;
import services.RegisterQuestionService;
import services.RegisterTestService;

@WebServlet("/" + Constants.QUESTION_URL_REGISTER)
public class QuestionRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private RegisterQuestionService registerQuestionService;
    private RegisterPossibleAnswersService registerPossibleAnswerService;
   
    public QuestionRegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
        registerQuestionService = new RegisterQuestionService();
        registerPossibleAnswerService = new RegisterPossibleAnswersService();
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
		   
		Question reqCredentials = new GsonBuilder().create().fromJson(jsonRequest, Question.class);
		
	 		
	 String questionName = reqCredentials.name;
     Long QuestionTypeID = reqCredentials.questionTypeId;
     Long TestHeaderID = reqCredentials.testHeaderId;
	  
     Question registeredQuestion = registerQuestionService.register(questionName, QuestionTypeID, TestHeaderID);
     reqCredentials.id = registeredQuestion.id;
     
     for( PossibleAnswer pa : reqCredentials.possibleAnswers) {
    	 pa.questionId =  reqCredentials.id;
    	registerPossibleAnswerService.register(pa);
     }
     
    if (registeredQuestion != null) {
	  response.setContentType("application/json;charset=UTF-8");
      Gson gson = gson_builder.create();
      response.getWriter().write(gson.toJson(reqCredentials));
    }
  else {
      request.setAttribute("error", "Registration was unsuccessful!");
  }
}

}
