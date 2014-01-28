package com.rugao.zhaoshang;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.rugao.zhaoshang.beans.Project;
import com.rugao.zhaoshang.beans.ValueBean;

public class CheckboxFragment extends BaseFragment {
	private LayoutInflater mInflater;
	private Project p;
	private int wsSize;
	private String[] ws;
	private List<String> checked;
	List<ValueBean> it;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mInflater = inflater;
		View dLayout = inflater.inflate(R.layout.checkbox_layout, container,
				false);
		ListView lv = (ListView) dLayout.findViewById(R.id.checkbox_listview);
		it = getMyApplication().getProjectPeople();
		ws = p.getWorkers().split(",");
		wsSize = ws.length;
		checked = new ArrayList<String>();
		for (String s : ws) {
			checked.add(s);
		}
		lv.setAdapter(new CheckboxAdapter(it));
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				ViewHolder holder = (ViewHolder) v.getTag();
				if (holder.cb.isChecked()) {
					holder.cb.setChecked(false);
					checked.remove(String.valueOf(it.get(arg2).getKey()));
				} else {
					holder.cb.setChecked(true);
					checked.add(String.valueOf(it.get(arg2).getKey()));
				}

			}

		});
		dLayout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
				StringBuffer sb = new StringBuffer();
				StringBuffer sbw = new StringBuffer();
				for (String s : checked) {
					sb.append(s).append(",");
					for (ValueBean vb : it) {
						if (String.valueOf(vb.getKey()).equals(s)) {
							sbw.append(vb.getValue()).append(",");
							break;
						}
					}
				}
				if (sb.length() > 0) {
					p.setWorkers(sb.substring(0, sb.length() - 1));
					if (sbw.length() > 0) {
						p.setWorkersDisplay(sbw.substring(0, sbw.length() - 1));
					}
				} else {
					p.setWorkers("");
					p.setWorkersDisplay("");
				}
			}
		});

		dLayout.findViewById(R.id.tl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
			}
		});
		return dLayout;
	}

	class CheckboxAdapter extends BaseAdapter {
		List<ValueBean> workers;

		public CheckboxAdapter(List<ValueBean> workers) {
			this.workers = workers;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return workers.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			ValueBean vb = workers.get(position);
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.checkbox_list_item,
						null);
				holder.tv = (TextView) convertView
						.findViewById(R.id.cb_item_tv);
				holder.cb = (CheckBox) convertView
						.findViewById(R.id.cb_item_cb);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.tv.setText(vb.getValue());
			for (int i = 0; i < wsSize; i++) {
				int checkedint = -1;
				try {
					checkedint = Integer.valueOf(ws[i]);
				} catch (Exception e) {
					e.printStackTrace();
				}
				if (vb.getKey() == checkedint) {
					holder.cb.setChecked(true);
					break;
				}
			}
			return convertView;
		}

	}

	class ViewHolder {
		public TextView tv;
		public CheckBox cb;
	}

	public void setProject(Project p) {
		this.p = p;
	}
}
