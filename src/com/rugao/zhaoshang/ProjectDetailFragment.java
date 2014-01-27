package com.rugao.zhaoshang;

import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.rugao.zhaoshang.ChooseFragment.OnFragmentItemClickListener;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.ProjectBean.Project;
import com.rugao.zhaoshang.beans.ValueBean;

public class ProjectDetailFragment extends BaseFragment implements DataView {
	private Project project;
	private EditText pn;
	private TextView ps;
	private TextView ppd;
	private TextView pit;
	private TextView pid;
	private TextView pp;
	private TextView pe;
	private EditText pc;
	private EditText pst;
	private TextView psu;
	private EditText pl;
	private EditText pbt;
	private EditText pta;
	private EditText ptt;
	private EditText ppp;
	private TextView pri;
	private EditText pcl;
	private EditText ptl;
	private EditText pr;
	private TextView pw;
	private EditText pi;
	private EditText pcs;

	private ChooseFragment cf;
	private DisplayFragment df;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View projectDetailLayout = inflater.inflate(
				R.layout.project_detail_layout, container, false);
		pn = (EditText) projectDetailLayout.findViewById(R.id.project_name);
		ps = (TextView) projectDetailLayout.findViewById(R.id.project_stage);
		ppd = (TextView) projectDetailLayout
				.findViewById(R.id.project_planning_date);
		pit = (TextView) projectDetailLayout
				.findViewById(R.id.project_industry_type);
		pid = (TextView) projectDetailLayout
				.findViewById(R.id.project_industry_detail);
		pp = (TextView) projectDetailLayout.findViewById(R.id.project_policy);
		pe = (TextView) projectDetailLayout
				.findViewById(R.id.project_enviroment);
		pc = (EditText) projectDetailLayout.findViewById(R.id.project_case);
		pst = (EditText) projectDetailLayout.findViewById(R.id.project_scale);
		psu = (TextView) projectDetailLayout
				.findViewById(R.id.project_scale_unit);
		pl = (EditText) projectDetailLayout.findViewById(R.id.project_land);
		pbt = (EditText) projectDetailLayout
				.findViewById(R.id.project_build_time);
		pta = (EditText) projectDetailLayout
				.findViewById(R.id.project_total_amount);
		ptt = (EditText) projectDetailLayout
				.findViewById(R.id.project_total_tax);
		ppp = (EditText) projectDetailLayout.findViewById(R.id.project_prjplan);
		pri = (TextView) projectDetailLayout
				.findViewById(R.id.project_responsible_id);
		pcl = (EditText) projectDetailLayout
				.findViewById(R.id.project_city_leader);
		ptl = (EditText) projectDetailLayout
				.findViewById(R.id.project_town_leader);
		pr = (EditText) projectDetailLayout.findViewById(R.id.project_referrer);
		pw = (TextView) projectDetailLayout.findViewById(R.id.project_workers);
		pi = (EditText) projectDetailLayout
				.findViewById(R.id.project_investors);
		pcs = (EditText) projectDetailLayout
				.findViewById(R.id.project_contacts);
		initListeners(projectDetailLayout);
		return projectDetailLayout;
	}

	@Override
	public void onResume() {
		super.onResume();
		pn.setText(project.getProjectName());
		ps.setText(project.getStageIdDisplay());
		ppd.setText(project.getPlanningDate());
		pit.setText(project.getIndustryType());
		pid.setText(project.getIndustryDetail());
		pp.setText(project.getPolicyDisplay());
		pe.setText(project.getEnviromentDisplay());
		pc.setText(project.getCase());
		pst.setText(String.valueOf(project.getScale()));
		psu.setText(project.getScaleUnitDisplay());
		pl.setText(project.getLandRequire());
		pbt.setText(project.getBuildTime());
		pta.setText(String.valueOf(project.getTotalAmount()));
		ptt.setText(String.valueOf(project.getTotalTax()));
		ppp.setText(project.getProjectPlan());
		pri.setText(project.getResponsibleIdDisplay());
		pcl.setText(project.getCityLeader());
		ptl.setText(project.getTownLeader());
		pr.setText(project.getReferrer());
		pw.setText(project.getWorkersDisplay());
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
						project.setStageId(data.get(position).getKey());
						project.setStageIdDisplay(data.get(position).getValue());
					}
				});
				t.replace(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		final DatePickerDialog.OnDateSetListener dateListener = new DatePickerDialog.OnDateSetListener() {
			@Override
			public void onDateSet(DatePicker datePicker, int year, int month,
					int dayOfMonth) {
				String planningDate = year + "-" + (month + 1) + "-"
						+ dayOfMonth;
				project.setPlanningDate(planningDate);
				ppd.setText(planningDate);
			}
		};
		ppd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				new DatePickerDialog(getActivity(), dateListener, calendar
						.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
						calendar.get(Calendar.DAY_OF_MONTH)).show();
			}
		});
		pit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getProjectIndustryType();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						project.setIndustryType(data.get(position).getValue());
					}
				});
				t.replace(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		pid.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getProjectIndustryDetail();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						project.setIndustryDetail(data.get(position).getValue());
					}
				});
				t.replace(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		pp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication().getProjectPolicy();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						project.setPolicyDisplay(data.get(position).getValue());
						project.setPolicy(data.get(position).getKey());
					}
				});
				t.replace(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		pe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getProjectEnviroment();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						project.setEnviromentDisplay(data.get(position).getValue());
						project.setEnviroment(data.get(position).getKey());
					}
				});
				t.replace(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		psu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication().getProjectUnit();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						project.setScaleUnitDisplay(data.get(position).getValue());
						project.setScaleUnit(String.valueOf(data.get(position).getKey()));
					}
				});
				t.replace(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		pri.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (cf == null) {
					cf = new ChooseFragment();
				}
				final List<ValueBean> data = getMyApplication()
						.getProjectManager();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						project.setResponsibleIdDisplay(data.get(position).getValue());
						project.setResponsibleId(data.get(position).getKey());
					}
				});
				t.replace(R.id.content, cf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		pw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (df == null) {
					df = new DisplayFragment();
				}
				df.setProject(project);
				t.replace(R.id.content, df);
				t.addToBackStack(null);
				t.commit();
			}
		});
	}

	@Override
	public void setData(DataBean db) {
	}

	public void setProjectBean(Project project) {
		this.project = project;
	}
}
