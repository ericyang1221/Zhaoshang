package com.rugao.zhaoshang;

import java.util.Calendar;
import java.util.List;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.rugao.zhaoshang.ChooseFragment.OnFragmentItemClickListener;
import com.rugao.zhaoshang.DisplayFragment.DisplayFragmentListener;
import com.rugao.zhaoshang.asynctask.CreateProjectTask;
import com.rugao.zhaoshang.beans.Contact;
import com.rugao.zhaoshang.beans.DataBean;
import com.rugao.zhaoshang.beans.Investor;
import com.rugao.zhaoshang.beans.Project;
import com.rugao.zhaoshang.beans.UserBean;
import com.rugao.zhaoshang.beans.ValueBean;
import com.rugao.zhaoshang.utils.Constants;

public class ProjectDetailFragment extends BaseFragment implements DataView {
	private final String TAG = "ProjectDetailFragment";
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
	protected EditText pri;
	protected EditText pcl;
	protected EditText ptl;
	protected EditText pr;
	protected TextView pw;
	protected EditText pm;
	protected CheckBox isCompany;
	protected View pi;
	protected View pcs;
	protected TextView piText;
	protected TextView pcsText;

	protected ChooseFragment cf;
	protected DisplayFragment df;
	protected InvestorDisplayFragment idf;
	protected ContactDisplayFragment cdf;

	protected ScrollView sv;
	protected int index = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		View projectDetailLayout = inflater.inflate(
				R.layout.project_detail_layout, container, false);
		sv = (ScrollView) projectDetailLayout
				.findViewById(R.id.project_detail_sv);
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
		pri = (EditText) projectDetailLayout
				.findViewById(R.id.project_responsible_id);
		pcl = (EditText) projectDetailLayout
				.findViewById(R.id.project_city_leader);
		ptl = (EditText) projectDetailLayout
				.findViewById(R.id.project_town_leader);
		pr = (EditText) projectDetailLayout.findViewById(R.id.project_referrer);
		pw = (TextView) projectDetailLayout.findViewById(R.id.project_workers);
		pm = (EditText) projectDetailLayout.findViewById(R.id.project_content);
		isCompany = (CheckBox) projectDetailLayout.findViewById(R.id.project_iscompany);
		pi = projectDetailLayout.findViewById(R.id.project_investors);
		pcs = projectDetailLayout.findViewById(R.id.project_contacts);
		piText = (TextView) projectDetailLayout
				.findViewById(R.id.project_investors_text);
		pcsText = (TextView) projectDetailLayout
				.findViewById(R.id.project_contacts_text);
		initListeners(projectDetailLayout);

