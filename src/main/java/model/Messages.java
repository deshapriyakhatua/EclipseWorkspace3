package model;

public class Messages {

	String groupid;
	String senderid;
	String message;
	String time;

	public Messages() {
	
	}
	
	public Messages(String groupid, String senderid, String message, String time) {
		
		this.groupid = groupid;
		this.senderid = senderid;
		this.message = message;
		this.time = time;
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public String getSenderid() {
		return senderid;
	}

	public void setSenderid(String senderid) {
		this.senderid = senderid;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
