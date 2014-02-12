package com.rugao.zhaoshang;

import java.util.ArrayList;
import java.util.Calendar;
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
	private String selectedDate;
	private CalendarView cal;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View activityLayout = inflater.inflate(R.layout.activity_layout,
				container, false);
		final ListView lv = (ListView) activityLayout.findViewById(R.id.cal_lv);
		cal = new CalendarView();
		FragmentTransaction transaction = getChildFragmentManager()
				.beginTransaction();
		transaction.add(R.id.cal_cal, cal).commit();

		cal.setOnCalendarDateClickListener(new OnCalendarDateClickListener() {
			@Override
			public void onCalendarDateClickListener(int year, int month, int day) {
				selectedDate = year + "-" + (month + 1) + "-" + day;
				System.out.println(selectedDate);
				int tureMonth = month + 1;
				List<ActivityItem> aiList = cal.getAiList();
				List<ActivityItem> aiListOfCurrentMonth = new ArrayList<ActivityItem>();
				if (aiList != null) {
					for (ActivityItem ai : aiList) {
						String date = ai.getDate();
						if (date != null && date.length() > 0) {
							String[] d = date.split("-");
							if (d.length == 3) {
								try {
									int yy = Integer.valueOf(d[0]);
									int mm = Integer.valueOf(d[1]);
									int dd = Integer.valueOf(d[2]);
									if (yy == year && mm == tureMonth
											&& dd == day) {
										aiListOfCurrentMonth.add(ai);
									}
								} catch (Exception e) {
								}
							}
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
				activityDetailFragment.setSelectedDate(selectedDate);
				FragmentTransaction transaction = getFragmentManager()
						.beginTransaction();
				transaction.add(R.id.content, activityDetailFragment);
				transaction.addToBackStack(null);
				transaction.commit();
			}
		});

		Calendar calendar = Calendar.getInstance();
		selectedDate = calendar.get(Calendar.YEAR) + "-"
				+ (calendar.get(Calendar.MONTH) + 1) + "-"
				+ calendar.get(Calendar.DAY_OF_MONTH);
		activityLayout.findViewById(R.id.tr).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						ActivityAddFragment activityAddFragment = new ActivityAddFragment();
						activityAddFragment.setActivityItem(new ActivityItem());
						activityAddFragment.setSelectedDate(selectedDate);
						activityAddFragment.setCalendarView(cal);
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
