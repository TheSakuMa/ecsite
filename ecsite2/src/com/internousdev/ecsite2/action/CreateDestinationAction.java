package com.internousdev.ecsite2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;

	public String execute() {
		String result = SUCCESS;
		int loginedFlg = !session.containsKey("loginedFlg")? 0: Integer.parseInt(session.get("loginedFlg").toString());
		if(loginedFlg != 1) {
			return "login";
		}
		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
