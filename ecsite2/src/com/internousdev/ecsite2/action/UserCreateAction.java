package com.internousdev.ecsite2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class UserCreateAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private String userCreateByAdminFlg = null;
	public String execute() {
		// 管理者画面から遷移して、新規ユーザー登録が完了した場合に、管理者画面TOPに遷移させるためのフラグ
		if("1".equals(userCreateByAdminFlg)) {
			session.put("userCreateByAdminFlg", 1);
		}
		return SUCCESS;
	}

	public void setUserCreateByAdminFlg(String userCreateByAdminFlg) {
		this.userCreateByAdminFlg = userCreateByAdminFlg;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
