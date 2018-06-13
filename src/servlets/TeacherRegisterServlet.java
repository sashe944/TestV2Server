package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import objects.Teacher;
import services.RegisterTeacherService;

@WebServlet("/"+Constants.TEACHER_URL_REGISTER)
public class TeacherRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private RegisterTeacherService registerTeacherService;
	
    public TeacherRegisterServlet() {
        super();
        registerTeacherService = new RegisterTeacherService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
        new Teacher();
		
		final String name = request.getParameter("name");
		final String password = request.getParameter("password");
		request.getParameter("repassword");
		final String email = request.getParameter("email");
		final String gender = request.getParameter("gender");
		final Long userTypeId = Long.parseLong(request.getParameter("Teacher"));
		
		registerTeacherService.register(name, password, email, gender, userTypeId);
		
		
	}

}
