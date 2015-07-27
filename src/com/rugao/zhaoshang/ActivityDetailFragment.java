package com.rugao.zhaoshang;

import java.util.List;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.rugao.zhaoshang.ChooseFragment.OnFragmentItemClickListener;
import com.rugao.zhaoshang.DisplayFragment.DisplayFragmentListener;
import com.rugao.zhaoshang.asynctask.CreateActivityTask;
import com.rugao.zhaoshang.beans.ActivityItem;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.beans.ValueBean;
import com.rugao.zhaoshang.utils.Constants;

public class ActivityDetailFragment extends BaseFragment implements DataView {
	private final String TAG = "ActivityDetailFragment";
	protected ActivityItem activityItem;
	protected EditText an;
	protected TextView pn;
	protected TextView ps;
	protected EditText aa;
	protected EditText ac;
	protected TextView cl;
	protected EditText rc;
	protected String selectedDate;

	protected ChooseFragment cf;
	protected DisplayFragment df;
	protected InvestorDisplayFragment idf;
	protected ContactDisplayFragment cdf;

	protected boolean isClEnable;

	protected ScrollView sv;
	protected int index = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View layout = inflater.inflate(R.layout.activity_detail_layout,
				container, false);
		sv = (ScrollView) layout.findViewById(R.id.activity_detail_sv);
		an = (EditText) layout.findViewById(R.id.activity_name);
		pn = (TextView) layout.findViewById(R.id.project_name);
		ps = (TextView) layout.findViewById(R.id.project_stage);
		aa = (EditText) layout.findViewById(R.id.activity_address);
		ac = (EditText) layout.findViewById(R.id.activity_case);
		cl = (TextView) layout.findViewById(R.id.choose_leaders);
		rc = (EditText) layout.findViewById(R.id.request_content);

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
				String activityName = an.getText().toString();
				String projectName = pn.getText().toString();
				String projectStage = ps.getText().toString();
				String activityAddress = aa.getText().toString();
				String activityCase = ac.getText().toString();
				String requestContent = rc.getText().toString();
				String leaders = activityItem.getLeaderIdsDisplay();

				StringBuffer sb = new StringBuffer();
				String colon = getString(R.string.colon);
				if (activityName == null || activityName.length() < 1) {
					sb.append(getString(R.string.activity_name).replace(colon,
							" "+getString(R.string.can_not_be_empty)+" "));
				}
//				if (projectName == null || projectName.length() < 1) {
//					sb.append(getString(R.string.project_name).replace(colon,
//							"  "));
//				}
//				if (projectStage == null || projectStage.length() < 1) {
//					sb.append(getString(R.string.project_stage).replace(colon,
//							" "+getString(R.string.can_not_be_empty)+" "));
//				}
				if (activityCase == null || activityCase.length() < 30) {
					sb.append(getString(R.string.activity_case).replace(colon,
							" 应大于30字 "));
				}
//				if (requestContent == null || requestContent.length() < 1) {
//					sb.append(getString(R.string.request_content).replace(
//							colon, " "+getString(R.string.can_not_be_empty)+" "));
//				}
//				if (leaders == null || leaders.length() < 1) {
//					sb.append(getString(R.string.choose_leaders).replace(colon,
//							"  "));
//				}
				if (sb.length() > 0) {
//					sb.append(getString(R.string.can_not_be_empty));
					getBaseActivity().showToast(sb.toString());
					return;
				}

				activityItem.setActivityIdDisplay(activityName);
				activityItem.setDate(selectedDate);
				activityItem.setActiMemo(activityCase);
				activityItem.setProblems(requestContent);
				activityItem.setAddress(activityAddress);

