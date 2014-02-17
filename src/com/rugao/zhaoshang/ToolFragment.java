package com.rugao.zhaoshang;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

public class ToolFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View toolLayout = inflater.inflate(R.layout.tool_layout,
				container, false);
		toolLayout.findViewById(R.id.tr).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent i = new Intent(getActivity(),LoginActivity.class);
						getActivity().startActivity(i);
						getActivity().finish();
					}
				});
		return toolLayout;
	}

}
