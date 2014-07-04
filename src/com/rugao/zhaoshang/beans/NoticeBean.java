package com.rugao.zhaoshang.beans;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class NoticeBean extends DataBean {
	private int totalCount;
	private List<Notice> noticeList;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Notice> getNoticeList() {
		return noticeList;
	}

	public void setNoticeList(List<Notice> noticeList) {
		this.noticeList = noticeList;
	}

	public NoticeBean(JSONObject rs) throws JSONException {
		super(rs);
		if (resultData != null) {
			if (resultData.has("totalCount")) {
				totalCount = resultData.getInt("totalCount");
			}
			if (resultData.has("data")) {
				noticeList = new ArrayList<Notice>();
				JSONArray ja = resultData.getJSONArray("data");
				if (ja != null) {
					for (int i = 0; i < ja.length(); i++) {
						JSONObject jo = ja.getJSONObject(i);
						Notice n = new Notice();
						n.setNoticeId(jo.optInt("NoticeId"));
						n.setNoticeIdDisplay(jo.optString("NoticeId_display"));
						n.setCreateDate(jo.optString("CreateDate"));
						noticeList.add(n);
					}
				}
			}
		}
	}
}
