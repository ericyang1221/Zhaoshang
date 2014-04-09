package com.rugao.zhaoshang.utils;

public class Constants {
	public static final String DOMAIN = "http://rugao.sapb1ec.com:30000/Api/";
//	public static final String DOMAIN = "http://rugao.sapb1ec.com/api/";
	public static final String API_LOGIN = "Home/Login?username=?&password=?";
	public static final String API_GET_PROJECTS = "project/Get?pageIndex=?&pageSize=?&UserId=?&Memo=?";
	public static final String PROJECT_GETSTAGE = "Project/GetStage";
	public static final String PROJECT_GETINDUSTRYTYPE = "Project/GetIndustryType";
	public static final String PROJECT_GETINDUSTRYDETAIL = "Project/GetIndustryDetail";
	public static final String PROJECT_GETPOSITION = "Project/GetPosition";
	public static final String PROJECT_GETPOLICY = "Project/GetPolicy";
	public static final String PROJECT_GETENVIROMENT = "Project/GetEnviroment";
	public static final String PROJECT_GETUNIT = "Project/GetUnit";
	public static final String PROJECT_GETPEOPLEINFO = "Project/GetPeopleInfo?type=?&UserId=?&Memo=?";
	public static final String PROJECT_CREATE = "project/create";
	public static final String PROJECT_EDIT = "project/Edit";
	public static final String ACTIVITY_GET = "Activity/Get?UserId=?&Memo=?&year=?&month=?";
	public static final String ACTIVITY_GETPROJECT = "Activity/getProject?UserId=?&Memo=?";
	public static final String MESSAGE_GET = "Message/Get?UserId=?&Memo=?&pageIndex=0&pageSize=999999999";
	public static final String MESSAGE_GETLIST = "Message/GetList?UserId=?&Memo=?&ActivityId=?";
	public static final String MESSAGE_REVIEW = "Message/Review?UserId=?&Memo=?&ActivityId=?";
	public static final String MESSAGE_REPLY = "Message/Reply?UserId=?&Memo=?&ActivityId=?&Content=?";
	public static final String ACTIVITY_CREATE = "Activity/create";
	public static final String ACTIVITY_EDIT = "Activity/edit";
}