				titleRightButtonAction();
			}
		});

		disableEditView();
		return layout;
	}

	protected void titleRightButtonAction() {
		UserBean ub = getMyApplication().getUserBean();
		String url = Constants.DOMAIN + Constants.ACTIVITY_EDIT;
		CreateActivityTask cat = new CreateActivityTask(getActivity());
		cat.execute(
				ActivityDetailFragment.this,
				url,
				activityItem.getPostParams(String.valueOf(ub.getUserId()),
						ub.getMemo(), false));
	}

	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
		if (index != 0 && sv != null) {
			Log.d(TAG, "index: " + index);
			sv.post(new Runnable() {
				@Override
				public void run() {
					sv.scrollTo(0, index);
				}
			});
		}
		an.setText(activityItem.getActivityIdDisplay());
		pn.setText(activityItem.getProjectIdDisplay());
		ps.setText(activityItem.getStageIdDisplay());
		aa.setText(activityItem.getAddress());
		ac.setText(activityItem.getActiMemo());
		rc.setText(activityItem.getProblems());
		cl.setText(activityItem.getLeaderIdsDisplay());

		new Thread(new Runnable() {
			@Override
			public void run() {
				getMyApplication().updateActivityProject();
			}
		}).start();
	}

	private void save() {
		activityItem.setActivityIdDisplay(an.getText().toString());
		activityItem.setActiMemo(ac.getText().toString());
		activityItem.setProblems(rc.getText().toString());
	}

	private void initListeners(View projectDetailLayout) {
		ps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getProjectStage();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						try {
							activityItem.setStageId(Integer.valueOf(data.get(
									position).getKey()));
						} catch (Exception e) {
							activityItem.setStageId(-1);
						}
						activityItem.setStageIdDisplay(data.get(position)
								.getValue());
						ActivityDetailFragment.this.onResume();
					}
				});
				go(cf);
			}
		});

		pn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getActivityProject();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						try {
							activityItem.setProjectId(Integer.valueOf(data.get(
									position).getKey()));
						} catch (Exception e) {
							activityItem.setProjectId(-1);
						}
						activityItem.setProjectIdDisplay(data.get(position)
								.getValue());
						ActivityDetailFragment.this.onResume();
					}
				});
				go(cf);
			}
		});

		cl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
				if (df == null) {
					df = new DisplayFragment();
				}
				df.isEditable(isClEnable);
				df.setData(activityItem.getLeaderIdsDisplay().split(","),
						activityItem.getLeaderIds().split(","),
						DisplayFragment.ACTIVITY_LEADERS);
				df.setDisplayFragmentListener(new DisplayFragmentListener() {
					@Override
					public void onChooseConfirm(List<String> checked,
							List<ValueBean> all) {
						StringBuffer sb = new StringBuffer();
						StringBuffer sbw = new StringBuffer();
						for (String s : checked) {
							sb.append(s).append(",");
							for (ValueBean vb : all) {
								if (String.valueOf(vb.getKey()).equals(s)) {
									sbw.append(vb.getValue()).append(",");
									break;
								}
							}
						}
						if (sb.length() > 0) {
							activityItem.setLeaderIds(sb.substring(0,
									sb.length() - 1));
							if (sbw.length() > 0) {
								activityItem.setLeaderIdsDisplay(sbw.substring(
										0, sbw.length() - 1));
							}
						} else {
							activityItem.setLeaderIds("");
							activityItem.setLeaderIdsDisplay("");
						}
						df.setData(activityItem.getLeaderIdsDisplay()
								.split(","),
								activityItem.getLeaderIds().split(","),
								DisplayFragment.ACTIVITY_LEADERS);
						cl.setText(activityItem.getLeaderIdsDisplay());
					}

					@Override
					public void onItemDelete(int position,
							List<String> wsdList, List<ValueBean> it) {
						wsdList.remove(position);
						StringBuffer sbd = new StringBuffer();
						StringBuffer sb = new StringBuffer();
						for (String s : wsdList) {
							sbd.append(s).append(",");
							for (ValueBean vb : it) {
								if (vb.getValue().equals(s)) {
									sb.append(vb.getKey()).append(",");
									break;
								}
							}
						}
						if (sb.length() > 0) {
							activityItem.setLeaderIds(sb.substring(0,
									sb.length() - 1));
							if (sbd.length() > 0) {
								activityItem.setLeaderIdsDisplay(sbd.substring(
										0, sbd.length() - 1));
							}
						} else {
							activityItem.setLeaderIds("");
							activityItem.setLeaderIdsDisplay("");
						}
						df.setData(activityItem.getLeaderIdsDisplay()
								.split(","),
								activityItem.getLeaderIds().split(","),
								DisplayFragment.ACTIVITY_LEADERS);
						cl.setText(activityItem.getLeaderIdsDisplay());
					}
				});
				go(df);
			}
		});
	}

	@Override
	public void onDestroyView() {
		Log.d(TAG, "onDestroyView");
		if (sv != null) {
			index = sv.getScrollY();
			Log.d(TAG, "index: " + index);
		}
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

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public void setActivityItem(ActivityItem activityItem) {
		this.activityItem = activityItem;
	}

	protected void disableEditView() {
		rc.setEnabled(false);
		isClEnable = false;
	}
}