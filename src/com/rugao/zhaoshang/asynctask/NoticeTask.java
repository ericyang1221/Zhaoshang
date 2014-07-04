package com.rugao.zhaoshang.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.rugao.zhaoshang.BaseActivity;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.NoticeBean;

public class NoticeTask extends BaseAsyncTask {

	public NoticeTask(Context context) {
		super(context);
	}

	@Override
	protected DataBean processResult(JSONObject result) throws JSONException {
		return new NoticeBean(result);
	}

	@Override
	protected Integer getDialogId() {
		return BaseActivity.LOADING_DIALOG;
	}

}
