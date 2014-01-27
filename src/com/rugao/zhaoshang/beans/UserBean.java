package com.rugao.zhaoshang.beans;

import org.json.JSONException;
import org.json.JSONObject;

public class UserBean extends DataBean {
	private int userId;
	private String loginName;
	private String fullName;
	private String email;
	private String mobile;
	private String memo;
	private boolean isAdmin;
	private String createDate;
	private String lastLoginDate;
	private int positionId;
	private int deptId;

	public UserBean(JSONObject rs) throws JSONException {
		super(rs);
		if (resultData != null) {
			if (resultData.has("UserId")) {
				userId = resultData.getInt("UserId");
			}
			if (resultData.has("LoginName")) {
				loginName = resultData.getString("LoginName");
			}
			if (resultData.has("FullName")) {
				fullName = resultData.getString("FullName");
			}
			if (resultData.has("Email")) {
				email = resultData.getString("Email");
			}
			if (resultData.has("Mobile")) {
				mobile = resultData.getString("Mobile");
			}
			if (resultData.has("Memo")) {
				memo = resultData.getString("Memo");
			}
			if (resultData.has("IsAdmin")) {
				isAdmin = resultData.getBoolean("IsAdmin");
			}
			if (resultData.has("CreatedDate")) {
				createDate = resultData.getString("CreatedDate");
			}
			if (resultData.has("LastLoginDate")) {
				lastLoginDate = resultData.getString("LastLoginDate");
			}
			if (resultData.has("PositionId")) {
				try {
					positionId = resultData.getInt("PositionId");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (resultData.has("DeptId")) {
				try {
					deptId = resultData.getInt("DeptId");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public int getPositionId() {
		return positionId;
	}

	public void setPositionId(int positionId) {
		this.positionId = positionId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
}
