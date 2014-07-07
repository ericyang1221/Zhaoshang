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
	private ListView lv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View activityLayout = inflater.inflate(R.layout.activity_layout,
				container, false);
		lv = (ListView) activityLayout.findViewById(R.id.cal_lv);
		cal = new CalendarView();
		cal.setActivityFragment(this);
		FragmentTransaction transaction = getBaseActivity()
				.getTheFragmentManager().beginTransaction();
		transaction.add(R.id.cal_cal, cal).commit();

		cal.setOnCalendarDateClickListener(new OnCalendarDateClickListener() {
			@Override
			public void onCalendarDateClickListener(int year, int month, int day) {
				selectedDate = year + "-" + (month + 1) + "-" + day;
				System.out.println(selectedDate);
				updateSelectdateList(selectedDate);
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
				go(activityDetailFragment);
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
						go(activityAddFragment);
					}
				});

		return activityLayout;
	}

	public void updateSelectdateList(String selectedDate) {
		if (selectedDate != null && selectedDate.length() > 0) {
			String[] s = selectedDate.split("-");
			if (s != null && s.length == 3) {
				int year, month, day;
				try {
					year = Integer.valueOf(s[0]);
					month = Integer.valueOf(s[1]);
					day = Integer.valueOf(s[2]);
				} catch (Exception e) {
					return;
				}
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
									if (yy == year && mm == month && dd == day) {
										aiListOfCurrentMonth.add(ai);
									}
								} catch (Exception e) {
								}
							}
						}
					}
					ArrayAdapter<ActivityItem> adapter = new ArrayAdapter<ActivityItem>(
							getActivity(), R.layout.activity_list_item,
							android.R.id.text1, aiListOfCurrentMonth);
					lv.setAdapter(adapter);
				}
			}
		}
	}

	public String getSelectedDate() {
		return selectedDate;
	}
}
