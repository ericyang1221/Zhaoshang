package com.rugao.zhaoshang.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.rugao.zhaoshang.BaseActivity;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.UserBean;

public class LoginTask extends BaseAsyncTask {

	public LoginTask(Context context) {
		super(context);
	}

	@Override
	protected DataBean processResult(JSONObject result) throws JSONException {
		return new UserBean(result);
	}

	@Override
	protected Integer getDialogId() {
		return BaseActivity.LOGIN_DIALOG;
	}

}
