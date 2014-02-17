package com.rugao.zhaoshang.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.rugao.zhaoshang.BaseActivity;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.MessagePieceBean;

public class MessageListTask extends BaseAsyncTask {

	public MessageListTask(Context context) {
		super(context);
	}

	@Override
	protected Integer getDialogId() {
		return BaseActivity.LOADING_DIALOG;
	}

	@Override
	protected DataBean processResult(JSONObject result) throws JSONException {
		Object obj = null;
		DataBean db = null;
		obj = new MessagePieceBean(result);
		db = new DataBean(null);
		db.setObj(obj);
		return db;
	}
}