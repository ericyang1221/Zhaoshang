package com.rugao.zhaoshang;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rugao.zhaoshang.ChooseFragment.OnFragmentItemClickListener;
import com.rugao.zhaoshang.asynctask.UpdateProjectEavlateTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.Project;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.beans.ValueBean;
import com.rugao.zhaoshang.utils.Constants;
import com.rugao.zhaoshang.utils.URLGenerater;

public class ProjectEavlateFragment extends BaseFragment implements DataView {
	private final String TAG = "ProjectEavlate";
	protected Project project;
	protected TextView pt;
	protected TextView ps;
	protected ChooseFragment cf;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View layout = inflater.inflate(R.layout.project_evaluate_layout,
				container, false);
		pt = (TextView) layout.findViewById(R.id.project_listview_item_type);
		ps = (TextView) layout.findViewById(R.id.project_listview_item_status);
		initListeners(layout);

		layout.findViewById(R.id.tl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				goBack();
			}
		});
		layout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				titleRightButtonAction();
			}
		});
		return layout;
	}

	protected void titleRightButtonAction() {
		UserBean ub = getMyApplication().getUserBean();
		new UpdateProjectEavlateTask(getActivity()).execute(
				ProjectEavlateFragment.this, URLGenerater.makeUrl(
						Constants.PROJECT_UPDATE_EAVLATE,
						new String[] { String.valueOf(ub.getUserId()),
								ub.getMemo(),
								String.valueOf(project.getProjectId()),
								String.valueOf(project.getType()),
								String.valueOf(project.getStatus()) }));
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		pt.setText(project.getTypeDisplay());
		ps.setText(project.getStatusDisplay());
	}

	private void initListeners(View projectDetailLayout) {
		pt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getProjectType();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						try {
							project.setType(Integer.valueOf(data.get(position)
									.getKey()));
						} catch (Exception e) {
							project.setType(-1);
						}
						project.setTypeDisplay(data.get(position).getValue());
						pt.setText(project.getTypeDisplay());
					}
				});
				go(cf);
			}
		});
		ps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getProjectStatus();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						try {
							project.setStatus(Integer.valueOf(data
									.get(position).getKey()));
						} catch (Exception e) {
							project.setStatus(-1);
						}
						project.setStatusDisplay(data.get(position).getValue());
						ps.setText(project.getStatusDisplay());
					}
				});
				go(cf);
			}
		});
	}

	@Override
	public void onDestroyView() {
		Log.d(TAG, "onDestroyView");
		super.onDestroyView();
	}

	@Override
	public void setData(DataBean db) {
		if (db.getResult()) {
			Toast.makeText(getActivity(), R.string.update_success,
					Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(getActivity(), R.string.update_fail,
					Toast.LENGTH_SHORT).show();
		}
		goBack();
	}

	public void setProjectBean(Project project) {
		this.project = project;
	}
}