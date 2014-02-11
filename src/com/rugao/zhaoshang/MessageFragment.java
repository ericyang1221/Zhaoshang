package com.rugao.zhaoshang;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rugao.zhaoshang.asynctask.MessageTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.Message;
import com.rugao.zhaoshang.beans.MessageBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;

public class MessageFragment extends BaseFragment implements DataView {
	private LayoutInflater mInflater;
	private ListView lv;
	private TextView tv;
	private MessageListAdapter ma;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		this.mInflater = inflater;
		View messageLayout = inflater.inflate(R.layout.message_layout,
				container, false);
		lv = (ListView) messageLayout.findViewById(R.id.message_listview);
		tv = (TextView) messageLayout.findViewById(R.id.message_msg);

		ma = new MessageListAdapter();
		lv.setDividerHeight(0);
		lv.setAdapter(ma);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				MessageListFragment messageListFragment = new MessageListFragment();
				ViewHolder holder = (ViewHolder)v.getTag();
				int activityId = holder.message.getActivityId();
				messageListFragment.setActivityId(activityId);
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				transaction.replace(R.id.content, messageListFragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		return messageLayout;
	}

	@Override
	public void onResume() {
		super.onResume();
		MyApplication myApp = getMyApplication();
		UserBean ub = myApp.getUserBean();
		new MessageTask(this.getActivity()).execute(
				this,
				URLGenerater.makeUrl(Constants.MESSAGE_GET, new String[] {
						String.valueOf(ub.getUserId()), ub.getMemo() }));
	}

	class MessageListAdapter extends BaseAdapter {
		private List<Message> mList;

		public void update(List<Message> mList) {
			this.mList = mList;
			this.notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mList == null ? 0 : mList.size();
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
			Message m = mList.get(position);
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.message_list_item,
						null);
				holder.title = (TextView) convertView.findViewById(R.id.m_ps);
				holder.subTitle = (TextView) convertView
						.findViewById(R.id.m_pn);
				holder.message = m;
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.title.setText(m.getActivityIdDisplay() + "  ["
					+ m.getCount() + "]");
			holder.subTitle.setText(m.getProjectIdDisplay());
			return convertView;
		}

	}

	class ViewHolder {
		public TextView title;
		public TextView subTitle;
		public Message message;
	}

	@Override
	public void setData(DataBean db) {
		if (db != null) {
			MessageBean mb = (MessageBean) db;
			if (mb.getResult()) {
				ma.update(mb.getMessages());
				lv.setVisibility(View.VISIBLE);
				tv.setVisibility(View.GONE);
			} else {
				lv.setVisibility(View.GONE);
				tv.setVisibility(View.VISIBLE);
				this.getBaseActivity().showToast(mb.getResultMsg());
			}
		} else {
			getBaseActivity().showDataBeanNullToast();
		}
	}
}
