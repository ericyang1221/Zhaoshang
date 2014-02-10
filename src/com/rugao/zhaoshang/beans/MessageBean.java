package com.rugao.zhaoshang.beans;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageBean extends DataBean {
	private int totalCount;
	private List<Message> messageList;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Message> getMessages() {
		return messageList;
	}

	public void setMessages(List<Message> messageList) {
		this.messageList = messageList;
	}

	public MessageBean(JSONObject rs) throws JSONException {
		super(rs);
		if (resultData != null) {
			if (resultData.has("totalCount")) {
				totalCount = resultData.getInt("totalCount");
			}
			if (resultData.has("data")) {
				messageList = new ArrayList<Message>();
				JSONArray ja = resultData.getJSONArray("data");
				if (ja != null) {
					for (int i = 0; i < ja.length(); i++) {
						JSONObject jo = ja.getJSONObject(i);
						Message m = new Message();
						if (jo.has("ActivityId")) {
							int activityId = -1;
							try {
								activityId = jo.getInt("ActivityId");
							} catch (Exception e) {
								e.printStackTrace();
							}
							m.setActivityId(activityId);
						}
						if (jo.has("ActivityId_display")) {
							m.setActivityIdDisplay(jo
									.getString("ActivityId_display"));
						}
						if (jo.has("ProjectId")) {
							int pid = -1;
							try {
								pid = jo.getInt("ProjectId");
							} catch (Exception e) {
								e.printStackTrace();
							}
							m.setProjectId(pid);
						}
						if (jo.has("ProjectId_display")) {
							m.setProjectIdDisplay(jo
									.getString("ProjectId_display"));
						}
						if (jo.has("count")) {
							m.setCount(jo.getString("count"));
						}
						if (jo.has("CreateDate")) {
							m.setCreateDate(jo.getString("CreateDate"));
						}
						messageList.add(m);
					}
				}
			}
		}
	}
}
