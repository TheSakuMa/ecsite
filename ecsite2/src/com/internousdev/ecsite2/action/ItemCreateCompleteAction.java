package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.ItemCreateCompleteDAO;
import com.internousdev.ecsite2.dao.MCategoryDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ItemCreateCompleteAction extends ActionSupport implements SessionAware {
	private String itemName;
	private String price;
	private String stock;
	private Map<String, Object> session;
	private ItemCreateCompleteDAO itemCreateCompleteDAO = new ItemCreateCompleteDAO();
	private MCategoryDAO mCategogyDAO = new MCategoryDAO();

	public String execute() {
		String categoryId = null;
		try {
			categoryId = mCategogyDAO.getMCategoryId(String.valueOf(session.get("categoryName")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			itemCreateCompleteDAO.createItem(
				session.get("itemName").toString(),
				session.get("itemDistributor").toString(),
				session.get("price").toString(),
				session.get("stock").toString(),
				categoryId
			);
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String result = SUCCESS;
		return result;
	}

	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}

	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
