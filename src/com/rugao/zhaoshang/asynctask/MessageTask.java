package com.rugao.zhaoshang.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.rugao.zhaoshang.BaseActivity;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.MessageBean;

public class MessageTask extends BaseAsyncTask {

	public MessageTask(Context context) {
		super(context);
	}

	@Override
	protected DataBean processResult(JSONObject result) throws JSONException {
		return new MessageBean(result);
	}

	@Override
	protected Integer getDialogId() {
		return BaseActivity.LOADING_DIALOG;
	}

}
