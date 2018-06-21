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
import objects.Question;
import services.FindPossibleAnswerService;
import services.FindQuestionService;
import services.RegisterPossibleAnswersService;


@WebServlet("/"+Constants.QUESTION_URL_FIND)
public class QuestionFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private FindQuestionService findQuestionService;
    private FindPossibleAnswerService findPossibleAnswerService;
    
    public QuestionFindServlet() {
        super();
        findQuestionService = new FindQuestionService();
        findPossibleAnswerService = new FindPossibleAnswerService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String testId = request.getParameter("testId");
		
		if(testId!=null){
		
        List<Question> questions = findQuestionService.findByTestHeaderId(testId);
		 
		 if (questions != null) {
			  response.setContentType("application/json;charset=UTF-8");
		      Gson gson = gson_builder.create();
		      
		      
		      for (Question q : questions) {
		    	  q.possibleAnswers = findPossibleAnswerService.findByQuestionId(q.id);
		      }
		      
		      response.getWriter().write(gson.toJson(questions));
		  }
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
