package com.rugao.zhaoshang.beans;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessagePieceBean {
	private List<MessagePiece> messagePieceList;
	protected boolean result;
	protected String resultMsg;
	protected JSONArray resultData;

	public boolean getResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public List<MessagePiece> getMessages() {
		return messagePieceList;
	}

	public void setMessages(List<MessagePiece> messagePieceList) {
		this.messagePieceList = messagePieceList;
	}

	public MessagePieceBean(JSONObject rs) throws JSONException {
		if (rs != null) {
			if (rs.has("Result")) {
				result = rs.getBoolean("Result");
			}
			if (rs.has("ResultMsg")) {
				resultMsg = rs.getString("ResultMsg");
			}
			if (rs.has("ResultData") && !rs.isNull("ResultData")) {
				resultData = rs.getJSONArray("ResultData");
				if (resultData != null) {
					messagePieceList = new ArrayList<MessagePiece>();
					for (int i = 0; i < resultData.length(); i++) {
						JSONObject jo = resultData.getJSONObject(i);
						MessagePiece m = new MessagePiece();
						if (jo.has("ActivityName")) {
							m.setActivityName(jo.getString("ActivityName"));
						}
						if (jo.has("MessageId")) {
							int mid = -1;
							try {
								mid = jo.getInt("MessageId");
							} catch (Exception e) {
								e.printStackTrace();
							}
							m.setMessageId(mid);
						}
						if (jo.has("Content")) {
							m.setContent(jo.getString("Content"));
						}
						if (jo.has("PosterID")) {
							int pid = -1;
							try {
								pid = jo.getInt("PosterID");
							} catch (Exception e) {
								e.printStackTrace();
							}
							m.setPosterId(pid);
						}
						if (jo.has("PosterID_display")) {
							m.setPosterIdDisplay(jo
									.getString("PosterID_display"));
						}
						if (jo.has("CreateDate")) {
							m.setCreateDate(jo.getString("CreateDate"));
						}
						messagePieceList.add(m);
					}
				}
			}
		}

	}
}
