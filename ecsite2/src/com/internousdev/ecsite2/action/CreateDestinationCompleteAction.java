package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.DestinationDAO;
import com.opensymphony.xwork2.ActionSupport;

public class CreateDestinationCompleteAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private DestinationDAO destinationDAO = new DestinationDAO();

	public String execute() {
		String result = ERROR;
		int insertResult = 0;
		String loginUserId;

		int loginedFlg = !session.containsKey("loginedFlg")? 0: Integer.parseInt(session.get("loginedFlg").toString());
		if(loginedFlg != 1) {
			return "login";
		} else {
			loginUserId = session.get("login_user_id").toString();
		}

		String familyName = session.get("familyName").toString();
		String firstName = session.get("firstName").toString();
		String phoneNumber = session.get("phoneNumber").toString();
		String address = session.get("address").toString();

		try {
			insertResult = destinationDAO.createDestination(loginUserId, familyName, firstName, phoneNumber, address);
			if(insertResult > 0) {
				result = SUCCESS;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return result;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
