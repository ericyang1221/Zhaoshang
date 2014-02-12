package com.rugao.zhaoshang.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class ActivityItem {
	private int activityId;
	private String activityIdDisplay;
	private Integer projectId;
	private String projectIdDisplay;
	private Integer stageId;
	private String stageIdDisplay;
	private String date;
	private String address;
	private String actiMemo;
	private String problems;
	private String leaderIds;
	private String leaderIdsDisplay;
	private String imageUrls;
	private int createId;
	private String createIdDisplay;
	private String createDate;
	private int year;
	private int month;
	private int day;

	public ActivityItem() {
		leaderIds = "";
		leaderIdsDisplay = "";
	}

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

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectIdDisplay() {
		return projectIdDisplay;
	}

	public void setProjectIdDisplay(String projectIdDisplay) {
		this.projectIdDisplay = projectIdDisplay;
	}

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
		this.stageId = stageId;
	}

	public String getStageIdDisplay() {
		return stageIdDisplay;
	}

	public void setStageIdDisplay(String stageIdDisplay) {
		this.stageIdDisplay = stageIdDisplay;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getActiMemo() {
		return actiMemo;
	}

	public void setActiMemo(String actiMemo) {
		this.actiMemo = actiMemo;
	}

	public String getProblems() {
		return problems;
	}

	public void setProblems(String problems) {
		this.problems = problems;
	}

	public String getLeaderIds() {
		return leaderIds;
	}

	public void setLeaderIds(String leaderIds) {
		this.leaderIds = leaderIds;
	}

	public String getLeaderIdsDisplay() {
		return leaderIdsDisplay;
	}

	public void setLeaderIdsDisplay(String leaderIdsDisplay) {
		this.leaderIdsDisplay = leaderIdsDisplay;
	}

	public String getImageUrls() {
		return imageUrls;
	}

	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}

	public int getCreateId() {
		return createId;
	}

	public void setCreateId(int createId) {
		this.createId = createId;
	}

	public String getCreateIdDisplay() {
		return createIdDisplay;
	}

	public void setCreateIdDisplay(String createIdDisplay) {
		this.createIdDisplay = createIdDisplay;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	@Override
	public String toString() {
		return activityIdDisplay;
	}

	public List<NameValuePair> getPostParams(String userId, String memo,
			boolean isAdd) {
		List<NameValuePair> p = new ArrayList<NameValuePair>();
		p.add(new BasicNameValuePair("Memo", memo));
		p.add(new BasicNameValuePair("UserId", String.valueOf(userId)));
		if (isAdd) {
			if (problems != null && problems.length() > 0 && leaderIds != null
					&& leaderIds.length() > 2) {
				p.add(new BasicNameValuePair("Problems", problems));
				p.add(new BasicNameValuePair("LeaderIds", leaderIds));
			}
		} else {
			p.add(new BasicNameValuePair("ActivityId", String
					.valueOf(activityId)));
		}
		p.add(new BasicNameValuePair("ProjectId", projectId==null?null:String.valueOf(projectId)));
		p.add(new BasicNameValuePair("StageId", stageId==null?null:String.valueOf(stageId)));
		p.add(new BasicNameValuePair("Address", address));
		p.add(new BasicNameValuePair("ActivityName", activityIdDisplay));
		p.add(new BasicNameValuePair("actiMemo", actiMemo));
		p.add(new BasicNameValuePair("Date", date));
		System.out.println(p.toString());
		return p;
	}
}
