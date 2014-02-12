package com.rugao.zhaoshang.beans;

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

	public String toJSONString() {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"Telephone\":").append("\"").append(this.telephone)
				.append("\",");
		sb.append("\"Memo\":").append("\"").append(this.memo).append("\",");
		sb.append("\"Role\":").append("\"").append(this.role).append("\",");
		sb.append("\"Phone\":").append("\"").append(this.phone).append("\",");
		sb.append("\"ProjectId\":").append("\"").append(this.projectId)
				.append("\",");
		sb.append("\"Name\":").append("\"").append(this.name).append("\",");
		sb.append("\"ContactId\":").append(this.contactId);
		sb.append("}");
		return sb.toString();
	}
}