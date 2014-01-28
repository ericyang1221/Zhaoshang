package com.rugao.zhaoshang;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

public class BaseActivity extends FragmentActivity {
	public static final int LOGIN_DIALOG = 1;
	public static final int GETPROJECT_DIALOG = 2;
	private MyApplication myApp;

	public void showDataBeanNullToast() {
		showToast(R.string.network_error);
	}

	public void showToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
	}

	public void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	public MyApplication getMyApplication() {
		if (myApp == null) {
			myApp = (MyApplication) this.getApplication();
		}
		return myApp;
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id, Bundle args) {
		switch (id) {
		case LOGIN_DIALOG:
			ProgressDialog pd = new ProgressDialog(this);
			pd.setMessage(getString(R.string.login_loading));
			pd.setCancelable(false);
			return pd;
		case GETPROJECT_DIALOG:
			pd = new ProgressDialog(this);
			pd.setMessage(getString(R.string.loading));
			pd.setCancelable(false);
			return pd;
		default:
			break;
		}
		return super.onCreateDialog(id, args);
	}

	@SuppressWarnings("deprecation")
	public void dismissDialogIfExist(int dialogId) {
		try {
			this.dismissDialog(dialogId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
