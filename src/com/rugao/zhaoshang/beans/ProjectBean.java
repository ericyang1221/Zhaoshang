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

	public class Project {
		private int projectId;
		private String projectName;
		private String planningDate;
		private int stageId;
		private String stageIdDisplay;
		private String industryType;
		private String industryDetail;
		private int policy;
		private String policyDisplay;
		private int enviroment;
		private String enviromentDisplay;
		private double scale;
		private String scaleUnit;
		private String scaleUnitDisplay;
		private String projectMemo;
		private String landRequire;
		private String workRequire;
		private String buildTime;
		private long totalAmount;
		private double totalTax;
		private String projectPlan;
		private String casse;
		private int responsibleId;
		private String responsibleIdDisplay;
		private String cityLeader;
		private String townLeader;
		private String referrer;
		private String workers;
		private String workersDisplay;
		private int createdId;
		private String createIdDisplay;
		private String createDate;
		private int updateId;
		private String updateIdDisplay;
		private String updateDate;
		private List<Investor> investorList;
		private List<Contact> contactList;

		public int getProjectId() {
			return projectId;
		}

		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}

		public String getProjectName() {
			return projectName;
		}

		public void setProjectName(String projectName) {
			this.projectName = projectName;
		}

		public String getPlanningDate() {
			return planningDate;
		}

		public void setPlanningDate(String planningDate) {
			this.planningDate = planningDate;
		}

		public int getStageId() {
			return stageId;
		}

		public void setStageId(int stageId) {
			this.stageId = stageId;
		}

		public String getStageIdDisplay() {
			return stageIdDisplay;
		}

		public void setStageIdDisplay(String stageIdDisplay) {
			this.stageIdDisplay = stageIdDisplay;
		}

		public String getIndustryType() {
			return industryType;
		}

		public void setIndustryType(String industryType) {
			this.industryType = industryType;
		}

		public String getIndustryDetail() {
			return industryDetail;
		}

		public void setIndustryDetail(String industryDetail) {
			this.industryDetail = industryDetail;
		}

		public int getPolicy() {
			return policy;
		}

		public void setPolicy(int policy) {
			this.policy = policy;
		}

		public String getPolicyDisplay() {
			return policyDisplay;
		}

		public String getProjectMemo() {
			return projectMemo;
		}

		public void setProjectMemo(String projectMemo) {
			this.projectMemo = projectMemo;
		}

		public String getLandRequire() {
			return landRequire;
		}

		public void setLandRequire(String landRequire) {
			this.landRequire = landRequire;
		}

		public String getWorkRequire() {
			return workRequire;
		}

		public void setWorkRequire(String workRequire) {
			this.workRequire = workRequire;
		}

		public String getBuildTime() {
			return buildTime;
		}

		public void setBuildTime(String buildTime) {
			this.buildTime = buildTime;
		}

		public long getTotalAmount() {
			return totalAmount;
		}

		public void setTotalAmount(long totalAmount) {
			this.totalAmount = totalAmount;
		}

		public double getTotalTax() {
			return totalTax;
		}

		public void setTotalTax(double totalTax) {
			this.totalTax = totalTax;
		}

		public String getProjectPlan() {
			return projectPlan;
		}

		public void setProjectPlan(String projectPlan) {
			this.projectPlan = projectPlan;
		}

		public String getCase() {
			return casse;
		}

		public void setCase(String casse) {
			this.casse = casse;
		}

		public int getResponsibleId() {
			return responsibleId;
		}

		public void setResponsibleId(int responsibleId) {
			this.responsibleId = responsibleId;
		}

		public String getResponsibleIdDisplay() {
			return responsibleIdDisplay;
		}

		public void setResponsibleIdDisplay(String responsibleIdDisplay) {
			this.responsibleIdDisplay = responsibleIdDisplay;
		}

		public void setPolicyDisplay(String policyDisplay) {
			this.policyDisplay = policyDisplay;
		}

		public int getEnviroment() {
			return enviroment;
		}

		public void setEnviroment(int enviroment) {
			this.enviroment = enviroment;
		}

		public String getEnviromentDisplay() {
			return enviromentDisplay;
		}

		public void setEnviromentDisplay(String enviromentDisplay) {
			this.enviromentDisplay = enviromentDisplay;
		}

		public double getScale() {
			return scale;
		}

		public void setScale(double scale) {
			this.scale = scale;
		}

		public String getScaleUnit() {
			return scaleUnit;
		}

		public void setScaleUnit(String scaleUnit) {
			this.scaleUnit = scaleUnit;
		}

		public String getScaleUnitDisplay() {
			return scaleUnitDisplay;
		}

		public void setScaleUnitDisplay(String scaleUnitDisplay) {
			this.scaleUnitDisplay = scaleUnitDisplay;
		}

		public String getCityLeader() {
			return cityLeader;
		}

		public void setCityLeader(String cityLeader) {
			this.cityLeader = cityLeader;
		}

		public String getTownLeader() {
			return townLeader;
		}

		public void setTownLeader(String townLeader) {
			this.townLeader = townLeader;
		}

		public String getReferrer() {
			return referrer;
		}

		public void setReferrer(String referrer) {
			this.referrer = referrer;
		}

		public String getWorkers() {
			return workers;
		}

		public void setWorkers(String workers) {
			this.workers = workers;
		}

		public String getWorkersDisplay() {
			return workersDisplay;
		}

		public void setWorkersDisplay(String workersDisplay) {
			this.workersDisplay = workersDisplay;
		}

		public int getCreatedId() {
			return createdId;
		}

		public void setCreatedId(int createdId) {
			this.createdId = createdId;
		}

		public String getCreateIdDisplay() {
			return createIdDisplay;
		}

		public void setCreateIdDisplay(String createIdDisplay) {
			this.createIdDisplay = createIdDisplay;
		}

		public String getCreateDate() {
			return createDate;
		}

		public void setCreateDate(String createDate) {
			this.createDate = createDate;
		}

		public int getUpdateId() {
			return updateId;
		}

		public void setUpdateId(int updateId) {
			this.updateId = updateId;
		}

		public String getUpdateIdDisplay() {
			return updateIdDisplay;
		}

		public void setUpdateIdDisplay(String updateIdDisplay) {
			this.updateIdDisplay = updateIdDisplay;
		}

		public String getUpdateDate() {
			return updateDate;
		}

		public void setUpdateDate(String updateDate) {
			this.updateDate = updateDate;
		}

		public List<Investor> getInvestors() {
			return investorList;
		}

		public void setInvestors(List<Investor> investorList) {
			this.investorList = investorList;
		}

		public List<Contact> getContacts() {
			return contactList;
		}

		public void setContacts(List<Contact> contactList) {
			this.contactList = contactList;
		}

	}

	public class Investor {
		private int investorId;
		private int projectId;
		private String investorName;
		private String address;
		private String status;
		private String mainProduct;
		private String productMemo;
		private String memo;

		public int getInvestorId() {
			return investorId;
		}

		public void setInvestorId(int investorId) {
			this.investorId = investorId;
		}

		public int getProjectId() {
			return projectId;
		}

		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}

		public String getInvestorName() {
			return investorName;
		}

		public void setInvestorName(String investorName) {
			this.investorName = investorName;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getMainProduct() {
			return mainProduct;
		}

		public void setMainProduct(String mainProduct) {
			this.mainProduct = mainProduct;
		}

		public String getProductMemo() {
			return productMemo;
		}

		public void setProductMemo(String productMemo) {
			this.productMemo = productMemo;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}
	}

	public class Contact {
		private int contactId;
		private int projectId;
		private String name;
		private String role;
		private String telephone;
		private String phone;
		private String memo;

		public int getContactId() {
			return contactId;
		}

		public void setContactId(int contactId) {
			this.contactId = contactId;
		}

		public int getProjectId() {
			return projectId;
		}

		public void setProjectId(int projectId) {
			this.projectId = projectId;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getRole() {
			return role;
		}

		public void setRole(String role) {
			this.role = role;
		}

		public String getTelephone() {
			return telephone;
		}

		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getMemo() {
			return memo;
		}

		public void setMemo(String memo) {
			this.memo = memo;
		}
	}
}
