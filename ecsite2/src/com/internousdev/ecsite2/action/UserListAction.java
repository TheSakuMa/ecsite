package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite2.dao.UserListDAO;
import com.internousdev.ecsite2.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserListAction extends ActionSupport {
	private UserListDAO userListDAO = new UserListDAO();
	private ArrayList<UserInfoDTO> userInfoList = new ArrayList<UserInfoDTO>();

	public String execute() throws SQLException {
		String result = SUCCESS;
		userInfoList = userListDAO.getUserList();
		// ユーザー情報がない場合（つまり、userInfoListのサイズが0の場合）、userInfoList を nullにしてuserList.jspに渡す
		if(userInfoList.size() == 0) {
			userInfoList = null;
			// ユーザー情報がない旨のメッセージは、jsp側で struts2 のif文を利用して userInfoList = null の場合に表示させる
		}
		return result;
	}

	public ArrayList<UserInfoDTO> getUserInfoList() {
		return userInfoList;
	}
}
