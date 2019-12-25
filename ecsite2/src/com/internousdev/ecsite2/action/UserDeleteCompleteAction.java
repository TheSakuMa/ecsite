package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.UserDeleteCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserDeleteCompleteAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private int id;
	int userCreateByAdminFlg = 0;
	private String message;

	public String execute() throws SQLException {
		String result = ERROR;
		int res = 0;
		String preUserCreateByAdminFlg = String.valueOf(session.get("userCreateByAdminFlg"));
		userCreateByAdminFlg = "null".equals(preUserCreateByAdminFlg)? 0: Integer.parseInt(preUserCreateByAdminFlg);
		session.remove("userCreateByAdminFlg");
		UserDeleteCompleteDAO userDeleteCompleteDAO = new UserDeleteCompleteDAO();
		// 削除した件数が返る
		res = userDeleteCompleteDAO.deleteUser(id);
		if(res > 0) {
			result = SUCCESS;
		} else {
			setMessage("削除に失敗しました。");
		}
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int setUserCreateByAdminFlg() {
		return userCreateByAdminFlg;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
