package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import objects.Teacher;
import services.FindTeacherService;


/**
 * Servlet implementation class TeacherLogInServlet
 */
@WebServlet("/"+Constants.TEACHER_URL_LOGIN)
public class TeacherLogInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	 private static GsonBuilder gson_builder = new GsonBuilder().serializeNulls().setDateFormat("MM/dd/yyyy");
	   
	    private FindTeacherService findTeacherService;
	    
    public TeacherLogInServlet() {
        super();
        // TODO Auto-generated constructor stub
          findTeacherService = new FindTeacherService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
        String jsonRequest= getBody(request);
		
		Teacher reqCredentials = new GsonBuilder().create().fromJson(jsonRequest, Teacher.class);
		
		 String password = reqCredentials.password;
		 String name = reqCredentials.name;
		 
		 Teacher teacher = findTeacherService.find(name,password);
		 
		 if (teacher != null) {
			  response.setContentType("application/json;charset=UTF-8");
		      Gson gson = gson_builder.create();
		      
		      response.getWriter().write(gson.toJson(teacher));
		  }
		  else {
		      request.setAttribute("error", "Unknown teacher, please try again");
		  }	
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
