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
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		Object obj = null;
		DataBean db = null;
		try {
			obj = new MessagePieceBean(result);
			db = new DataBean(null);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		db.setObj(obj);
		dv.setData(db);
		Integer dialogId = getDialogId();
		if (dialogId != null) {
			BaseActivity ba = getBaseActivity();
			if (ba != null) {
				ba.dismissDialogIfExist(dialogId);
			}
		}
	}

	@Override
	protected DataBean processResult(JSONObject result) throws JSONException {
		return null;
	}
}
