package com.rugao.zhaoshang;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

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
	
	public void go(BaseFragment dest){
		FragmentTransaction t = getActivity()
				.getSupportFragmentManager().beginTransaction();
		if (dest.isAdded()) {
			t.show(dest);
		} else {
			t.replace(R.id.content, dest);
			t.addToBackStack(null);
			t.commit();
		}
	}
}
