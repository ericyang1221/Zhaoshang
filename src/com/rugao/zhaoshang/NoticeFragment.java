package com.rugao.zhaoshang;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rugao.zhaoshang.asynctask.NoticeTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.Notice;
import com.rugao.zhaoshang.beans.NoticeBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;

public class NoticeFragment extends BaseFragment implements DataView {
	private LayoutInflater mInflater;
	private ListView lv;
	private TextView tv;
	private NoticeListAdapter na;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.mInflater = inflater;
		View noticeLayout = inflater.inflate(R.layout.notice_layout, container,
				false);
		lv = (ListView) noticeLayout.findViewById(R.id.notice_listview);
		tv = (TextView) noticeLayout.findViewById(R.id.notice_msg);

		na = new NoticeListAdapter();
		lv.setDividerHeight(0);
		lv.setAdapter(na);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				NoticeDetailFragment noticeDetailFragment = new NoticeDetailFragment();
				Notice n = ((ViewHolder) v.getTag()).notice;
				noticeDetailFragment.setData(n);
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				transaction.replace(R.id.content, noticeDetailFragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		
		noticeLayout.findViewById(R.id.tr).setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				getMyApplication().setUserBean(null);
				Intent i = new Intent(getActivity(),LoginActivity.class);
				getActivity().startActivity(i);
				getActivity().finish();
			}
			
		});
		return noticeLayout;
	}

	@Override
	public void onResume() {
		super.onResume();
		MyApplication myApp = getMyApplication();
		UserBean ub = myApp.getUserBean();
		new NoticeTask(this.getActivity()).execute(
				this,
				URLGenerater.makeUrl(Constants.NOTICE_GET, new String[] {
						String.valueOf(ub.getUserId()), ub.getMemo() }));
	}

	class NoticeListAdapter extends BaseAdapter {
		private List<Notice> nList;

		public void update(List<Notice> nList) {
			this.nList = nList;
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return nList == null ? 0 : nList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			Notice n = nList.get(position);
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater
						.inflate(R.layout.notice_list_item, null);
				holder.content = (TextView) convertView
						.findViewById(R.id.content);
				holder.time = (TextView) convertView.findViewById(R.id.time);
				holder.notice = n;
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.content.setText(n.getNoticeIdDisplay());
			holder.time.setText(n.getCreateDate());
			return convertView;
		}

	}

	class ViewHolder {
		public TextView content;
		public TextView time;
		public Notice notice;
	}

	@Override
	public void setData(DataBean db) {
		if (db != null) {
			NoticeBean nb = (NoticeBean) db;
			if (nb.getResult()) {
				na.update(nb.getNoticeList());
				lv.setVisibility(View.VISIBLE);
				tv.setVisibility(View.GONE);
			} else {
				lv.setVisibility(View.GONE);
				tv.setVisibility(View.VISIBLE);
				this.getBaseActivity().showToast(nb.getResultMsg());
			}
		} else {
			getBaseActivity().showDataBeanNullToast();
		}
	}
}
