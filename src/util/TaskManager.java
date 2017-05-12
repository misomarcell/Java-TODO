package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

public class TaskManager {

	SQLConnector sqlConnector = new SQLConnector();
	Data data = Data.newInstance();
	
	public Integer getCurrentId() {
		ResultSet rs = sqlConnector.getData("SELECT MAX(id) FROM tasks");
		try {
			if (rs.next()) {
				return rs.getInt(1) + 1;
			}
		} catch (SQLException e) {
			System.out.println("Error @ getCurrentId: " + e.getMessage());
		}
		return -1;
	}

	public int saveTask(String content, int index, HttpSession session) {
		int id = getCurrentId();

		if (!content.equals("")) {
			sqlConnector.sendQuery("INSERT INTO tasks VALUES ('" + id + "', '" + content + "', '0', '"
					+ data.getEmailBySessionID(session.getId()) + "', '0');");
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
		Data data = Data.newInstance();
		ResultSet rs = sqlConnector.getData("SELECT * FROM tasks WHERE email = '" + data.getEmailBySessionID(session.getId()) + "'");

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

	public void completeTask(int id) {
		ResultSet rs = sqlConnector.getData("SELECT status FROM tasks WHERE id = '" + id + "'");
		String status = "";
		try {
			if ( rs.next() ) {
				status = rs.getString(1);
			}
		} catch (SQLException e) {
			System.out.println("Error @ completeTask: " + e.getMessage());
		}
		
		String newStatus = "0";
		if ( status.equals("0") ) {
			newStatus = "2";
		} else {
			newStatus = "0";
		}
		
		sqlConnector.sendQuery("UPDATE tasks SET status = '" + newStatus + "' WHERE id = '" + id + "'");
	}

	public void removeTask(int id) {
		sqlConnector.sendQuery("DELETE FROM tasks WHERE id = '" + id + "'");
	}

	public String formatTasks(List<Task> tasks) {
		Gson gTasks = new Gson();
		return gTasks.toJson(tasks);		
	}

}
