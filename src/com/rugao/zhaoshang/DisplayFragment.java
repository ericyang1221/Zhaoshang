package com.rugao.zhaoshang;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rugao.zhaoshang.beans.ProjectBean.Project;

public class DisplayFragment extends BaseFragment {
	private List<String> data = new ArrayList<String>();
	private CheckboxFragment cf;
	private Project p;
	private ArrayAdapter<String> adapter ;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View dLayout = inflater.inflate(R.layout.display_layout, container,
				false);
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
		return dLayout;
	}

	@Override
	public void onResume() {
		super.onResume();
		String[] wsd = p.getWorkersDisplay().split(",");
		adapter.clear();
		for(String s:wsd){
			adapter.add(s);
		}
		adapter.notifyDataSetChanged();
	}

	public void setProject(Project p) {
		this.p = p;
	}
}
