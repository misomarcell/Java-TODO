package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.TaskManager;

@WebServlet(urlPatterns = "", name = "IndexServlet")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TaskManager tm = new TaskManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ( !request.getParameterMap().containsKey("key") ) {
			response.setHeader("key", "asdasd");
		}

		request.getRequestDispatcher("./index.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameterMap().containsKey("task")) {
			Integer id = tm.saveTask(request.getParameter("task"), 0, request.getSession());
			response.setHeader("id", id.toString());
		}

		if (request.getParameterMap().containsKey("complete")) {
			tm.completeTask(Integer.valueOf(request.getParameter("complete")));
		}

		if (request.getParameterMap().containsKey("del")) {
			tm.removeTask(Integer.valueOf(request.getParameter("del")));
		}
	}

}
