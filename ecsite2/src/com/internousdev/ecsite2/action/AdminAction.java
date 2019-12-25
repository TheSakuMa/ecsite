package com.internousdev.ecsite2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class AdminAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session; // 管理者ユーザーとしてログインしているという情報等をセッションに保持する
	public String execute() {
		String result = ERROR;
		if(session.containsKey("login_user_id") && "1".equals(session.get("admin_flg").toString())) {
			result = SUCCESS;
		}
		return result;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
