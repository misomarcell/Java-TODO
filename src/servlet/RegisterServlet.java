package servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.UserManager;


@WebServlet(urlPatterns="/register", name="RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = "";
		String password = "";
		String password2 = "";
		
		email = request.getParameter("email");
		password = request.getParameter("password");
		password2 = request.getParameter("password2");
		
		if ( !password.equals(password2) ) {
			request.setAttribute("message", "Password does not match.");
			request.getRequestDispatcher("./login.jsp").forward(request, response);
			return;
		}
		
		UserManager um = new UserManager();
		try {
			um.addUser(email, password);
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Error @ doPost (RegisterServlet): " + e.getMessage());
		}
		
		response.setHeader("message", "Register successfull!");
	}

}
