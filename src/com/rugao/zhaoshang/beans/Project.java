package com.rugao.zhaoshang.beans;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class Project {
	private final String TAG = "Project";
	
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

	@SuppressWarnings("deprecation")
	public List<NameValuePair> getPostParams(String userId, String memo,
			boolean isAdd) {
		List<NameValuePair> p = new ArrayList<NameValuePair>();
		p.add(new BasicNameValuePair("UserId", String.valueOf(userId)));
		p.add(new BasicNameValuePair("Memo", memo));
		if (isAdd) {
			p.add(new BasicNameValuePair("ProjectId", String.valueOf(projectId)));
		}
		Log.d(TAG, "UserId=" + userId + "  Memo=" + memo + "  ProjectId="
				+ projectId);
		p.add(new BasicNameValuePair("BuildTime", buildTime));
		p.add(new BasicNameValuePair("Case", URLEncoder.encode(this.casse)));
		p.add(new BasicNameValuePair("CityLeader", URLEncoder
				.encode(this.cityLeader)));
		p.add(new BasicNameValuePair("CreateDate", createDate));
		p.add(new BasicNameValuePair("CreateId", String.valueOf(createdId)));
		p.add(new BasicNameValuePair("Enviroment", String.valueOf(enviroment)));
		p.add(new BasicNameValuePair("IndustryDetail", URLEncoder
				.encode(this.industryDetail)));
		p.add(new BasicNameValuePair("IndustryType", URLEncoder
				.encode(this.industryType)));
		p.add(new BasicNameValuePair("LandRequire", landRequire));
		p.add(new BasicNameValuePair("PlanningDate", planningDate));
		p.add(new BasicNameValuePair("Policy", String.valueOf(policy)));
		p.add(new BasicNameValuePair("PrjMemo", projectMemo));
		p.add(new BasicNameValuePair("PrjName", URLEncoder
				.encode(this.projectName)));
		p.add(new BasicNameValuePair("PrjPlan", URLEncoder
				.encode(this.projectPlan)));
		p.add(new BasicNameValuePair("Referrer", URLEncoder
				.encode(this.referrer)));
		p.add(new BasicNameValuePair("ResponsibleId", String
				.valueOf(responsibleId)));
		p.add(new BasicNameValuePair("Scale", String.valueOf(scale)));
		p.add(new BasicNameValuePair("ScaleUnit", String.valueOf(scaleUnit)));
		p.add(new BasicNameValuePair("StageId", String.valueOf(stageId)));
		p.add(new BasicNameValuePair("TotalAmount", String.valueOf(totalAmount)));
		p.add(new BasicNameValuePair("TotalTax", String.valueOf(totalTax)));
		p.add(new BasicNameValuePair("TownLeader", URLEncoder
				.encode(this.townLeader)));
		p.add(new BasicNameValuePair("UpdateDate", updateDate));
		p.add(new BasicNameValuePair("UpdateId", String.valueOf(updateId)));
		p.add(new BasicNameValuePair("Workers", workers));
		p.add(new BasicNameValuePair("WorkersTable", workers));
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (int i = 0; i < contactList.size(); i++) {
			if (i == contactList.size() - 1) {
				sb.append(contactList.get(i).toJSONString());
			} else {
				sb.append(contactList.get(i).toJSONString()).append(",");
			}
		}
		sb.append("]");
		p.add(new BasicNameValuePair("ContactsTable", sb.toString()));
		sb.setLength(0);
		sb.append("[");
		for (int i = 0; i < this.investorList.size(); i++) {
			if (i == investorList.size() - 1) {
				sb.append(investorList.get(i).toJSONString());
			} else {
				sb.append(investorList.get(i).toJSONString()).append(",");
			}
		}
		sb.append("]");
		p.add(new BasicNameValuePair("InvestorTable", sb.toString()));
		return p;
	}

	@SuppressWarnings("deprecation")
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("BuildTime=").append(buildTime).append("&");
		sb.append("Case=").append(URLEncoder.encode(this.casse)).append("&");
		sb.append("CityLeader=").append(URLEncoder.encode(this.cityLeader))
				.append("&");
		sb.append("CreateDate=").append(this.createDate).append("&");
		sb.append("CreateId=").append(this.createdId).append("&");
		sb.append("Enviroment=").append(this.enviroment).append("&");
		sb.append("IndustryDetail=")
				.append(URLEncoder.encode(this.industryDetail)).append("&");
		sb.append("IndustryType=").append(URLEncoder.encode(this.industryType))
				.append("&");
		sb.append("LandRequire=").append(this.landRequire).append("&");
		sb.append("PlanningDate=").append(this.planningDate).append("&");
		sb.append("Policy=").append(this.policy).append("&");
		sb.append("PrjMemo=").append(this.projectMemo).append("&");
		sb.append("PrjName=").append(URLEncoder.encode(this.projectName))
				.append("&");
		sb.append("PrjPlan=").append(URLEncoder.encode(this.projectPlan))
				.append("&");
		sb.append("Referrer=").append(URLEncoder.encode(this.referrer))
				.append("&");
		sb.append("ResponsibleId=").append(this.responsibleId).append("&");
		sb.append("Scale=").append(this.scale).append("&");
		sb.append("ScaleUnit=").append(this.scaleUnit).append("&");
		sb.append("StageId=").append(this.stageId).append("&");
		sb.append("TotalAmount=").append(this.totalAmount).append("&");
		sb.append("TotalTax=").append(this.totalTax).append("&");
		sb.append("TownLeader=").append(URLEncoder.encode(this.townLeader))
				.append("&");
		sb.append("UpdateDate=").append(this.updateDate).append("&");
		sb.append("UpdateId=").append(this.updateId).append("&");
		sb.append("Workers=").append(this.workers).append("&");
		sb.append("WorkersTable=").append(this.workers).append("&");
		sb.append("ContactsTable=").append("[");
		for (int i = 0; i < contactList.size(); i++) {
			if (i == contactList.size() - 1) {
				sb.append(contactList.get(i).toJSONString());
			} else {
				sb.append(contactList.get(i).toJSONString()).append(",");
			}
		}
		sb.append("]").append("&");
		sb.append("InvestorTable=").append("[");
		for (int i = 0; i < this.investorList.size(); i++) {
			if (i == investorList.size() - 1) {
				sb.append(investorList.get(i).toJSONString());
			} else {
				sb.append(investorList.get(i).toJSONString()).append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
}
