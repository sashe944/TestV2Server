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
import objects.QuestionType;
import objects.Subject;
import services.FindQuestionService;
import services.FindQuestionTypeService;
import services.FindSubjectService;

/**
 * Servlet implementation class QuestionFindServlet
 */
@WebServlet("/"+Constants.QUESTION_TYPE_URL_FIND)
public class QuestionTypeFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private FindQuestionTypeService findQuestionTypeService;
    
    public QuestionTypeFindServlet() {
        super();
        // TODO Auto-generated constructor stub
        findQuestionTypeService = new FindQuestionTypeService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<QuestionType> questionTypes = findQuestionTypeService.findAll();
		 
		 if (questionTypes != null) {
			  response.setContentType("application/json;charset=UTF-8");
		      Gson gson = gson_builder.create();
		      
		      response.getWriter().write(gson.toJson(questionTypes));
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
