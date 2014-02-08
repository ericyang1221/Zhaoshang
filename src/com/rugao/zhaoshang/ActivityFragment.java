package com.rugao.zhaoshang;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.rugao.zhaoshang.calendar.CalendarView;
import com.rugao.zhaoshang.calendar.CalendarView.OnCalendarDateClickListener;

public class ActivityFragment extends BaseFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View activityLayout = inflater.inflate(R.layout.activity_layout,
				container, false);
		CalendarView cal = (CalendarView) this.getActivity()
				.getSupportFragmentManager().findFragmentById(R.id.cal_cal);
		cal.setOnCalendarDateClickListener(new OnCalendarDateClickListener() {
			@Override
			public void onCalendarDateClickListener(long timeInMillions) {
				System.out.println("hello world");
			}
		});
		ListView lv = (ListView) activityLayout.findViewById(R.id.cal_lv);

		return activityLayout;
	}

}
