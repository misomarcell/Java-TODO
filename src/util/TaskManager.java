package util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

public class TaskManager {

	SQLConnector sqlConnector = new SQLConnector();

	public void saveTask(String content, int index, HttpSession session) {
		if ( !content.equals("") ) {
			sqlConnector.sendQuery(
					"INSERT INTO tasks VALUES ('0', '" + content + "', '0', '" + session.getId().toString() + "', '0');");
		}
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

	public Task getTaskByResultSet(ResultSet rs) {
		Task task = null;
		try {
			task = new Task(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5));
		} catch (SQLException e) {
			System.out.println("Error @ getTaskByResultSet: " + e.getMessage());
		}

		return task;
	}

}
