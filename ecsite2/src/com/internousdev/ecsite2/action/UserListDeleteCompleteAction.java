package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite2.dao.UserListDAO;
import com.internousdev.ecsite2.dao.UserListDeleteCompleteDAO;
import com.internousdev.ecsite2.dto.UserInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class UserListDeleteCompleteAction extends ActionSupport {

	private UserListDeleteCompleteDAO userListDeleteCompleteDAO = new UserListDeleteCompleteDAO();
	private String message;
	private ArrayList<UserInfoDTO> userInfoList = new ArrayList<UserInfoDTO>();
	private UserListDAO userListDAO = new UserListDAO();

	public String execute() throws SQLException {
		String result = ERROR;
		int res = userListDeleteCompleteDAO.deleteUserList();
		if(res > 0) {
			result = SUCCESS;
		} else {
			// 削除に失敗した場合、userList.jsp に遷移する。
			// 管理者ユーザーは削除不可なので、削除に失敗したということになるが、
			// 管理者ユーザーの削除を試みて失敗した場合にも、
			// 管理者ユーザーの情報として userInfoList をuserList.jspに渡してあげる必要がある。
			userInfoList = userListDAO.getUserList();
			setMessage("削除に失敗しました。");
		}
		return result;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<UserInfoDTO> getUserInfoList() {
		return userInfoList;
	}
	public void setUserInfoList(ArrayList<UserInfoDTO> userInfoList) {
		this.userInfoList = userInfoList;
	}
}
