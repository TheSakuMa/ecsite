package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.MCategoryDAO;
import com.internousdev.ecsite2.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ItemCreateAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	public String execute() {
		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			ArrayList<MCategoryDTO> mCategoryList = new ArrayList<MCategoryDTO>();
			try {
				mCategoryList = mCategoryDAO.getMCategoryList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.put("mCategoryList", mCategoryList);
		}
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
