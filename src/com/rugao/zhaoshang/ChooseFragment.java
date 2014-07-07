package com.rugao.zhaoshang;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rugao.zhaoshang.beans.ValueBean;

public class ChooseFragment extends BaseFragment {
	private List<ValueBean> data;
	private OnFragmentItemClickListener onFragmentItemClickListener;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View chooseLayout = inflater.inflate(R.layout.choose_layout, container,
				false);
		ListView lv = (ListView) chooseLayout
				.findViewById(R.id.choose_listview);
		lv.setAdapter(new ArrayAdapter<ValueBean>(this.getActivity(),
				R.layout.ax, android.R.id.text1, data));
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (onFragmentItemClickListener != null) {
					onFragmentItemClickListener.onItemClick(arg2);
				}
				goBack();
			}
		});
		chooseLayout.findViewById(R.id.tl).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						goBack();
					}
				});
		return chooseLayout;
	}

	public void setData(List<ValueBean> data) {
		this.data = data;
	}

	interface OnFragmentItemClickListener {
		void onItemClick(int position);
	}

	public void setOnFragmentItemClickListener(
			OnFragmentItemClickListener onFragmentItemClickListener) {
		this.onFragmentItemClickListener = onFragmentItemClickListener;
	}
}
