package com.rugao.zhaoshang;

import android.widget.Toast;

import com.rugao.zhaoshang.asynctask.CreateProjectTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.calendar.CalendarView;
import com.rugao.zhaoshang.utils.Constants;

public class ActivityAddFragment extends ActivityDetailFragment {
	private CalendarView calendarView;

	@Override
	protected void titleRightButtonAction() {
		UserBean ub = getMyApplication().getUserBean();
		String url = Constants.DOMAIN + Constants.ACTIVITY_CREATE;
		CreateProjectTask cpt = new CreateProjectTask(getActivity());
		cpt.execute(
				ActivityAddFragment.this,
				url,
				activityItem.getPostParams(String.valueOf(ub.getUserId()),
						ub.getMemo(), true));
	}

	@Override
	public void setData(DataBean db) {
		if (db.getResult()) {
			Toast.makeText(getActivity(), R.string.create_success,
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getActivity(), R.string.create_fail,
					Toast.LENGTH_SHORT).show();
		}
		getActivity().getSupportFragmentManager().popBackStack();
		calendarView.updateCurrentMonth();
	}

	public void setCalendarView(CalendarView calendarView) {
		this.calendarView = calendarView;
	}

	@Override
	protected void disableEditView() {
	}
}