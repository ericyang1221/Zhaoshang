package com.rugao.zhaoshang.calendar;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.rugao.zhaoshang.BaseActivity;
import com.rugao.zhaoshang.R;
import com.rugao.zhaoshang.beans.ActivityBean;
import com.rugao.zhaoshang.beans.ActivityItem;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;

public class CalendarView extends Fragment {
	protected final Calendar calendar;
	private final Locale locale;
	private ViewSwitcher calendarSwitcher;
	private TextView currentMonth;
	private CalendarAdapter calendarAdapter;
	private OnCalendarDateClickListener onCalendarDateClickListener;
	private List<ActivityItem> aiList;

	public CalendarView() {
		calendar = Calendar.getInstance();
		locale = Locale.getDefault();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final RelativeLayout calendarLayout = (RelativeLayout) inflater
				.inflate(R.layout.calendar, null);
		final GridView calendarDayGrid = (GridView) calendarLayout
				.findViewById(R.id.calendar_days_grid);
		final GestureDetector swipeDetector = new GestureDetector(
				getActivity(), new SwipeGesture(getActivity()));
		final GridView calendarGrid = (GridView) calendarLayout
				.findViewById(R.id.calendar_grid);
		calendarSwitcher = (ViewSwitcher) calendarLayout
				.findViewById(R.id.calendar_switcher);
		currentMonth = (TextView) calendarLayout
				.findViewById(R.id.current_month);
		calendarAdapter = new CalendarAdapter(getActivity(), calendar);
		updateCurrentMonth();

		final TextView nextMonth = (TextView) calendarLayout
				.findViewById(R.id.next_month);
		nextMonth.setOnClickListener(new NextMonthClickListener());
		final TextView prevMonth = (TextView) calendarLayout
				.findViewById(R.id.previous_month);
		prevMonth.setOnClickListener(new PreviousMonthClickListener());
		calendarGrid.setOnItemClickListener(new DayItemClickListener());

		calendarGrid.setAdapter(calendarAdapter);
		calendarGrid.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				return swipeDetector.onTouchEvent(event);
			}
		});
		calendarDayGrid.setAdapter(new ArrayAdapter<String>(getActivity(),
				R.layout.day_item, getResources().getStringArray(
						R.array.days_array)));
		return calendarLayout;
	}

	@SuppressWarnings("deprecation")
	public void updateCurrentMonth() {
		final BaseActivity ba = ((BaseActivity) getActivity());
		ba.showDialog(BaseActivity.LOADING_DIALOG);
		new Thread(new Runnable() {
			@Override
			public void run() {
				UserBean ub = ba.getMyApplication().getUserBean();
				String url = URLGenerater
						.makeUrl(
								Constants.ACTIVITY_GET,
								new String[] {
										String.valueOf(ub.getUserId()),
										ub.getMemo(),
										String.valueOf(calendar
												.get(Calendar.YEAR)),
										String.valueOf(calendar
												.get(Calendar.MONTH) + 1) });
				try {
					JSONObject jo = ba.getMyApplication()
							.getHttpRequestHelper()
							.sendRequestAndReturnJson(url);
					ActivityBean ab = new ActivityBean(jo);
					aiList = ab.getAiList();
				} catch (JSONException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				ba.runOnUiThread(new Runnable() {
					@Override
					public void run() {
						calendarAdapter.refreshDays(aiList);
					}
				});
				ba.dismissDialogIfExist(BaseActivity.LOADING_DIALOG);
			}
		}).start();
		currentMonth.setText(String.format(locale, "%tB", calendar) + " "
				+ calendar.get(Calendar.YEAR));
	}

	private final class DayItemClickListener implements OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			final TextView dayView = (TextView) view.findViewById(R.id.date);
			final CharSequence text = dayView.getText();
			if (text != null && !"".equals(text)) {
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int day = Integer.valueOf(String.valueOf(text));
				calendarAdapter.setSelected(year, month, day);
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.YEAR, year);
				cal.set(Calendar.MONTH, month);
				cal.set(Calendar.DAY_OF_MONTH, day);
				if (onCalendarDateClickListener != null) {
					onCalendarDateClickListener.onCalendarDateClickListener(
							year, month, day);
				}
			}
		}
	}

	protected final void onNextMonth() {
		calendarSwitcher.setInAnimation(getActivity(), R.anim.in_from_right);
		calendarSwitcher.setOutAnimation(getActivity(), R.anim.out_to_left);
		calendarSwitcher.showNext();
		if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER) {
			calendar.set((calendar.get(Calendar.YEAR) + 1), Calendar.JANUARY, 1);
		} else {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		}
		updateCurrentMonth();
	}

	protected final void onPreviousMonth() {
		calendarSwitcher.setInAnimation(getActivity(), R.anim.in_from_left);
		calendarSwitcher.setOutAnimation(getActivity(), R.anim.out_to_right);
		calendarSwitcher.showPrevious();
		if (calendar.get(Calendar.MONTH) == Calendar.JANUARY) {
			calendar.set((calendar.get(Calendar.YEAR) - 1), Calendar.DECEMBER,
					1);
		} else {
			calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1);
		}
		updateCurrentMonth();
	}

	private final class NextMonthClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			onNextMonth();
		}
	}

	private final class PreviousMonthClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			onPreviousMonth();
		}
	}

	private final class SwipeGesture extends SimpleOnGestureListener {
		private final int swipeMinDistance;
		private final int swipeThresholdVelocity;

		public SwipeGesture(Context context) {
			final ViewConfiguration viewConfig = ViewConfiguration.get(context);
			swipeMinDistance = viewConfig.getScaledTouchSlop();
			swipeThresholdVelocity = viewConfig.getScaledMinimumFlingVelocity();
		}

		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			if (e1.getX() - e2.getX() > swipeMinDistance
					&& Math.abs(velocityX) > swipeThresholdVelocity) {
				onNextMonth();
			} else if (e2.getX() - e1.getX() > swipeMinDistance
					&& Math.abs(velocityX) > swipeThresholdVelocity) {
				onPreviousMonth();
			}
			return false;
		}
	}

	public interface OnCalendarDateClickListener {
		void onCalendarDateClickListener(int year, int month, int day);
	}

	public void setOnCalendarDateClickListener(
			OnCalendarDateClickListener onCalendarDateClickListener) {
		this.onCalendarDateClickListener = onCalendarDateClickListener;
	}

	public List<ActivityItem> getAiList() {
		return aiList;
	}
}
