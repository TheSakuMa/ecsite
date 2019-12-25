package com.internousdev.ecsite2.action;

import java.sql.SQLException;

import com.internousdev.ecsite2.dao.ItemDeleteCompleteDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ItemDeleteCompleteAction extends ActionSupport {
	private int id;
	private ItemDeleteCompleteDAO itemDeleteCompleteDAO = new ItemDeleteCompleteDAO();
	private String message;

	public String execute() throws SQLException {
		String result = ERROR;
		int res = 0;
		res = itemDeleteCompleteDAO.deleteItem(id);
		if(res > 0) {
			result = SUCCESS;
		} else {
			setMessage("削除に失敗しました。");
		}
		return result;
	}

	public int getId() {
		return id;
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
}
