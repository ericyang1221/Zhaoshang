package com.rugao.zhaoshang.beans;

public class Message {
	private int activityId;
	private String activityIdDisplay;
	private int projectId;
	private String projectIdDisplay;
	private String count;
	private String createDate;

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public String getActivityIdDisplay() {
		return activityIdDisplay;
	}

	public void setActivityIdDisplay(String activityIdDisplay) {
		this.activityIdDisplay = activityIdDisplay;
	}

	public int getProjectId() {
		return projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}

	public String getProjectIdDisplay() {
		return projectIdDisplay;
	}

	public void setProjectIdDisplay(String projectIdDisplay) {
		this.projectIdDisplay = projectIdDisplay;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
