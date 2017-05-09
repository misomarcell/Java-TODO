package util;

public class Task {

	int id;
	String content;
	int index;
	String sessionID;
	int status;
	
	public Task(int id, String content, int index, String sessionID, int status) {
		this.id = id;
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

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public int getIndex() {
		return index;
	}

	public String getSessionID() {
		return sessionID;
	}

	public int getStatus() {
		return status;
	}
}
