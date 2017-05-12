package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.Data;
import util.UserManager;

@WebServlet(urlPatterns="/login", name="LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("./login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "";
		String password = "";
		email = request.getParameter("email");
		password = request.getParameter("password");
		
		UserManager um = new UserManager();
		Data data = Data.newInstance();
		
		if ( um.checkUserLogin(email, password) ) {
			data.addSession(request.getSession(), email);
			response.sendRedirect("./");
			return;		
		} else {
			request.setAttribute("message", "Invalid username and/or password.");
		}
		
		request.getRequestDispatcher("./login.jsp").forward(request, response);
	}

}
