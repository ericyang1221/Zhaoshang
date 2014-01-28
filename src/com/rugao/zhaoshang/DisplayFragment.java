package com.rugao.zhaoshang;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rugao.zhaoshang.beans.Project;
import com.rugao.zhaoshang.beans.ValueBean;

public class DisplayFragment extends BaseFragment {
	private List<String> data = new ArrayList<String>();
	private CheckboxFragment cf;
	private Project p;
	private ArrayAdapter<String> adapter;
	private List<String> wsdList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View dLayout = inflater.inflate(R.layout.display_layout,
				container, false);
		ListView lv = (ListView) dLayout.findViewById(R.id.display_listview);
		adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.ax,
				android.R.id.text1, data);
		lv.setAdapter(adapter);
		dLayout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (cf == null) {
					cf = new CheckboxFragment();
				}
				cf.setProject(p);
				t.replace(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		final List<ValueBean> it = getMyApplication().getProjectPeople();
		lv.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
					final int arg2, long arg3) {
				AlertDialog.Builder builder = new Builder(dLayout.getContext());
				builder.setMessage(getString(R.string.are_you_sure_delete));
				builder.setTitle(getString(R.string.info));
				builder.setPositiveButton(getString(R.string.confirm),
						new Dialog.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog,
									int which) {
								wsdList.remove(arg2);
								StringBuffer sbd = new StringBuffer();
								StringBuffer sb = new StringBuffer();
								for (String s : wsdList) {
									sbd.append(s).append(",");
									for (ValueBean vb : it) {
										if (vb.getValue().equals(s)) {
											sb.append(vb.getKey()).append(",");
											break;
										}
									}
								}
								if (sb.length() > 0) {
									p.setWorkers(sb.substring(0,
											sb.length() - 1));
									if (sbd.length() > 0) {
										p.setWorkersDisplay(sbd.substring(0,
												sbd.length() - 1));
									}
								} else {
									p.setWorkers("");
									p.setWorkersDisplay("");
								}
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
		String[] wsd = p.getWorkersDisplay().split(",");
		if (wsdList == null) {
			wsdList = new ArrayList<String>();
		} else {
			wsdList.clear();
		}
		adapter.clear();
		for (String s : wsd) {
			adapter.add(s);
			wsdList.add(s);
		}
		adapter.notifyDataSetChanged();
	}

	public void setProject(Project p) {
		this.p = p;
	}
}
