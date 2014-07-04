package com.rugao.zhaoshang;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Application;
import android.util.Log;

import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.beans.ValueBean;
import com.rugao.zhaoshang.net.HttpRequestHelper;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;
import com.rugao.zhaoshang.utils.Utils;

public class MyApplication extends Application {
	private final String TAG = "MyApplication";
	private UserBean userBean;
	private HttpRequestHelper hrh;
	private List<ValueBean> projectStage;
	private List<ValueBean> projectIndustryType;
	private List<ValueBean> projectIndustryDetail;
	private List<ValueBean> projectPosition;
	private List<ValueBean> projectPolicy;
	private List<ValueBean> projectEnviroment;
	private List<ValueBean> projectUnit;
	private List<ValueBean> projectType;
	private List<ValueBean> projectStatus;
	private List<ValueBean> activityLeader;
	private List<ValueBean> activityProject;
	private List<ValueBean> projectWorker;
	private String newNotice;

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
							Constants.ACTIVITY_GETLEADERS, new String[] {
									String.valueOf(userBean.getUserId()),
									userBean.getMemo() });
					JSONObject jo = getHttpRequestHelper()
							.sendRequestAndReturnJson(url);
					JSONArray ja = jo.getJSONArray("ResultData");
					Utils.putActivityLeaders(MyApplication.this, ja.toString());
					activityLeader = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					activityLeader = Utils.convertJAStr2SA(Utils
							.getActivityLeaders(MyApplication.this));
				}
				
				try {
					String url = URLGenerater.makeUrl(
							Constants.PROJECT_GETWORKERS, new String[] {
									String.valueOf(userBean.getUserId()),
									userBean.getMemo() });
					JSONObject jo = getHttpRequestHelper()
							.sendRequestAndReturnJson(url);
					Log.d(TAG, "PROJECT_GETWORKERS: "+jo.toString());
					JSONArray ja = jo.getJSONArray("ResultData");
					Utils.putProjectWorker(MyApplication.this, ja.toString());
					projectWorker = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					projectWorker = Utils.convertJAStr2SA(Utils
							.getProjectWorker(MyApplication.this));
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
					activityProject = Utils.convertJAStr2SA(Utils
							.getActivityProject(MyApplication.this));
				}
				
				try {
					JSONObject jo = getHttpRequestHelper()
							.sendRequestAndReturnJson(
									Constants.DOMAIN
											+ Constants.NEW_NOTICE_GET);
					if (jo != null) {
						newNotice = jo.optString("ResultData");
					}
				} catch (Exception e) {
					Log.e(TAG, e.toString());
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

	public List<ValueBean> getProjectWorker() {
		return projectWorker;
	}

	public List<ValueBean> getActivityLeader() {
		return activityLeader;
	}

	public List<ValueBean> getActivityProject() {
		return activityProject;
	}

	public List<ValueBean> getProjectType() {
		return projectType;
	}

	public List<ValueBean> getProjectStatus() {
		return projectStatus;
	}

	public String getNewNotice() {
		return newNotice;
	}

	public void setNewNotice(String newNotice) {
		this.newNotice = newNotice;
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
				
				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETTYPE);
					Utils.putProjectType(MyApplication.this, ja.toString());
					projectType = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					projectType = Utils.convertJAStr2SA(Utils
							.getProjectType(MyApplication.this));
				}
				
				try {
					JSONArray ja = getHttpRequestHelper()
							.sendRequestAndReturnJsonArray(
									Constants.DOMAIN
											+ Constants.PROJECT_GETSTATUS);
					Utils.putProjectStatus(MyApplication.this, ja.toString());
					projectStatus = Utils.convertJA2SA(ja);
				} catch (Exception e) {
					projectStatus = Utils.convertJAStr2SA(Utils
							.getProjectStatus(MyApplication.this));
				}
			}
		}).start();
	}
}
