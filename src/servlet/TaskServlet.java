package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.TaskManager;

@WebServlet(urlPatterns="/task", name="TaskServlet") 
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TaskManager tm = new TaskManager();
		String key = "NONE";
		
		if ( request.getParameterMap().containsKey("key")) {
			key = request.getParameter("key");
		}
		
		response.getWriter().println(tm.formatTasks(tm.getTaskList(key)));
	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		// TODO Auto-generated method stub
//		doGet(request, response);
//	}

}
