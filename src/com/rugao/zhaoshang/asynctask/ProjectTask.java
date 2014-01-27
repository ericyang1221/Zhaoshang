package com.rugao.zhaoshang.asynctask;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.rugao.zhaoshang.BaseActivity;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.ProjectBean;

public class ProjectTask extends BaseAsyncTask {

	public ProjectTask(Context context) {
		super(context);
	}

	@Override
	protected DataBean processResult(JSONObject result) throws JSONException {
		return new ProjectBean(result);
	}

	@Override
	protected Integer getDialogId() {
		return BaseActivity.GETPROJECT_DIALOG;
	}

}
