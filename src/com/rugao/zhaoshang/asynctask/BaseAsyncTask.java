package com.rugao.zhaoshang.asynctask;

import java.io.IOException;
import java.util.List;

import org.apache.http.NameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.rugao.zhaoshang.BaseActivity;
import com.rugao.zhaoshang.DataView;
import com.rugao.zhaoshang.MyApplication;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.net.HttpRequestHelper;

public abstract class BaseAsyncTask extends
		AsyncTask<Object, Integer, JSONObject> {
	protected Context context;
	protected DataView dv;
	protected boolean isPost = false;

	public BaseAsyncTask(Context context) {
		this.context = context;
		setPost();
	}

	@Override
	protected JSONObject doInBackground(Object... params) {
		dv = (DataView) params[0];
		String url = (String) params[1];
		HttpRequestHelper hrh = ((MyApplication) ((Activity) context)
				.getApplication()).getHttpRequestHelper();
		JSONObject json = null;
		try {
			if (isPost) {
				@SuppressWarnings("unchecked")
				List<NameValuePair> p = (List<NameValuePair>) params[2];
				json = hrh.sendPostRequestAndReturnJson(url, p);
			} else {
				json = hrh.sendRequestAndReturnJson(url);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		Integer dialogId = getDialogId();
		if (dialogId != null) {
			BaseActivity ba = getBaseActivity();
			if (ba != null) {
				ba.showDialog(dialogId);
			}
		}
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		super.onPostExecute(result);
		DataBean db = null;
		try {
			db = processResult(result);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dv.setData(db);
		Integer dialogId = getDialogId();
		if (dialogId != null) {
			BaseActivity ba = getBaseActivity();
			if (ba != null) {
				ba.dismissDialogIfExist(dialogId);
			}
		}
	}

	protected abstract DataBean processResult(JSONObject result)
			throws JSONException;

	protected BaseActivity getBaseActivity() {
		if (context instanceof BaseActivity) {
			return (BaseActivity) context;
		} else {
			return null;
		}
	}

	protected abstract Integer getDialogId();

	protected void setPost() {
		isPost = false;
	}
}
