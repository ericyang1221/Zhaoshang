package com.rugao.zhaoshang.beans;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityBean extends DataBean {
	private int totalCount;
	private List<ActivityItem> aiList;

	public ActivityBean(JSONObject rs) throws JSONException {
		super(rs);
		if (resultData.has("totalCount")) {
			totalCount = resultData.getInt("totalCount");
		}
		if (resultData.has("data")) {
			JSONArray ja = resultData.getJSONArray("data");
			aiList = new ArrayList<ActivityItem>();
			for (int i = 0; i < ja.length(); i++) {
				JSONObject jo = ja.getJSONObject(i);
				ActivityItem ai = new ActivityItem();
				if (jo.has("ActivityId")) {
					ai.setActivityId(jo.getInt("ActivityId"));
				}
				if (jo.has("ActivityId_display")) {
					ai.setActivityIdDisplay(jo.getString("ActivityId_display"));
				}
				if (jo.has("ProjectId")) {
					Integer pid = null;
					try {
						pid = jo.getInt("ProjectId");
					} catch (Exception e) {

					}
					ai.setProjectId(pid);
				}
				if (jo.has("ProjectId_display")) {
					ai.setProjectIdDisplay(jo.getString("ProjectId_display"));
				}
				if (jo.has("StageId")) {
					Integer s = null;
					try {
						s = jo.getInt("StageId");
					} catch (Exception e) {

					}
					ai.setStageId(s);
				}
				if (jo.has("StageId_display")) {
					ai.setStageIdDisplay(jo.getString("StageId_display"));
				}
				if (jo.has("Date")) {
					ai.setDate(jo.getString("Date"));
				}
				if (jo.has("Address")) {
					String a = jo.getString("Address");
					ai.setAddress(a == null || a.equals("null") ? null : a);
				}
				if (jo.has("actiMemo")) {
					String am = jo.getString("actiMemo");
					ai.setActiMemo(am == null || am.equals("null") ? null : am);
				}
				if (jo.has("Problems")) {
					ai.setProblems(jo.getString("Problems"));
				}
				if (jo.has("LeaderIds")) {
					ai.setLeaderIds(jo.getString("LeaderIds"));
				}
				if (jo.has("LeaderIds_display")) {
					ai.setLeaderIdsDisplay(jo.getString("LeaderIds_display"));
				}
				if (jo.has("ImageUrls")) {
					ai.setImageUrls(jo.getString("ImageUrls"));
				}
				if (jo.has("CreateId")) {
					ai.setCreateId(jo.getInt("CreateId"));
				}
				if (jo.has("CreateId_display")) {
					ai.setCreateIdDisplay(jo.getString("CreateId_display"));
				}
				if (jo.has("CreateDate")) {
					ai.setCreateDate(jo.getString("CreateDate"));
				}
				if (jo.has("year")) {
					ai.setYear(jo.getInt("year"));
				}
				if (jo.has("month")) {
					ai.setMonth(jo.getInt("month"));
				}
				if (jo.has("day")) {
					ai.setDay(jo.getInt("day"));
				}
				aiList.add(ai);
			}
		}
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<ActivityItem> getAiList() {
		return aiList;
	}

	public void setAiList(List<ActivityItem> aiList) {
		this.aiList = aiList;
	}

}
