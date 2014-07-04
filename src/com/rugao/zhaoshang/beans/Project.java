package com.rugao.zhaoshang.beans;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class Project {
	private Integer projectId;
	private String projectName;
	private String planningDate;
	private Integer stageId;
	private String stageIdDisplay;
	private String industryType;
	private String industryDetail;
	private Integer policy;
	private String policyDisplay;
	private Integer enviroment;
	private String enviromentDisplay;
	private Double scale;
	private String scaleUnit;
	private String scaleUnitDisplay;
	private String projectMemo;
	private String landRequire;
	private String workRequire;
	private String buildTime;
	private Long totalAmount;
	private Double totalTax;
	private String projectPlan;
	private String casse;
	private String responsibler;
	private String cityLeader;
	private String townLeader;
	private String referrer;
	private String workers;
	private String workersDisplay;
	private Integer createdId;
	private String createIdDisplay;
	private String createDate;
	private Integer updateId;
	private String updateIdDisplay;
	private String updateDate;
	private int type;
	private String typeDisplay;
	private int status;
	private String statusDisplay;
	private String evaluater;
	private boolean isHasEvaluatRole;
	private List<Investor> investorList;
	private List<Contact> contactList;

	public Project() {
		casse = "";
		cityLeader = "";
		industryDetail = "";
		industryType = "";
		projectName = "";
		projectPlan = "";
		referrer = "";
		townLeader = "";
		workers = "";
		workersDisplay = "";
		contactList = new ArrayList<Contact>();
		investorList = new ArrayList<Investor>();
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
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

	public Integer getStageId() {
		return stageId;
	}

	public void setStageId(Integer stageId) {
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

	public Integer getPolicy() {
		return policy;
	}

	public void setPolicy(Integer policy) {
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

	public Long getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Long totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(Double totalTax) {
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

	public String getResponsibler() {
		return responsibler;
	}

	public void setResponsibler(String responsibler) {
		this.responsibler = responsibler;
	}

	public void setPolicyDisplay(String policyDisplay) {
		this.policyDisplay = policyDisplay;
	}

	public Integer getEnviroment() {
		return enviroment;
	}

	public void setEnviroment(Integer enviroment) {
		this.enviroment = enviroment;
	}

	public String getEnviromentDisplay() {
		return enviromentDisplay;
	}

	public void setEnviromentDisplay(String enviromentDisplay) {
		this.enviromentDisplay = enviromentDisplay;
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
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

	public Integer getCreatedId() {
		return createdId;
	}

	public void setCreatedId(Integer createdId) {
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

	public Integer getUpdateId() {
		return updateId;
	}

	public void setUpdateId(Integer updateId) {
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getTypeDisplay() {
		return typeDisplay;
	}

	public void setTypeDisplay(String typeDisplay) {
		this.typeDisplay = typeDisplay;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getStatusDisplay() {
		return statusDisplay;
	}

	public void setStatusDisplay(String statusDisplay) {
		this.statusDisplay = statusDisplay;
	}

	public String getEvaluater() {
		return evaluater;
	}

	public void setEvaluater(String evaluater) {
		this.evaluater = evaluater;
	}

	public boolean isHasEvaluatRole() {
		return isHasEvaluatRole;
	}

	public void setHasEvaluatRole(boolean isHasEvaluatRole) {
		this.isHasEvaluatRole = isHasEvaluatRole;
	}

	public String getInvestorsDisplay() {
		StringBuffer sb = new StringBuffer();
		if (investorList != null) {
			for (Investor i : investorList) {
				sb.append(i.getInvestorName()).append(",");
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		return sb.toString();
	}

	public String getContactsDisplay() {
		StringBuffer sb = new StringBuffer();
		if (contactList != null) {
			for (Contact c : contactList) {
				sb.append(c.getName()).append(",");
			}
			if (sb.length() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
		}
		return sb.toString();
	}

	public List<NameValuePair> getPostParams(String userId, String memo,
			boolean isAdd) {
		List<NameValuePair> p = new ArrayList<NameValuePair>();
		p.add(new BasicNameValuePair("Memo", memo));
		p.add(new BasicNameValuePair("UserId", String.valueOf(userId)));
		if (isAdd) {
			// p.add(new BasicNameValuePair("ProjectId",
			// String.valueOf(projectId)));
		} else {
			p.add(new BasicNameValuePair("ProjectId", String.valueOf(projectId)));
		}
		p.add(new BasicNameValuePair("BuildTime", buildTime == null ? ""
				: buildTime));
		p.add(new BasicNameValuePair("Case", this.casse));
		p.add(new BasicNameValuePair("CityLeader", this.cityLeader));
		// p.add(new BasicNameValuePair("CreateDate", createDate == null ? ""
		// : createDate));
		// p.add(new BasicNameValuePair("CreateId", String.valueOf(createdId)));
		p.add(new BasicNameValuePair("Enviroment", String.valueOf(enviroment)));
		p.add(new BasicNameValuePair("IndustryDetail", this.industryDetail));
		p.add(new BasicNameValuePair("IndustryType", this.industryType));
		p.add(new BasicNameValuePair("LandRequire", landRequire == null ? ""
				: landRequire));
		p.add(new BasicNameValuePair("PlanningDate", planningDate == null ? ""
				: planningDate));
		p.add(new BasicNameValuePair("Policy", String.valueOf(policy)));
		p.add(new BasicNameValuePair("PrjMemo", projectMemo == null ? ""
				: projectMemo));
		p.add(new BasicNameValuePair("PrjName", this.projectName));
		p.add(new BasicNameValuePair("PrjPlan", this.projectPlan));
		p.add(new BasicNameValuePair("Referrer", this.referrer));
		p.add(new BasicNameValuePair("Responsibler", this.responsibler));
		p.add(new BasicNameValuePair("Scale", String.valueOf(scale)));
		p.add(new BasicNameValuePair("ScaleUnit", String.valueOf(scaleUnit)));
		p.add(new BasicNameValuePair("StageId", String.valueOf(stageId)));
		p.add(new BasicNameValuePair("TotalAmount", String.valueOf(totalAmount)));
		p.add(new BasicNameValuePair("TotalTax", String.valueOf(totalTax)));
		p.add(new BasicNameValuePair("TownLeader", this.townLeader));
		// p.add(new BasicNameValuePair("UpdateDate", updateDate == null ? ""
		// : updateDate));
		// p.add(new BasicNameValuePair("UpdateId", String.valueOf(updateId)));
		p.add(new BasicNameValuePair("Workers", workers == null ? "" : workers));
		p.add(new BasicNameValuePair("WorkersTable", workers == null ? ""
				: workers));
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		if (contactList != null) {
			for (int i = 0; i < contactList.size(); i++) {
				if (i == contactList.size() - 1) {
					sb.append(contactList.get(i).toJSONString());
				} else {
					sb.append(contactList.get(i).toJSONString()).append(",");
				}
			}
		}
		sb.append("]");
		p.add(new BasicNameValuePair("ContactsTable", sb.toString()));
		sb.setLength(0);
		sb.append("[");
		if (investorList != null) {
			for (int i = 0; i < this.investorList.size(); i++) {
				if (i == investorList.size() - 1) {
					sb.append(investorList.get(i).toJSONString());
				} else {
					sb.append(investorList.get(i).toJSONString()).append(",");
				}
			}
		}
		sb.append("]");
		p.add(new BasicNameValuePair("InvestorTable", sb.toString()));
		System.out.println(p.toString());
		return p;
	}
}
