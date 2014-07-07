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

import com.rugao.zhaoshang.beans.Contact;
import com.rugao.zhaoshang.beans.Project;

public class ContactDisplayFragment extends BaseFragment {
	private LayoutInflater mInflater;
	private ContactDetailFragment cdf;
	private Project p;
	private ContactListAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		mInflater = inflater;
		final View dLayout = inflater.inflate(R.layout.contact_display_layout,
				container, false);
		ListView lv = (ListView) dLayout.findViewById(R.id.display_listview);
		adapter = new ContactListAdapter(p.getContacts());
		lv.setAdapter(adapter);
		dLayout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cdf == null) {
					cdf = new ContactDetailFragment();
				}
				cdf.setProject(p);
				cdf.setIndex(-1);
				go(cdf);
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
								p.getContacts().remove(holder.contact);
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
				if (cdf == null) {
					cdf = new ContactDetailFragment();
				}
				cdf.setProject(p);
				cdf.setIndex(arg2);
				go(cdf);
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

	@Override
	public void onResume() {
		super.onResume();
		reflash();
	}

	private void reflash() {
		adapter.setData(p.getContacts());
		adapter.notifyDataSetChanged();
	}

	public void setProject(Project p) {
		this.p = p;
	}

	class ContactListAdapter extends BaseAdapter {
		List<Contact> data;

		public void setData(List<Contact> data) {
			this.data = data;
		}

		public ContactListAdapter(List<Contact> data) {
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
			Contact c = data.get(position);
			ViewHolder holder = null;
			if (convertView == null) {
				holder = new ViewHolder();
				convertView = mInflater.inflate(R.layout.investor_list_item,
						null);
				holder.name = (TextView) convertView.findViewById(R.id.ii_name);
				holder.role = (TextView) convertView
						.findViewById(R.id.ii_address);
				holder.contact = c;
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			holder.name.setText(c.getName());
			holder.role.setText(c.getRole());
			return convertView;
		}

	}

	class ViewHolder {
		public TextView name;
		public TextView role;
		public Contact contact;
	}
}
