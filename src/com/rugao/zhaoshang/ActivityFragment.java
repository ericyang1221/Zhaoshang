package com.rugao.zhaoshang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ActivityFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View activityLayout = inflater.inflate(R.layout.activity_layout, container,
				false);
		return activityLayout;
	}

}
