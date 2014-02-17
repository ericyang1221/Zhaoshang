package com.rugao.zhaoshang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

import com.rugao.zhaoshang.asynctask.LoginTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;
import com.rugao.zhaoshang.utils.Utils;

public class LoginActivity extends BaseActivity implements DataView {
	private boolean isRemember = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);

		final EditText name = (EditText) findViewById(R.id.login_name);
		final EditText pwd = (EditText) findViewById(R.id.login_pwd);
		CheckBox cb = (CheckBox) findViewById(R.id.remember_me);
		String un = Utils.getUsername(this);
		if (un == null) {
			cb.setChecked(false);
			isRemember = false;
		} else {
			cb.setChecked(true);
			name.setText(un);
			isRemember = true;
		}
		cb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				isRemember = isChecked;
			}
		});
		findViewById(R.id.login_login).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						String n = name.getText().toString();
						n = "yanghui";
						if (n.length() < 1) {
							Toast.makeText(
									LoginActivity.this,
									getString(R.string.username_cannot_be_empty),
									Toast.LENGTH_SHORT).show();
							return;
						}
						String p = pwd.getText().toString();
						p = "yanghui";
						if (p.length() < 1) {
							Toast.makeText(LoginActivity.this,
									getString(R.string.pwd_cannot_be_empty),
									Toast.LENGTH_SHORT).show();
							return;
						}
						new LoginTask(LoginActivity.this).execute(
								LoginActivity.this, URLGenerater.makeUrl(
										Constants.API_LOGIN, new String[] { n,
												p }));
						if (isRemember) {
							Utils.putUsername(LoginActivity.this, n);
						} else {
							Utils.putUsername(LoginActivity.this, null);
						}
					}
				});
	}

	@Override
	public void setData(DataBean db) {
		if (db != null) {
			UserBean ub = (UserBean) db;
			if (ub.getResult()) {
				getMyApplication().setUserBean(ub);
				Intent i = new Intent(LoginActivity.this, MainActivity.class);
				startActivity(i);
				finish();
			} else {
				this.showToast(db.getResultMsg());
			}
		} else {
			showDataBeanNullToast();
		}

	}
}
