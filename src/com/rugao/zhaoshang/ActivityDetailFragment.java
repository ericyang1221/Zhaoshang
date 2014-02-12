package com.rugao.zhaoshang;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
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
	protected ActivityItem activityItem;
	protected EditText an;
	protected TextView pn;
	protected TextView ps;
	protected EditText ac;
	protected TextView cl;
	protected EditText rc;
	protected String selectedDate;

	protected ChooseFragment cf;
	protected DisplayFragment df;
	protected InvestorDisplayFragment idf;
	protected ContactDisplayFragment cdf;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View layout = inflater.inflate(R.layout.activity_detail_layout,
				container, false);
		an = (EditText) layout.findViewById(R.id.activity_name);
		pn = (TextView) layout.findViewById(R.id.project_name);
		ps = (TextView) layout.findViewById(R.id.project_stage);
		ac = (EditText) layout.findViewById(R.id.activity_case);
		cl = (TextView) layout.findViewById(R.id.choose_leaders);
		rc = (EditText) layout.findViewById(R.id.request_content);

		initListeners(layout);

		layout.findViewById(R.id.tl).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				getActivity().getSupportFragmentManager().popBackStack();
			}
		});
		layout.findViewById(R.id.tr).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String activityName = an.getText().toString();
				if (activityName == null || activityName.length() < 1) {
					getBaseActivity().showToast(R.string.pls_input_activity_name);
					return;
				} else {
					activityItem.setActivityIdDisplay(activityName);
				}
				activityItem.setDate(selectedDate);
				activityItem.setActiMemo(ac.getText().toString());
				activityItem.setProblems(rc.getText().toString());
				
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
		an.setText(activityItem.getActivityIdDisplay());
		pn.setText(activityItem.getProjectIdDisplay());
		ps.setText(activityItem.getStageIdDisplay());
		ac.setText(activityItem.getActiMemo());
		rc.setText(activityItem.getProblems());
		cl.setText(activityItem.getLeaderIdsDisplay());
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
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
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
				t.add(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});

		pn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
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
				t.add(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});

		cl.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				save();
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (df == null) {
					df = new DisplayFragment();
				}
				df.setData(activityItem.getLeaderIdsDisplay().split(","),
						activityItem.getLeaderIds().split(","));
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
								activityItem.getLeaderIds().split(","));
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
								activityItem.getLeaderIds().split(","));
						cl.setText(activityItem.getLeaderIdsDisplay());
					}
				});
				t.add(R.id.content, df);
				t.addToBackStack(null);
				t.commit();
			}
		});

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
		getActivity().getSupportFragmentManager().popBackStack();
	}

	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}

	public void setActivityItem(ActivityItem activityItem) {
		this.activityItem = activityItem;
	}
	
	protected void disableEditView(){
		rc.setEnabled(false);
		cl.setEnabled(false);
	}
}