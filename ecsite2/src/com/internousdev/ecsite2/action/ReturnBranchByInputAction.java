package com.internousdev.ecsite2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ReturnBranchByInputAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private String jspName;
	public String execute() {
		String result = ERROR;
		if(session.containsKey("jspName")) {
			jspName = session.get("jspName").toString();
			session.remove("jspName");
		}
		session.put("searchErrorMessage", "検索ワードに使用できる文字は、\n半角英数字・漢字・全角カタカナ・ひらがな・スペースです。");
		if("home.jsp".equals(jspName)) {
			return "home";
		}
		if("cart.jsp".equals(jspName)) {
			return "cart";
		}
		// header.jspを includeした jsp からの遷移であれば、
		// jsp名を設定しなくても、valueとして空文字が送られてくるので、isEmpty() メソッドを使用する
		if(jspName == null || jspName.isEmpty() || "buyItem.jsp".equals(jspName)) {
			return "buyItem";
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