		projectDetailLayout.findViewById(R.id.tl).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						goBack();
					}
				});
		projectDetailLayout.findViewById(R.id.tr).setOnClickListener(
				new OnClickListener() {
					@Override
					public void onClick(View v) {
						String projectName = pn.getText().toString();
						String projectStage = ps.getText().toString();
						String projectPlanningDate = ppd.getText().toString();
						String projectIndustryType = pit.getText().toString();
						String projectIndustryDetail = pid.getText().toString();
						String projectPolicy = pp.getText().toString();
						String projectEnviroment = pe.getText().toString();
						String projectCase = pc.getText().toString();
						String projectScale = pst.getText().toString();
						String projectScaleUnit = psu.getText().toString();
						String projectLand = pl.getText().toString();
						String projectBuildTime = pbt.getText().toString();
						String projectTotalAmount = pta.getText().toString();
						String projectTotalTax = ptt.getText().toString();
						String projectPrjplan = ppp.getText().toString();
						String projectResponsibler = pri.getText().toString();
						String projectCityLeader = pcl.getText().toString();
						String projectTownLeader = ptl.getText().toString();
						String projectReferrer = pr.getText().toString();
						String projectWorkers = pw.getText().toString();
						String projectMemo = pm.getText().toString();
						List<Investor> iList = project.getInvestors();
						List<Contact> cList = project.getContacts();

						StringBuffer sb = new StringBuffer();
//						if (projectWorkers == null
//								|| projectWorkers.length() < 1) {
//							sb.append(getString(R.string.check_workers))
//									.append("  ");
//						}
						if (iList == null || iList.size() < 1) {
							sb.append(getString(R.string.check_investor))
									.append("  ");
						}
						if (cList == null || cList.size() < 1) {
							sb.append(getString(R.string.check_contact))
									.append("  ");
						}
						StringBuffer sb2 = new StringBuffer();
						String colon = getString(R.string.colon);
						if (projectName == null || projectName.length() < 1) {
							sb2.append(getString(R.string.project_name)
									.replace(colon, "  "));
						}
						if (projectStage == null || projectStage.length() < 1) {
							sb2.append(getString(R.string.project_stage)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectPlanningDate == null
								|| projectPlanningDate.length() < 1) {
							sb2.append(getString(R.string.project_planning_date)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectIndustryType == null
								|| projectIndustryType.length() < 1) {
							sb2.append(getString(R.string.project_industry_type)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectIndustryDetail == null
								|| projectIndustryDetail.length() < 1) {
							sb2.append(getString(
									R.string.project_industry_detail).replace(
									colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectPolicy == null || projectPolicy.length() < 1) {
							sb2.append(getString(R.string.project_policy)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectEnviroment == null
								|| projectEnviroment.length() < 1) {
							sb2.append(getString(R.string.project_enviroment)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectCase == null || projectCase.length() > 100) {
							sb2.append(getString(R.string.project_case)
									.replace(colon, " 投资方成功案例应小于100字 "));
						}
						if (projectScale == null || projectScale.length() < 1) {
							sb2.append(getString(R.string.project_scale)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectScaleUnit == null
								|| projectScaleUnit.length() < 1) {
							sb2.append(getString(R.string.project_scale_unit)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectLand == null || projectLand.length() < 1) {
							sb2.append(getString(R.string.project_land)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectBuildTime == null
								|| projectBuildTime.length() < 1) {
							sb2.append(getString(R.string.project_build_time)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectTotalAmount == null
								|| projectTotalAmount.length() < 1) {
							sb2.append(getString(R.string.project_total_amount)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectTotalTax == null
								|| projectTotalTax.length() < 1) {
							sb2.append(getString(R.string.project_total_tax)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectPrjplan == null
								|| projectPrjplan.length() < 30 || projectPrjplan.length() > 100) {
							sb2.append(getString(R.string.project_prjplan)
									.replace(colon, " 投资实施计划字数应为30-100字 "));
						}
						if (projectResponsibler == null
								|| projectResponsibler.length() < 1) {
							sb2.append(getString(
									R.string.project_responsible_id).replace(
									colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectCityLeader == null
								|| projectCityLeader.length() < 1) {
							sb2.append(getString(R.string.project_city_leader)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
						if (projectTownLeader == null
								|| projectTownLeader.length() < 1) {
							sb2.append(getString(R.string.project_town_leader)
									.replace(colon, " "+getString(R.string.can_not_be_empty)+" "));
						}
//						if (projectReferrer == null
//								|| projectReferrer.length() < 1) {
//							sb2.append(getString(R.string.project_referrer)
//									.replace(colon, "  "));
//						}
//						if (sb2.length() > 0) {
//							sb2.append(getString(R.string.not_meet_requirement));
//						}
//						sb.append(sb2);
						if (sb.length() > 0) {
							getBaseActivity().showToast(sb.toString());
							return;
						}

						project.setProjectName(projectName);
						project.setCase(projectCase);
						try {
							project.setScale(Double.valueOf(projectScale));
						} catch (Exception e) {
							project.setScale(null);
						}
						project.setLandRequire(projectLand);
						project.setBuildTime(projectBuildTime);
						try {
							project.setTotalAmount(Long
									.valueOf(projectTotalAmount));
						} catch (Exception e) {
							project.setTotalAmount(null);
						}
						try {
							project.setTotalTax(Double.valueOf(projectTotalTax));
						} catch (Exception e) {
							project.setTotalTax(null);
						}
						project.setProjectPlan(projectPrjplan);
						project.setCityLeader(projectCityLeader);
						project.setResponsibler(projectResponsibler);
						project.setTownLeader(projectTownLeader);
						project.setReferrer(projectReferrer);
						project.setProjectMemo(projectMemo);

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
		pn.setText(project.getProjectName());
		ps.setText(project.getStageIdDisplay());
		ppd.setText(project.getPlanningDate());
		pit.setText(project.getIndustryType() == null ? "" : project
				.getIndustryType());
		pid.setText(project.getIndustryDetail() == null ? "" : project
				.getIndustryDetail());
		pp.setText(project.getPolicyDisplay() == null ? "" : project
				.getPolicyDisplay());
		pe.setText(project.getEnviromentDisplay() == null ? "" : project
				.getEnviromentDisplay());
		pc.setText(project.getCase() == null ? "" : project.getCase());
		pst.setText(String.valueOf(project.getScale() == null ? "" : project
				.getScale()));
		psu.setText(project.getScaleUnitDisplay());
		pl.setText(project.getLandRequire() == null ? "" : project
				.getLandRequire());
		pbt.setText(project.getBuildTime() == null ? "" : project
				.getBuildTime());
		pta.setText(String.valueOf(project.getTotalAmount() == null
				|| project.getTotalAmount() == -1 ? "" : project
				.getTotalAmount()));
		ptt.setText(String.valueOf(project.getTotalTax() == null
				|| project.getTotalTax() == -1 ? "" : project.getTotalTax()));
		ppp.setText(project.getProjectPlan() == null ? "" : project
				.getProjectPlan());
		pri.setText(project.getResponsibler() == null ? "" : project
				.getResponsibler());
		pcl.setText(project.getCityLeader() == null ? "" : project
				.getCityLeader());
		ptl.setText(project.getTownLeader() == null ? "" : project
				.getTownLeader());
		pr.setText(project.getReferrer() == null ? "" : project.getReferrer());
		pw.setText(project.getWorkersDisplay());
		String piTxt = project.getInvestorsDisplay();
		piText.setText(piTxt);
		if(piTxt == null||piTxt.length()<1){
			isCompany.setChecked(true);
		}
		pcsText.setText(project.getContactsDisplay());
	}

	private void initListeners(View projectDetailLayout) {
		pn.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setProjectName(pn.getText().toString());
			}
		});
		pc.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setCase(pc.getText().toString());
			}
		});
		pst.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				try {
					project.setScale(Double.valueOf(pst.getText().toString()));
				} catch (Exception e) {
					// project.setScale(0);
					// pst.setText(String.valueOf(0));
					// getBaseActivity().showToast(R.string.pls_input_number);
				}
			}
		});
		pl.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setLandRequire(pl.getText().toString());
			}
		});
		pbt.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setBuildTime(pbt.getText().toString());
			}
		});
		pta.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				try {
					project.setTotalAmount(Long.valueOf(pta.getText()
							.toString()));
				} catch (Exception e) {
					// project.setTotalAmount(0);
					// pta.setText(String.valueOf(0));
					// getBaseActivity().showToast(R.string.pls_input_number);
				}
			}
		});
		ptt.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				try {
					project.setTotalTax(Double
							.valueOf(ptt.getText().toString()));
				} catch (Exception e) {
					// project.setTotalTax(0);
					// ptt.setText(String.valueOf(0));
					// getBaseActivity().showToast(R.string.pls_input_number);
				}
			}
		});
		ppp.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setProjectPlan(ppp.getText().toString());
			}
		});
		pri.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setResponsibler(pri.getText().toString());
			}
		});
		pcl.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setCityLeader(pcl.getText().toString());
			}
		});
		ptl.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setTownLeader(ptl.getText().toString());
			}
		});
		pr.setOnFocusChangeListener(new OnFocusChangeListener() {
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				project.setReferrer(pr.getText().toString());
			}
		});
		ps.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
							project.setStageId(Integer.valueOf(data.get(
									position).getKey()));
						} catch (Exception e) {
							project.setStageId(-1);
						}
						project.setStageIdDisplay(data.get(position).getValue());
						ps.setText(project.getStageIdDisplay());
					}
				});
				go(cf);
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
						pit.setText(project.getIndustryType() == null ? ""
								: project.getIndustryType());
					}
				});
				go(cf);
			}
		});
		pid.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
						pid.setText(project.getIndustryDetail() == null ? ""
								: project.getIndustryDetail());
					}
				});
				go(cf);
			}
		});
		pp.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
						try {
							project.setPolicy(Integer.valueOf(data
									.get(position).getKey()));
						} catch (Exception e) {
							project.setPolicy(-1);
						}
						pp.setText(project.getPolicyDisplay() == null ? ""
								: project.getPolicyDisplay());
					}
				});
				go(cf);
			}
		});
		pe.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
						try {
							project.setEnviroment(Integer.valueOf(data.get(
									position).getKey()));
						} catch (Exception e) {
							project.setEnviroment(-1);
						}
						pe.setText(project.getEnviromentDisplay() == null ? ""
								: project.getEnviromentDisplay());
					}
				});
				go(cf);
			}
		});
		psu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
						psu.setText(project.getScaleUnitDisplay());
					}
				});
				go(cf);
			}
		});
		pw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (df == null) {
					df = new DisplayFragment();
				}
				// df.setProject(project);
				df.setData(project.getWorkersDisplay().split(","), project
						.getWorkers().split(","),
						DisplayFragment.PROJECT_WORKERS);
				df.setDisplayFragmentListener(new DisplayFragmentListener() {
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
							project.setWorkers(sb.substring(0, sb.length() - 1));
							if (sbd.length() > 0) {
								project.setWorkersDisplay(sbd.substring(0,
										sbd.length() - 1));
							}
						} else {
							project.setWorkers("");
							project.setWorkersDisplay("");
						}
						df.setData(project.getWorkersDisplay().split(","),
								project.getWorkers().split(","),
								DisplayFragment.PROJECT_WORKERS);
						pw.setText(project.getWorkersDisplay());
					}

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
							project.setWorkers(sb.substring(0, sb.length() - 1));
							if (sbw.length() > 0) {
								project.setWorkersDisplay(sbw.substring(0,
										sbw.length() - 1));
							}
						} else {
							project.setWorkers("");
							project.setWorkersDisplay("");
						}
						df.setData(project.getWorkersDisplay().split(","),
								project.getWorkers().split(","),
								DisplayFragment.PROJECT_WORKERS);
						pw.setText(project.getWorkersDisplay());
					}
				});
				go(df);
			}
		});

		pi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (idf == null) {
					idf = new InvestorDisplayFragment();
				}
				idf.setProject(project);
				go(idf);
			}
		});
		pcs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (cdf == null) {
					cdf = new ContactDisplayFragment();
				}
				cdf.setProject(project);
				go(cdf);
			}
		});
		isCompany.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(pi != null){
					if(!isChecked){
						pi.setVisibility(View.GONE);
					}else{
						pi.setVisibility(View.VISIBLE);
					}
				}
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

	public void setProjectBean(Project project) {
		this.project = project;
	}
}