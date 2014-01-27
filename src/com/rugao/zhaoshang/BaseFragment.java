package com.rugao.zhaoshang;

import android.support.v4.app.Fragment;

public class BaseFragment extends Fragment {
	private MyApplication myApp;

	public MyApplication getMyApplication() {
		if (myApp == null) {
			myApp = ((BaseActivity) this.getActivity()).getMyApplication();
		}
		return myApp;
	}
	
	public BaseActivity getBaseActivity(){
		return (BaseActivity)this.getActivity();
	}
}
