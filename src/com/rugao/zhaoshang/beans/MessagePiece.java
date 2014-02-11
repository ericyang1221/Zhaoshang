package com.rugao.zhaoshang.beans;

public class MessagePiece {
	private String activityName;
	private int messageId;
	private String content;
	private int posterId;
	private String posterIdDisplay;
	private String createDate;

	public MessagePiece() {
	}

	public MessagePiece(String posterIdDisplay, String content) {
		this.posterIdDisplay = posterIdDisplay;
		this.content = content;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public int getMessageId() {
		return messageId;
	}

	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getPosterId() {
		return posterId;
	}

	public void setPosterId(int posterId) {
		this.posterId = posterId;
	}

	public String getPosterIdDisplay() {
		return posterIdDisplay;
	}

	public void setPosterIdDisplay(String posterIdDisplay) {
		this.posterIdDisplay = posterIdDisplay;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
