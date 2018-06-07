package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import objects.Subject;
import services.FindSubjectService;

/**
 * Servlet implementation class SubjectFindServlet
 */
@WebServlet("/"+Constants.SUBJECT_URL_FIND)
public class SubjectFindServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
    private FindSubjectService findSubjectService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubjectFindServlet() {
        super();
        // TODO Auto-generated constructor stub
        findSubjectService = new FindSubjectService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String subjectId = request.getParameter("subjectId");
		
		if(subjectId!=null){
		
        Subject discipline = findSubjectService.find(subjectId);
		 
		 if (discipline != null) {
			  response.setContentType("application/json;charset=UTF-8");
		      Gson gson = gson_builder.create();
		      
		      response.getWriter().write(gson.toJson(discipline));
		  }
		  else {
		      request.setAttribute("error", "Unknown subject, please try again");
		  }	
		}else {
			
			 List<Subject> discipline = findSubjectService.findAllSubjects();
			 
			 if (discipline != null) {
				  response.setContentType("application/json;charset=UTF-8");
			      Gson gson = gson_builder.create();
			      
			      response.getWriter().write(gson.toJson(discipline));
			  }
			  else {
			      request.setAttribute("error", "Unknown user, please try again");
			  }	
			
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
		public static String getBody(HttpServletRequest request) throws IOException {
		  String body = null;
		    StringBuilder stringBuilder = new StringBuilder();
		    BufferedReader bufferedReader = null;

		    try {
		        InputStream inputStream = request.getInputStream();
		        if (inputStream != null) {
		            bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
		            char[] charBuffer = new char[128];
		            int bytesRead = -1;
		            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
		                stringBuilder.append(charBuffer, 0, bytesRead);
		            }
		        } else {
		            stringBuilder.append("");
		        }
		    } catch (IOException ex) {
		        throw ex;
		    } finally {
		        if (bufferedReader != null) {
		            try {
		                bufferedReader.close();
		            } catch (IOException ex) {
		                throw ex;
		            }
		        }
		    }

		    body = stringBuilder.toString();
		    return body;
		}
	}
