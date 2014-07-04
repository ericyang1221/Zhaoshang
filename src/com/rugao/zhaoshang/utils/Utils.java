package com.rugao.zhaoshang.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.Context;
import android.content.SharedPreferences;

import com.rugao.zhaoshang.beans.ValueBean;

public class Utils {
	public static List<ValueBean> convertJA2SA(JSONArray ja) {
		List<ValueBean> ret = null;
		if (ja != null) {
			int size = ja.length();
			ret = new ArrayList<ValueBean>();
			for (int i = 0; i < size; i++) {
				String key = "";
				try {
					key = ja.getJSONObject(i).getString("Key");
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					ret.add(new ValueBean(key, ja.getJSONObject(i).getString(
							"Value")));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	public static List<ValueBean> convertJAStr2SA(String jaStr) {
		JSONArray ja = null;
		if (jaStr != null && jaStr.length() > 0) {
			try {
				ja = new JSONArray(jaStr);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		return convertJA2SA(ja);
	}

	private static SharedPreferences getSharedPreferences(Context context) {
		return context.getSharedPreferences("DATA", Context.MODE_PRIVATE);
	}

	public static void putProjectStage(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_STAGE", str);
		editor.commit();
	}

	public static String getProjectStage(Context context) {
		return getSharedPreferences(context).getString("PROJECT_STAGE", null);
	}

	public static void putProjectIndustryType(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_INDUSTRYTYPE", str);
		editor.commit();
	}

	public static String getProjectIndustryType(Context context) {
		return getSharedPreferences(context).getString("PROJECT_INDUSTRYTYPE",
				null);
	}

	public static void putProjectIndustryDetail(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_INDUSTRYDETAIL", str);
		editor.commit();
	}

	public static String getProjectIndustryDetail(Context context) {
		return getSharedPreferences(context).getString(
				"PROJECT_INDUSTRYDETAIL", null);
	}

	public static void putProjectPosition(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_POSITION", str);
		editor.commit();
	}

	public static String getProjectPosition(Context context) {
		return getSharedPreferences(context)
				.getString("PROJECT_POSITION", null);
	}

	public static void putProjectPolicy(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_POLICY", str);
		editor.commit();
	}

	public static String getProjectPolicy(Context context) {
		return getSharedPreferences(context).getString("PROJECT_POLICY", null);
	}

	public static void putProjectEnviroment(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_ENVIROMENT", str);
		editor.commit();
	}

	public static String getProjectEnviroment(Context context) {
		return getSharedPreferences(context).getString("PROJECT_ENVIROMENT",
				null);
	}

	public static void putProjectUnit(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_UNIT", str);
		editor.commit();
	}

	public static String getProjectUnit(Context context) {
		return getSharedPreferences(context).getString("PROJECT_UNIT", null);
	}
	
	public static void putProjectType(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_TYPE", str);
		editor.commit();
	}

	public static String getProjectType(Context context) {
		return getSharedPreferences(context).getString("PROJECT_TYPE", null);
	}
	
	public static void putProjectStatus(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_STATUS", str);
		editor.commit();
	}

	public static String getProjectStatus(Context context) {
		return getSharedPreferences(context).getString("PROJECT_STATUS", null);
	}

	public static void putProjectWorker(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_WORKER", str);
		editor.commit();
	}
	
	public static String getProjectWorker(Context context) {
		return getSharedPreferences(context).getString("PROJECT_WORKER", null);
	}
	
	public static void putActivityLeaders(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("ACTIVITY_LEADERS", str);
		editor.commit();
	}
	
	public static String getActivityLeaders(Context context) {
		return getSharedPreferences(context).getString("ACTIVITY_LEADERS", null);
	}

	public static void putProjectManager(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("PROJECT_MANAGER", str);
		editor.commit();
	}
	
	public static String getProjectManager(Context context) {
		return getSharedPreferences(context).getString("PROJECT_MANAGER", null);
	}
	
	public static void putActivityProject(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("ACTIVITY_PROJECT", str);
		editor.commit();
	}
	
	public static String getActivityProject(Context context) {
		return getSharedPreferences(context).getString("ACTIVITY_PROJECT", null);
	}
	
	public static void putUsername(Context context, String str) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putString("USER_NAME", str);
		editor.commit();
	}
	
	public static String getUsername(Context context) {
		return getSharedPreferences(context).getString("USER_NAME", null);
	}
	
	public static void putIsDemo(Context context, boolean isDemo) {
		SharedPreferences.Editor editor = getSharedPreferences(context).edit();
		editor.putBoolean("ISDEMO", isDemo);
		editor.commit();
	}
	
	public static boolean getIsDemo(Context context) {
		return getSharedPreferences(context).getBoolean("ISDEMO", false);
	}
}
