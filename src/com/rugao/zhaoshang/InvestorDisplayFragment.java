package com.rugao.zhaoshang;

import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.rugao.zhaoshang.beans.Investor;
import com.rugao.zhaoshang.beans.Project;

public class InvestorDisplayFragment extends BaseFragment {
	private LayoutInflater mInflater;
	private InvestorDetailFragment df;
	private Project p;
	private InvestorListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mInflater = inflater;
		final View dLayout = inflater.inflate(R.layout.investor_display_layout,
				container, false);
		ListView lv = (ListView) dLayout.findViewById(R.id.display_listview);
		adapter = new InvestorListAdapter(p.getInvestors());
		lv.setAdapter(adapter);
		dLayout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (df == null) {
					df = new InvestorDetailFragment();
				}
				df.setProject(p);
				df.setIndex(-1);
				go(df);
			}
		});
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0,
					final View arg1, final int arg2, long arg3) {
				AlertDialog.Builder builder = new Builder(dLayout.getContext());
				builder.setMessage(getString(R.string.are_you_sure_delete));
				builder.setTitle(getString(R.string.info));
				builder.setPositiveButton(getString(R.string.confirm),
						new Dialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								ViewHolder holder = (ViewHolder) arg1.getTag();
								p.getInvestors().remove(holder.investor);
								reflash();
								dialog.dismiss();
							}
						});
				builder.setNegativeButton(getString(R.string.cancel),
						new Dialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});
				builder.create().show();
				return false;
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				if (df == null) {
					df = new InvestorDetailFragment();
				}
				df.setProject(p);
				df.setIndex(arg2);
				go(df);
			}
		});
		dLayout.findViewById(R.id.tl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goBack();
			}
		});
		return dLayout;
	}

	@Override
	public void onResume() {
		super.onResume();
		reflash();
	}

	private void reflash() {
		adapter.setData(p.getInvestors());
		adapter.notifyDataSetChanged();
	}

	public void setProject(Project p) {
		this.p = p;
	}

	class InvestorListAdapter extends BaseAdapter {
		List<Investor> data;

		public void setData(List<Investor> data) {
			this.data = data;
		}

		public InvestorListAdapter(List<Investor> data) {
			this.data = data;
		}

		@Override
		public int getCount() {
			if (data != null) {
				return data.size();
			} else {
				return 0;
			}
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
			Investor i = data.get(position);
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.investor_list_item,
						null);
				holder.name = (TextView) convertView.findViewById(R.id.ii_name);
				holder.address = (TextView) convertView
						.findViewById(R.id.ii_address);
				holder.investor = i;
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(i.getInvestorName());
			holder.address.setText(i.getAddress());
			return convertView;
		}

	}

	class ViewHolder {
		public TextView name;
		public TextView address;
		public Investor investor;
	}
}
