package com.rugao.zhaoshang.beans;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DataBean {
	protected boolean result;
	protected String resultMsg;
	protected JSONObject resultData;

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

	public JSONObject getResultData() {
		return resultData;
	}

	public void setResultData(JSONObject resultData) {
		this.resultData = resultData;
	}

	public DataBean(JSONObject rs) throws JSONException {
		if (rs != null) {
			if (rs.has("Result")) {
				result = rs.getBoolean("Result");
			}
			if (rs.has("ResultMsg")) {
				resultMsg = rs.getString("ResultMsg");
			}
			if (rs.has("ResultData") && !rs.isNull("ResultData")) {
				resultData = rs.getJSONObject("ResultData");
			}
		}
	}
}
