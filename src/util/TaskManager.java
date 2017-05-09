package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class TaskManager {

	SQLConnector sqlConnector = new SQLConnector();

	public Integer getCurrentId() {
		ResultSet rs = sqlConnector.getData("SELECT MAX(id) FROM tasks");
		try {
			if ( rs.next() ) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			System.out.println("Error @ getCurrentId: " + e.getMessage());
		}
		return -1;
	}
	
	public int saveTask(String content, int index, HttpSession session) {
		int id = getCurrentId();
		
		if ( !content.equals("") ) {
			sqlConnector.sendQuery(
					"INSERT INTO tasks VALUES ('" + id + "', '" + content + "', '0', '" + session.getId().toString() + "', '0');");
		}
		
		return id;
	}

	public Task getTask(int id) {

		ResultSet rs = sqlConnector.getData("SELECT * FROM tasks WHERE id = '" + id + "'");
		return getTaskByResultSet(rs);
	}

	public List<Task> getTaskList() {
		List<Task> taskList = new ArrayList<>();
		ResultSet rs = sqlConnector.getData("SELECT * FROM tasks");

		try {
			while (rs.next()) {
				taskList.add(getTaskByResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error @ getTaskList: " + e.getMessage());
		}
		return taskList;
	}
	
	public List<Task> getTaskList(HttpSession session) {
		List<Task> taskList = new ArrayList<>();
		ResultSet rs = sqlConnector.getData("SELECT * FROM tasks WHERE sessionID = '" + session.getId() + "'");

		try {
			while (rs.next()) {
				taskList.add(getTaskByResultSet(rs));
			}
		} catch (SQLException e) {
			System.out.println("Error @ getTaskList: " + e.getMessage());
		}
		return taskList;
	}

	public Task getTaskByResultSet(ResultSet rs) {
		Task task = null;
		try {
			task = new Task(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
		} catch (SQLException e) {
			System.out.println("Error @ getTaskByResultSet: " + e.getMessage());
		}

		return task;
	}
	
	public void removeTask(int id) {
		sqlConnector.sendQuery("DELETE FROM tasks WHERE id = '" + id + "'");
	}
	
	public String formatTasks(List<Task> tasks) {
		
		String html = "";
		for ( Task task : tasks ) {
			String icons = "<div class='icons'><i onClick='iconClick(this);' class='fa fa-trash' aria-hidden='true'></i></div></li>";
			html = "<li id='" + task.getId() + "' class='task'>" + task.getContent() + icons + html;
		}
		
		return html;
	}

}
