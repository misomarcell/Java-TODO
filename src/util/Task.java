package util;

public class Task {

	int id;
	String content;
	int index;
	String sessionID;
	int status;
	
	public Task(int id, String content, int index, String sessionID, int status) {
		this.content = content;
		this.index = index;
		this.sessionID = sessionID;
		this.status = status;
	}
	
	public Task(String content, int index, String sessionID) {
		this.content = content;
		this.index = index;
		this.sessionID = sessionID;
		this.status = 0;
	}
}
