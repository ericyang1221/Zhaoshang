package com.rugao.zhaoshang.beans;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ProjectBean extends DataBean {
	private int totalCount;
	private List<Project> projectList;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public List<Project> getProjects() {
		return projectList;
	}

	public void setProjects(List<Project> projectList) {
		this.projectList = projectList;
	}

	public ProjectBean(JSONObject rs) throws JSONException {
		super(rs);
		if (resultData != null) {
			if (resultData.has("totalCount")) {
				totalCount = resultData.getInt("totalCount");
			}
			if (resultData.has("data")) {
				projectList = new ArrayList<Project>();
				JSONArray ja = resultData.getJSONArray("data");
				if (ja != null) {
					for (int i = 0; i < ja.length(); i++) {
						JSONObject jo = ja.getJSONObject(i);
						Project p = new Project();
						if (jo.has("ProjectId")) {
							int pid = -1;
							try {
								pid = jo.getInt("ProjectId");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setProjectId(pid);
						}
						if (jo.has("PrjName")) {
							p.setProjectName(jo.getString("PrjName"));
						}
						if (jo.has("PlanningDate")) {
							p.setPlanningDate(jo.getString("PlanningDate"));
						}
						if (jo.has("StageId")) {
							int sid = -1;
							try {
								sid = jo.getInt("StageId");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setStageId(sid);
						}
						if (jo.has("StageId_display")) {
							p.setStageIdDisplay(jo.getString("StageId_display"));
						}
						if (jo.has("IndustryType")) {
							p.setIndustryType(jo.getString("IndustryType"));
						}
						if (jo.has("IndustryDetail")) {
							p.setIndustryDetail(jo.getString("IndustryDetail"));
						}
						if (jo.has("Policy")) {
							int pid = -1;
							try {
								pid = jo.getInt("Policy");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setPolicy(pid);
						}
						if (jo.has("Policy_display")) {
							p.setPolicyDisplay(jo.getString("Policy_display"));
						}
						if (jo.has("Enviroment")) {
							int eid = -1;
							try {
								eid = jo.getInt("Enviroment");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setEnviroment(eid);
						}
						if (jo.has("Enviroment_display")) {
							p.setEnviromentDisplay(jo
									.getString("Enviroment_display"));
						}
						if (jo.has("Scale")) {
							double sc = -1;
							try {
								sc = jo.getDouble("Scale");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setScale(sc);
						}
						if (jo.has("ScaleUnit")) {
							p.setScaleUnit(jo.getString("ScaleUnit"));
						}
						if (jo.has("ScaleUnit_display")) {
							p.setScaleUnitDisplay(jo
									.getString("ScaleUnit_display"));
						}
						if (jo.has("PrjMemo")) {
							p.setProjectMemo(jo.getString("PrjMemo"));
						}
						if (jo.has("LandRequire")) {
							p.setLandRequire(jo.getString("LandRequire"));
						}
						if (jo.has("WorkRequire")) {
							p.setWorkRequire(jo.getString("WorkRequire"));
						}
						if (jo.has("BuildTime")) {
							p.setBuildTime(jo.getString("BuildTime"));
						}
						if (jo.has("TotalAmount")) {
							long ta = -1;
							try {
								ta = jo.getLong("TotalAmount");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setTotalAmount(ta);
						}
						if (jo.has("TotalTax")) {
							double tt = -1;
							try {
								tt = jo.getDouble("TotalTax");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setTotalTax(tt);
						}
						if (jo.has("PrjPlan")) {
							p.setProjectPlan(jo.getString("PrjPlan"));
						}
						if (jo.has("Case")) {
							p.setCase(jo.getString("Case"));
						}
						if (jo.has("ResponsibleId")) {
							int rid = -1;
							try {
								rid = jo.getInt("ResponsibleId");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setResponsibleId(rid);
						}
						if (jo.has("ResponsibleId_display")) {
							p.setResponsibleIdDisplay(jo
									.getString("ResponsibleId_display"));
						}
						if (jo.has("CityLeader")) {
							p.setCityLeader(jo.getString("CityLeader"));
						}
						if (jo.has("TownLeader")) {
							p.setTownLeader(jo.getString("TownLeader"));
						}
						if (jo.has("Referrer")) {
							p.setReferrer(jo.getString("Referrer"));
						}
						if (jo.has("Workers")) {
							p.setWorkers(jo.getString("Workers"));
						}
						if (jo.has("Workers_display")) {
							p.setWorkersDisplay(jo.getString("Workers_display"));
						}
						if (jo.has("CreateId")) {
							int cid = -1;
							try {
								cid = jo.getInt("CreateId");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setCreatedId(cid);
						}
						if (jo.has("CreateId_display")) {
							p.setCreateIdDisplay(jo
									.getString("CreateId_display"));
						}
						if (jo.has("CreateDate")) {
							p.setCreateDate(jo.getString("CreateDate"));
						}
						if (jo.has("UpdateId")) {
							int uid = -1;
							try {
								uid = jo.getInt("UpdateId");
							} catch (Exception e) {
								e.printStackTrace();
							}
							p.setUpdateId(uid);
						}
						if (jo.has("UpdateId_display")) {
							p.setUpdateIdDisplay(jo
									.getString("UpdateId_display"));
						}
						if (jo.has("UpdateDate")) {
							p.setUpdateDate(jo.getString("UpdateDate"));
						}
						if (jo.has("Investor")) {
							List<Investor> investorList = new ArrayList<Investor>();
							JSONArray jai = jo.getJSONArray("Investor");
							if (jai != null) {
								for (int j = 0; j < jai.length(); j++) {
									JSONObject ji = jai.getJSONObject(j);
									Investor ir = new Investor();
									if (ji.has("InvestorId")) {
										int iid = -1;
										try {
											iid = ji.getInt("InvestorId");
										} catch (Exception e) {
											e.printStackTrace();
										}
										ir.setInvestorId(iid);
									}
									if (ji.has("ProjectId")) {
										int pid = -1;
										try {
											pid = ji.getInt("ProjectId");
										} catch (Exception e) {
											e.printStackTrace();
										}
										ir.setProjectId(pid);
									}
									if (ji.has("InvestorName")) {
										ir.setInvestorName(ji
												.getString("InvestorName"));
									}
									if (ji.has("Address")) {
										ir.setAddress(ji.getString("Address"));
									}
									if (ji.has("Status")) {
										ir.setStatus(ji.getString("Status"));
									}
									if (ji.has("MainProduct")) {
										ir.setMainProduct(ji
												.getString("MainProduct"));
									}
									if (ji.has("ProductMemo")) {
										ir.setProductMemo(ji
												.getString("ProductMemo"));
									}
									if (ji.has("Memo")) {
										ir.setMemo(ji.getString("Memo"));
									}
									investorList.add(ir);
								}
							}
							p.setInvestors(investorList);
						}
						if (jo.has("Contacts")) {
							List<Contact> contactList = new ArrayList<Contact>();
							JSONArray jac = jo.getJSONArray("Contacts");
							if (jac != null) {
								for (int j = 0; j < jac.length(); j++) {
									JSONObject jc = jac.getJSONObject(j);
									Contact c = new Contact();
									if (jc.has("ContactId")) {
										int cid = -1;
										try {
											cid = jc.getInt("ContactId");
										} catch (Exception e) {
											e.printStackTrace();
										}
										c.setContactId(cid);
									}
									if (jc.has("ProjectId")) {
										int pid = -1;
										try {
											pid = jc.getInt("ProjectId");
										} catch (Exception e) {
											e.printStackTrace();
										}
										c.setProjectId(pid);
									}
									if (jc.has("Name")) {
										c.setName(jc.getString("Name"));
									}
									if (jc.has("Role")) {
										c.setRole(jc.getString("Role"));
									}
									if (jc.has("Telephone")) {
										c.setTelephone(jc
												.getString("Telephone"));
									}
									if (jc.has("Phone")) {
										c.setPhone(jc.getString("Phone"));
									}
									if (jc.has("Memo")) {
										c.setMemo(jc.getString("Memo"));
									}
									contactList.add(c);
								}
							}
							p.setContacts(contactList);
						}
						projectList.add(p);
					}
				}
			}
		}
	}
}
