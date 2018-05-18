package servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import objects.User;
import services.FindUserService;


/**
 * Servlet implementation class UserLogInServlet
 */
@WebServlet("/"+Constants.USER_URL_LOGIN)
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
		/*
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
		  }	*/
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsonRequest= getBody(request);
		
		User reqCredentials = new GsonBuilder().create().fromJson(jsonRequest, User.class);
		
		 String Password = reqCredentials.Password;
		 String FacultyNumber = reqCredentials.FacultyNumber;
		 
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
