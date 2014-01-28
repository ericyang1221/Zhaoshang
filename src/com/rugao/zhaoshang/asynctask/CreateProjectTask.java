package com.rugao.zhaoshang.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.rugao.zhaoshang.BaseActivity;
import com.rugao.zhaoshang.beans.DataBean;

public class CreateProjectTask extends BaseAsyncTask {

	public CreateProjectTask(Context context) {
		super(context);
	}

	@Override
	protected DataBean processResult(JSONObject result) throws JSONException {
		return new DataBean(result);
	}

	@Override
	protected Integer getDialogId() {
		return BaseActivity.LOGIN_DIALOG;
	}

	protected void setPost() {
		isPost = true;
	}
}
