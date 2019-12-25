package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.ItemListDAO;
import com.internousdev.ecsite2.dao.MCategoryDAO;
import com.internousdev.ecsite2.dto.ItemInfoDTO;
import com.internousdev.ecsite2.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class HomeAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private ArrayList<ItemInfoDTO> itemInfoList = new ArrayList<ItemInfoDTO>();
	private String message;
	private String searchErrorMessage;

	public String execute() throws SQLException {
		String result = ERROR;

		// 検索エラーメッセージの受け渡し
		if(session.containsKey("searchErrorMessage")) {
			searchErrorMessage = session.get("searchErrorMessage").toString();
			session.remove("searchErrorMessage");
		}

		ItemListDAO itemListDAO = new ItemListDAO();
		itemInfoList = itemListDAO.getItemInfo();
		if(itemInfoList.size() > 0) {
			// セッションを利用しないと、success以外の場合に商品情報が保持されずに困ることになる！
			session.put("itemInfoList", itemInfoList);
			result = SUCCESS;
		} else {
			setMessage("商品情報がありません。");
		}

		// 商品検索のためのカテゴリー情報を取得し、セッションに格納
		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			ArrayList<MCategoryDTO> mCategoryList = new ArrayList<MCategoryDTO>();
			mCategoryList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryList", mCategoryList);
		}

		return result;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public ArrayList<ItemInfoDTO> getItemInfoList() {
		return itemInfoList;
	}

	public String getSearchErrorMessage() {
		return searchErrorMessage;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
