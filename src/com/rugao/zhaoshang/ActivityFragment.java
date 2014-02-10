package com.rugao.zhaoshang;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.rugao.zhaoshang.beans.ActivityItem;
import com.rugao.zhaoshang.calendar.CalendarView;
import com.rugao.zhaoshang.calendar.CalendarView.OnCalendarDateClickListener;

public class ActivityFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View activityLayout = inflater.inflate(R.layout.activity_layout,
				container, false);
		final ListView lv = (ListView) activityLayout.findViewById(R.id.cal_lv);
		final CalendarView cal = new CalendarView();
		FragmentTransaction transaction = getChildFragmentManager()
				.beginTransaction();
		transaction.add(R.id.cal_cal, cal).commit();

		cal.setOnCalendarDateClickListener(new OnCalendarDateClickListener() {
			@Override
			public void onCalendarDateClickListener(int year, int month, int day) {
				List<ActivityItem> aiList = cal.getAiList();
				List<ActivityItem> aiListOfCurrentMonth = new ArrayList<ActivityItem>();
				if (aiList != null) {
					for (ActivityItem ai : aiList) {
						if (ai.getYear() == year && ai.getMonth() == month + 1
								&& ai.getDay() == day) {
							aiListOfCurrentMonth.add(ai);
						}
					}
					lv.setAdapter(new ArrayAdapter<ActivityItem>(getActivity(),
							R.layout.activity_list_item, android.R.id.text1,
							aiListOfCurrentMonth));
				}
			}
		});
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				ActivityItem ai = (ActivityItem) lv.getAdapter().getItem(arg2);
				ActivityDetailFragment activityDetailFragment = new ActivityDetailFragment();
				activityDetailFragment.setActivityItem(ai);
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				transaction.add(R.id.content, activityDetailFragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		
		activityLayout.findViewById(R.id.tr).setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				ActivityAddFragment activityAddFragment = new ActivityAddFragment();
				activityAddFragment.setActivityItem(new ActivityItem());
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				transaction.add(R.id.content, activityAddFragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});
		return activityLayout;
	}
}
