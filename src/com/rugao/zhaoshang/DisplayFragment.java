package com.rugao.zhaoshang;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rugao.zhaoshang.CheckboxFragment.OnConfirmClickListener;
import com.rugao.zhaoshang.beans.ValueBean;

public class DisplayFragment extends BaseFragment {
	private final String TAG = "DisplayFragment";
	public static int ACTIVITY_LEADERS = 1;
	public static int PROJECT_WORKERS = 2;
	private List<String> data = new ArrayList<String>();
	private CheckboxFragment cf;
	// private Project p;
	private String[] ws;
	private String[] wsd;
	private ArrayAdapter<String> adapter;
	private List<String> wsdList;
	private DisplayFragmentListener displayFragmentListener;
	private boolean isEditable = true;
	private int type;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		final View dLayout = inflater.inflate(R.layout.display_layout,
				container, false);
		ListView lv = (ListView) dLayout.findViewById(R.id.display_listview);
		adapter = new ArrayAdapter<String>(this.getActivity(), R.layout.ax,
				android.R.id.text1, data);
		lv.setAdapter(adapter);
		final List<ValueBean> allList ;
		if(type == PROJECT_WORKERS){
			allList = getMyApplication().getProjectWorker();
		}else{
			allList = getMyApplication().getActivityLeader();
		}
		dLayout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (isEditable) {
					FragmentTransaction t = getActivity()
							.getSupportFragmentManager().beginTransaction();
					if (cf == null) {
						cf = new CheckboxFragment();
					}
					// cf.setProject(p);
					cf.setData(ws, allList);
					cf.setOnConfirmClickListener(new OnConfirmClickListener() {
						@Override
						public void onConfirmClick(List<String> checked,
								List<ValueBean> all) {
							if (displayFragmentListener != null) {
								displayFragmentListener.onChooseConfirm(
										checked, all);
								reflash();
							}
						}
					});
					if (cf.isAdded()) {
						t.show(cf);
					} else {
						t.add(R.id.content, cf);
						t.addToBackStack(null);
						t.commit();
					}
				}
			}
		});
		if (isEditable) {
			lv.setOnItemLongClickListener(new OnItemLongClickListener() {
				@Override
				public boolean onItemLongClick(AdapterView<?> arg0, View arg1,
						final int arg2, long arg3) {
					AlertDialog.Builder builder = new Builder(dLayout
							.getContext());
					builder.setMessage(getString(R.string.are_you_sure_delete));
					builder.setTitle(getString(R.string.info));
					builder.setPositiveButton(getString(R.string.confirm),
							new Dialog.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog,
										int which) {
									if (displayFragmentListener != null) {
										displayFragmentListener.onItemDelete(
												arg2, wsdList, allList);
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
		}
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

	public void setData(String[] wsd, String[] ws,int type) {
		Log.d(TAG, "worksDisplay: " + wsd + "works: " + ws);
		this.wsd = wsd;
		this.ws = ws;
		this.type = type;
	}

	public DisplayFragmentListener getDisplayFragmentListener() {
		return displayFragmentListener;
	}

	public void setDisplayFragmentListener(
			DisplayFragmentListener displayFragmentListener) {
		this.displayFragmentListener = displayFragmentListener;
	}

	interface DisplayFragmentListener {
		public void onChooseConfirm(List<String> checked, List<ValueBean> all);

		public void onItemDelete(int position, List<String> wsdList,
				List<ValueBean> allList);
	}

	public void isEditable(boolean isEditable) {
		this.isEditable = isEditable;
	}
}
