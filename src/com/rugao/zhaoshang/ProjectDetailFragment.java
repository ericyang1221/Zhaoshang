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
import android.widget.Toast;

import com.rugao.zhaoshang.ChooseFragment.OnFragmentItemClickListener;
import com.rugao.zhaoshang.asynctask.CreateProjectTask;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.Project;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.beans.ValueBean;
import com.rugao.zhaoshang.utils.Constants;

public class ProjectDetailFragment extends BaseFragment implements DataView {
	protected Project project;
	protected EditText pn;
	protected TextView ps;
	protected TextView ppd;
	protected TextView pit;
	protected TextView pid;
	protected TextView pp;
	protected TextView pe;
	protected EditText pc;
	protected EditText pst;
	protected TextView psu;
	protected EditText pl;
	protected EditText pbt;
	protected EditText pta;
	protected EditText ptt;
	protected EditText ppp;
	protected TextView pri;
	protected EditText pcl;
	protected EditText ptl;
	protected EditText pr;
	protected TextView pw;
	protected View pi;
	protected View pcs;

	protected ChooseFragment cf;
	protected DisplayFragment df;
	protected InvestorDisplayFragment idf;
	protected ContactDisplayFragment cdf;

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
		pi = projectDetailLayout.findViewById(R.id.project_investors);
		pcs = projectDetailLayout.findViewById(R.id.project_contacts);
		initListeners(projectDetailLayout);

		projectDetailLayout.findViewById(R.id.tl).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						getActivity().getSupportFragmentManager()
								.popBackStack();
					}
				});
		projectDetailLayout.findViewById(R.id.tr).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						titleRightButtonAction();
					}
				});
		return projectDetailLayout;
	}

	protected void titleRightButtonAction() {
		UserBean ub = getMyApplication().getUserBean();
		String url = Constants.DOMAIN + Constants.PROJECT_EDIT;
		CreateProjectTask cpt = new CreateProjectTask(getActivity());
		cpt.execute(
				ProjectDetailFragment.this,
				url,
				project.getPostParams(String.valueOf(ub.getUserId()),
						ub.getMemo(), false));
		System.out.println(project.toString());
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
		pst.setText(String.valueOf(project.getScale() == 0 ? "" : project
				.getScale()));
		psu.setText(project.getScaleUnitDisplay());
		pl.setText(project.getLandRequire());
		pbt.setText(project.getBuildTime());
		pta.setText(String.valueOf(project.getTotalAmount() == 0 ? "" : project
				.getTotalAmount()));
		ptt.setText(String.valueOf(project.getTotalTax() == 0 ? "" : project
				.getTotalTax()));
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
				final List<ValueBean> data = getMyApplication()
						.getProjectPolicy();
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
						project.setEnviromentDisplay(data.get(position)
								.getValue());
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
				final List<ValueBean> data = getMyApplication()
						.getProjectUnit();
				cf.setData(data);
				cf.setOnFragmentItemClickListener(new OnFragmentItemClickListener() {
					@Override
					public void onItemClick(int position) {
						project.setScaleUnitDisplay(data.get(position)
								.getValue());
						project.setScaleUnit(String.valueOf(data.get(position)
								.getKey()));
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
						project.setResponsibleIdDisplay(data.get(position)
								.getValue());
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

		pi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (idf == null) {
					idf = new InvestorDisplayFragment();
				}
				idf.setProject(project);
				t.replace(R.id.content, idf);
				t.addToBackStack(null);
				t.commit();
			}
		});
		pcs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FragmentTransaction t = getActivity()
						.getSupportFragmentManager().beginTransaction();
				if (cdf == null) {
					cdf = new ContactDisplayFragment();
				}
				cdf.setProject(project);
				t.replace(R.id.content, cdf);
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

	public void setProjectBean(Project project) {
		this.project = project;
	}
}