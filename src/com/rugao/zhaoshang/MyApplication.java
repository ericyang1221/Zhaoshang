package com.rugao.zhaoshang;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Application;

import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.beans.ValueBean;
import com.rugao.zhaoshang.net.HttpRequestHelper;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;
import com.rugao.zhaoshang.utils.Utils;

public class MyApplication extends Application {
	private UserBean userBean;
	private HttpRequestHelper hrh;
	private List<ValueBean> projectStage;
	private List<ValueBean> projectIndustryType;
	private List<ValueBean> projectIndustryDetail;
	private List<ValueBean> projectPosition;
	private List<ValueBean> projectPolicy;
	private List<ValueBean> projectEnviroment;
	private List<ValueBean> projectUnit;
	private List<ValueBean> projectPeople;
	private List<ValueBean> projectManager;
	private List<ValueBean> activityProject;

	@Override
	public void onCreate() {
		super.onCreate();
		initData();
	}

	public HttpRequestHelper getHttpRequestHelper() {
		if (hrh == null) {
			hrh = new HttpRequestHelper();
		}
		return hrh;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(final UserBean userBean) {
		this.userBean = userBean;

		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					String url = URLGenerater.makeUrl(
							Constants.PROJECT_GETPEOPLEINFO, new String[] {
									"1", String.valueOf(userBean.getUserId()),
									userBean.getMemo() });
					JSONObject jo = getHttpRequestHelper()
							.sendRequestAndReturnJson(url);
					JSONArray ja = jo.getJSONArray("ResultData");
					Utils.putProjectPeople(MyApplication.this, ja.toString());
					projectPeople = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectPeople = Utils.convertJAStr2SA(Utils
							.getProjectPeople(MyApplication.this));
				}

				try {
					String url = URLGenerater.makeUrl(
							Constants.PROJECT_GETPEOPLEINFO, new String[] {
									"2", String.valueOf(userBean.getUserId()),
									userBean.getMemo() });
					JSONObject jo = getHttpRequestHelper()
							.sendRequestAndReturnJson(url);
					JSONArray ja = jo.getJSONArray("ResultData");
					Utils.putProjectManager(MyApplication.this, ja.toString());
					projectManager = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectManager = Utils.convertJAStr2SA(Utils
							.getProjectManager(MyApplication.this));
				}

				try {
					String url = URLGenerater.makeUrl(
							Constants.ACTIVITY_GETPROJECT, new String[] {
									String.valueOf(userBean.getUserId()),
									userBean.getMemo() });
					JSONObject jo = getHttpRequestHelper()
							.sendRequestAndReturnJson(url);
					JSONArray ja = jo.getJSONArray("ResultData");
					Utils.putActivityProject(MyApplication.this, ja.toString());
					activityProject = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					activityProject = Utils.convertJAStr2SA(Utils
							.getActivityProject(MyApplication.this));
				}
			}
		}).start();
	}

	public List<ValueBean> getProjectStage() {
		return projectStage;
	}

	public List<ValueBean> getProjectIndustryType() {
		return projectIndustryType;
	}

	public List<ValueBean> getProjectIndustryDetail() {
		return projectIndustryDetail;
	}

	public List<ValueBean> getProjectPosition() {
		return projectPosition;
	}

	public List<ValueBean> getProjectPolicy() {
		return projectPolicy;
	}

	public List<ValueBean> getProjectEnviroment() {
		return projectEnviroment;
	}

	public List<ValueBean> getProjectUnit() {
		return projectUnit;
	}

	public List<ValueBean> getProjectPeople() {
		return projectPeople;
	}

	public List<ValueBean> getProjectManager() {
		return projectManager;
	}

	public List<ValueBean> getActivityProject() {
		return activityProject;
	}

	private void initData() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETSTAGE);
					Utils.putProjectStage(MyApplication.this, ja.toString());
					projectStage = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectStage = Utils.convertJAStr2SA(Utils
							.getProjectStage(MyApplication.this));
				}

				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETINDUSTRYTYPE);
					Utils.putProjectIndustryType(MyApplication.this,
							ja.toString());
					projectIndustryType = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectIndustryType = Utils.convertJAStr2SA(Utils
							.getProjectIndustryType(MyApplication.this));
				}

				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETINDUSTRYDETAIL);
					Utils.putProjectIndustryType(MyApplication.this,
							ja.toString());
					projectIndustryDetail = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectIndustryDetail = Utils.convertJAStr2SA(Utils
							.getProjectIndustryDetail(MyApplication.this));
				}

				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETPOSITION);
					Utils.putProjectPosition(MyApplication.this, ja.toString());
					projectPosition = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectPosition = Utils.convertJAStr2SA(Utils
							.getProjectPosition(MyApplication.this));
				}

				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETPOLICY);
					Utils.putProjectPolicy(MyApplication.this, ja.toString());
					projectPolicy = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectPolicy = Utils.convertJAStr2SA(Utils
							.getProjectPolicy(MyApplication.this));
				}

				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETENVIROMENT);
					Utils.putProjectEnviroment(MyApplication.this,
							ja.toString());
					projectEnviroment = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectEnviroment = Utils.convertJAStr2SA(Utils
							.getProjectEnviroment(MyApplication.this));
				}

				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETUNIT);
					Utils.putProjectUnit(MyApplication.this, ja.toString());
					projectUnit = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					// e.printStackTrace();
					projectUnit = Utils.convertJAStr2SA(Utils
							.getProjectUnit(MyApplication.this));
				}
			}
		}).start();
	}
}
