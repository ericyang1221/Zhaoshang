package com.rugao.zhaoshang.beans;

import java.net.URLEncoder;

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

	@SuppressWarnings("deprecation")
	public String toJSONString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"Status\":").append("\"")
				.append(URLEncoder.encode(this.status)).append("\",");
		sb.append("\"InvestorName\":").append("\"")
				.append(URLEncoder.encode(this.investorName)).append("\",");
		sb.append("\"ProjectId\":").append("\"").append(this.projectId)
				.append("\",");
		sb.append("\"MainProduct\":").append("\"")
				.append(URLEncoder.encode(this.mainProduct)).append("\",");
		sb.append("\"Address\":").append("\"")
				.append(URLEncoder.encode(this.address)).append("\",");
		sb.append("\"ProductMemo\":").append("\"")
				.append(URLEncoder.encode(this.productMemo)).append("\",");
		sb.append("\"InvestorId\":").append(this.investorId)
				.append("}");
		return sb.toString();
	}
}