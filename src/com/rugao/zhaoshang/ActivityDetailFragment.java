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
import com.rugao.zhaoshang.beans.ActivityItem;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.ValueBean;

public class ActivityDetailFragment extends BaseFragment implements DataView {
	protected ActivityItem activityItem;
	protected EditText an;
	protected TextView pn;
	protected TextView ps;
	protected EditText ac;
	protected TextView cl;
	protected EditText rc;

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
				titleRightButtonAction();
			}
		});
		return layout;
	}

	protected void titleRightButtonAction() {
		// UserBean ub = getMyApplication().getUserBean();
		// String url = Constants.DOMAIN + Constants.PROJECT_EDIT;
		// CreateProjectTask cpt = new CreateProjectTask(getActivity());
		// cpt.execute(
		// ActivityDetailFragment.this,
		// url,
		// project.getPostParams(String.valueOf(ub.getUserId()),
		// ub.getMemo(), false));
		// System.out.println(project.toString());
	}

	@Override
	public void onResume() {
		super.onResume();
		an.setText(activityItem.getActivityIdDisplay());
		pn.setText(activityItem.getProjectIdDisplay());
		ps.setText(activityItem.getStageIdDisplay());
		ac.setText(activityItem.getActiMemo());
		rc.setText(activityItem.getProblems());
	}

	private void initListeners(View projectDetailLayout) {
		ps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
						activityItem.setStageId(data.get(position).getKey());
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
						activityItem.setProjectId(data.get(position).getKey());
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
		System.out.println(db.getResultMsg());
	}

	public void setActivityItem(ActivityItem activityItem) {
		this.activityItem = activityItem;
	}
}