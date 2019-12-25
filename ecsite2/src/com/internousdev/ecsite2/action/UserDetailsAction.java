package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.UserDetailsDAO;
import com.internousdev.ecsite2.dto.UserDetailsDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserDetailsAction extends ActionSupport implements SessionAware {
	private int id;
	private Map<String, Object> session;

	public String execute() throws SQLException {
		String result = SUCCESS;
		UserDetailsDAO userDetailsDAO = new UserDetailsDAO();
		UserDetailsDTO userDetailsDTO = userDetailsDAO.getUserInfo(id);
		session.put("uid", userDetailsDTO.getId());
		session.put("login_id", userDetailsDTO.getLoginUserId());
		session.put("login_pass", userDetailsDTO.getLoginPassword());
		session.put("user_name", userDetailsDTO.getUserName());
		session.put("insert_date", userDetailsDTO.getInsert_date());
		return result;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
