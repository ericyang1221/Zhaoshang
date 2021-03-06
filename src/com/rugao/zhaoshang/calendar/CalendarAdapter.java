package com.rugao.zhaoshang.calendar;

import java.util.Calendar;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.rugao.zhaoshang.R;
import com.rugao.zhaoshang.beans.ActivityItem;

public class CalendarAdapter extends BaseAdapter {
	private static final int FIRST_DAY_OF_WEEK = Calendar.MONDAY;
	private final Calendar calendar;
	private final CalendarItem today;
	private final CalendarItem selected;
	private final LayoutInflater inflater;
	private CalendarItem[] days;
	private static List<ActivityItem> aiList;

	public CalendarAdapter(Context context, Calendar monthCalendar) {
		calendar = monthCalendar;
		today = new CalendarItem(monthCalendar);
		selected = new CalendarItem(monthCalendar);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return days == null ? 0 : days.length;
	}

	@Override
	public Object getItem(int position) {
		return days[position];
	}

	@Override
	public long getItemId(int position) {
		final CalendarItem item = days[position];
		if (item != null) {
			return days[position].id;
		}
		return -1;
	}

	@SuppressWarnings("deprecation")
	@Override
	public View getView(int position, View view, ViewGroup parent) {
		if (view == null) {
			view = inflater.inflate(R.layout.calendar_item, null);
		}
		final TextView dayView = (TextView) view.findViewById(R.id.date);
		final View dot = view.findViewById(R.id.dot);
		final CalendarItem currentItem = days[position];

		if (currentItem == null) {
			dayView.setClickable(false);
			dayView.setFocusable(false);
			view.setBackgroundDrawable(null);
			dayView.setText(null);
		} else {
			if (currentItem.equals(today)) {
				view.setBackgroundResource(R.drawable.today_background);
				dayView.setTextColor(Color.WHITE);
				dot.setBackgroundColor(Color.WHITE);
			} else if (currentItem.equals(selected)) {
				view.setBackgroundResource(R.drawable.selected_background);
				dayView.setTextColor(Color.WHITE);
				dot.setBackgroundColor(Color.WHITE);
			} else {
				view.setBackgroundResource(R.drawable.normal_background);
				dayView.setTextColor(Color.BLACK);
				dot.setBackgroundColor(Color.BLACK);
			}
			if (currentItem.monthType != 0) {
				view.setEnabled(false);
				dayView.setTextColor(Color.GRAY);
				dot.setBackgroundColor(Color.GRAY);
			} else {
				view.setEnabled(true);
			}
			dayView.setText(currentItem.text);
			if (currentItem.hasActivity) {
				dot.setVisibility(View.VISIBLE);
			} else {
				dot.setVisibility(View.INVISIBLE);
			}
		}
		return view;
	}

	public final void setSelected(int year, int month, int day) {
		selected.year = year;
		selected.month = month;
		selected.day = day;
		notifyDataSetChanged();
	}

	public final void refreshDays(List<ActivityItem> aiList) {
		CalendarAdapter.aiList = aiList;
		final int year = calendar.get(Calendar.YEAR);
		final int month = calendar.get(Calendar.MONTH);
		final int firstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
		final int lastDayOfMonth = calendar
				.getActualMaximum(Calendar.DAY_OF_MONTH);

		final int blankies;
		final CalendarItem[] days;
		if (firstDayOfMonth == FIRST_DAY_OF_WEEK) {
			blankies = 0;
		} else if (firstDayOfMonth < FIRST_DAY_OF_WEEK) {
			blankies = Calendar.SATURDAY - (FIRST_DAY_OF_WEEK - 1);
		} else {
			blankies = firstDayOfMonth - FIRST_DAY_OF_WEEK;
		}

		Calendar c = (Calendar) calendar.clone();
		c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));
		int maxDayInWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		c.add(Calendar.MONDAY, -1);
		int lastY = c.get(Calendar.YEAR);
		int lastM = c.get(Calendar.MONTH);
		int lastMonthMaxDay = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.add(Calendar.MONDAY, 2);
		int nextY = c.get(Calendar.YEAR);
		int nextM = c.get(Calendar.MONTH);

		int acturalDay = lastDayOfMonth + blankies;
		days = new CalendarItem[acturalDay + 7 - maxDayInWeek];

		for (int day = lastMonthMaxDay, position = blankies; position >= 0; position--) {
			days[position] = new CalendarItem(lastY, lastM, day--, -1);
		}
		for (int day = 1, position = blankies; position < acturalDay; position++) {
			days[position] = new CalendarItem(year, month, day++, 0);
		}
		for (int day = 1, position = acturalDay; position < days.length; position++) {
			days[position] = new CalendarItem(nextY, nextM, day++, 1);
		}

		CalendarAdapter.this.days = days;
		notifyDataSetChanged();
	}

	private static class CalendarItem {
		public int year;
		public int month;
		public int day;
		public String text;
		public long id;
		public boolean hasActivity = false;
		public int monthType;

		public CalendarItem(Calendar calendar) {
			this(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
					calendar.get(Calendar.DAY_OF_MONTH), 0);
		}

		public CalendarItem(int year, int month, int day, int monthType) {
			this.year = year;
			this.month = month;
			this.day = day;
			this.monthType = monthType;
			this.text = String.valueOf(day);
			this.id = Long.valueOf(year + "" + month + "" + day);
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
								if (yy == year && mm == month + 1 && dd == day) {
									hasActivity = true;
									break;
								}
							} catch (Exception e) {
							}
						}
					}
				}
			}
		}

		@Override
		public boolean equals(Object o) {
			if (o != null && o instanceof CalendarItem) {
				final CalendarItem item = (CalendarItem) o;
				return item.year == year && item.month == month
						&& item.day == day;
			}
			return false;
		}
	}
}