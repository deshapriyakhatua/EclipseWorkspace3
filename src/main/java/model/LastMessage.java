package model;

public class LastMessage {

	String groupid;
	String senderid;
	String messageContent;
	String time;
	
	public LastMessage() {
		
	}

	public LastMessage(String groupid, String senderid, String messageContent, String time) {
		super();
		this.groupid = groupid;
		this.senderid = senderid;
		this.messageContent = messageContent;
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
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
}
