package com.rugao.zhaoshang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.rugao.zhaoshang.beans.Notice;

public class NoticeDetailFragment extends BaseFragment {
	private TextView content;
	private Notice n;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View layout = inflater.inflate(R.layout.notice_detail_layout,
				container, false);
		content = (TextView) layout.findViewById(R.id.notice_content);
		layout.findViewById(R.id.tl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
			}
		});
		return layout;
	}

	@Override
	public void onResume() {
		super.onResume();
		content.setText(n.getNoticeIdDisplay());
	}

	public void setData(Notice n) {
		this.n = n;
	}
}
