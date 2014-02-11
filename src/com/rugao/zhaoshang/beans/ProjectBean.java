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
							Integer pid = null;
							try {
								pid = jo.getInt("ProjectId");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setProjectId(pid);
						}
						if (jo.has("PrjName")) {
							String prjName = jo.getString("PrjName");
							p.setProjectName(prjName==null||prjName.equals("null")?null:prjName);
						}
						if (jo.has("PlanningDate")) {
							String pd = jo.getString("PlanningDate");
							p.setPlanningDate(pd==null||pd.equals("null")?null:pd);
						}
						if (jo.has("StageId")) {
							Integer sid = null;
							try {
								sid = jo.getInt("StageId");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setStageId(sid);
						}
						if (jo.has("StageId_display")) {
							String sd = jo.getString("StageId_display");
							p.setStageIdDisplay(sd==null||sd.equals("null")?null:sd);
						}
						if (jo.has("IndustryType")) {
							String it = jo.getString("IndustryType");
							p.setIndustryType(it==null||it.equals("null")?null:it);
						}
						if (jo.has("IndustryDetail")) {
							String id = jo.getString("IndustryDetail");
							p.setIndustryDetail(id==null||id.equals("null")?null:id);
						}
						if (jo.has("Policy")) {
							Integer pid = null;
							try {
								pid = jo.getInt("Policy");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setPolicy(pid);
						}
						if (jo.has("Policy_display")) {
							String pd = jo.getString("Policy_display");
							p.setPolicyDisplay(pd==null||pd.equals("null")?null:pd);
						}
						if (jo.has("Enviroment")) {
							Integer eid = null;
							try {
								eid = jo.getInt("Enviroment");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setEnviroment(eid);
						}
						if (jo.has("Enviroment_display")) {
							String ed = jo
									.getString("Enviroment_display");
							p.setEnviromentDisplay(ed==null||ed.equals("null")?null:ed);
						}
						if (jo.has("Scale")) {
							Double sc = null;
							try {
								sc = jo.getDouble("Scale");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setScale(sc);
						}
						if (jo.has("ScaleUnit")) {
							String su = jo.getString("ScaleUnit");
							p.setScaleUnit(su==null||su.equals("null")?null:su);
						}
						if (jo.has("ScaleUnit_display")) {
							String sd = jo
									.getString("ScaleUnit_display");
							p.setScaleUnitDisplay(sd==null||sd.equals("null")?null:sd);
						}
						if (jo.has("PrjMemo")) {
							String pm = jo.getString("PrjMemo");
							p.setProjectMemo(pm==null||pm.equals("null")?null:pm);
						}
						if (jo.has("LandRequire")) {
							String lr = jo.getString("LandRequire");
							p.setLandRequire(lr==null||lr.equals("null")?null:lr);
						}
						if (jo.has("WorkRequire")) {
							String wr = jo.getString("WorkRequire");
							p.setWorkRequire(wr==null||wr.equals("null")?null:wr);
						}
						if (jo.has("BuildTime")) {
							String bt = jo.getString("BuildTime");
							p.setBuildTime(bt==null||bt.equals("null")?null:bt);
						}
						if (jo.has("TotalAmount")) {
							Long ta = null;
							try {
								ta = jo.getLong("TotalAmount");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setTotalAmount(ta);
						}
						if (jo.has("TotalTax")) {
							Double tt = null;
							try {
								tt = jo.getDouble("TotalTax");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setTotalTax(tt);
						}
						if (jo.has("PrjPlan")) {
							String pp = jo.getString("PrjPlan");
							p.setProjectPlan(pp==null||pp.equals("null")?null:pp);
						}
						if (jo.has("Case")) {
							String c = jo.getString("Case");
							p.setCase(c==null||c.equals("null")?null:c);
						}
						if (jo.has("ResponsibleId")) {
							Integer rid = null;
							try {
								rid = jo.getInt("ResponsibleId");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setResponsibleId(rid);
						}
						if (jo.has("ResponsibleId_display")) {
							String rd = jo
									.getString("ResponsibleId_display");
							p.setResponsibleIdDisplay(rd==null||rd.equals("null")?null:rd);
						}
						if (jo.has("CityLeader")) {
							String cl = jo.getString("CityLeader");
							p.setCityLeader(cl==null||cl.equals("null")?null:cl);
						}
						if (jo.has("TownLeader")) {
							String tl = jo.getString("TownLeader");
							p.setTownLeader(tl==null||tl.equals("null")?null:tl);
						}
						if (jo.has("Referrer")) {
							String r = jo.getString("Referrer");
							p.setReferrer(r==null||r.equals("null")?null:r);
						}
						if (jo.has("Workers")) {
							String w = jo.getString("Workers");
							p.setWorkers(w==null||w.equals("null")?null:w);
						}
						if (jo.has("Workers_display")) {
							String wd = jo.getString("Workers_display");
							p.setWorkersDisplay(wd==null||wd.equals("null")?null:wd);
						}
						if (jo.has("CreateId")) {
							Integer cid = null;
							try {
								cid = jo.getInt("CreateId");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setCreatedId(cid);
						}
						if (jo.has("CreateId_display")) {
							String cd = jo
									.getString("CreateId_display");
							p.setCreateIdDisplay(cd==null||cd.equals("null")?null:cd);
						}
						if (jo.has("CreateDate")) {
							String cd = jo.getString("CreateDate");
							p.setCreateDate(cd==null||cd.equals("null")?null:cd);
						}
						if (jo.has("UpdateId")) {
							Integer uid = null;
							try {
								uid = jo.getInt("UpdateId");
							} catch (Exception e) {
								// e.printStackTrace();
							}
							p.setUpdateId(uid);
						}
						if (jo.has("UpdateId_display")) {
							String ud = jo
									.getString("UpdateId_display");
							p.setUpdateIdDisplay(ud==null||ud.equals("null")?null:ud);
						}
						if (jo.has("UpdateDate")) {
							String ud = jo.getString("UpdateDate");
							p.setUpdateDate(ud==null||ud.equals("null")?null:ud);
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
											// e.printStackTrace();
										}
										ir.setInvestorId(iid);
									}
									if (ji.has("ProjectId")) {
										int pid = -1;
										try {
											pid = ji.getInt("ProjectId");
										} catch (Exception e) {
											// e.printStackTrace();
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
											// e.printStackTrace();
										}
										c.setContactId(cid);
									}
									if (jc.has("ProjectId")) {
										int pid = -1;
										try {
											pid = jc.getInt("ProjectId");
										} catch (Exception e) {
											// e.printStackTrace();
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
