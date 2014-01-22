package com.rugao.zhaoshang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProjectFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View projectLayout = inflater.inflate(R.layout.contacts_layout,
				container, false);
		return projectLayout;
	}

}
