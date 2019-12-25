package com.internousdev.ecsite2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationConfirmAction extends ActionSupport implements SessionAware {
	private String loginUserId;
	private String familyName;
	private String firstName;
	private String phoneNumber;
	private String address;
	private Map<String, Object> session;

	public String execute() {
		String result = SUCCESS;

		int loginedFlg = !session.containsKey("loginedFlg")? 0: Integer.parseInt(session.get("loginedFlg").toString());
		if(loginedFlg != 1) {
			return "login";
		} else {
			loginUserId = session.get("login_user_id").toString();
		}

		session.put("familyName", familyName);
		session.put("firstName", firstName);
		session.put("phoneNumber", phoneNumber);
		session.put("address", address);

		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
