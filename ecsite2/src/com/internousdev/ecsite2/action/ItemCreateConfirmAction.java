package com.internousdev.ecsite2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class ItemCreateConfirmAction extends ActionSupport implements SessionAware {
	private String itemName;
	private String itemDistributor;
	private String categoryName;
	private String price;
	private String stock;
	private Map<String, Object> session;
	private String errorMessage;

	public String execute() {
		String result = SUCCESS;
		if(!"1".equals(session.get("admin_flg").toString())) {
			return "login";
		}
		if(!(itemName.equals("")) && !(price.equals("")) && !(stock.equals("")) && !(categoryName.equals(""))) {
			session.put("itemName", itemName);
			session.put("itemDistributor", itemDistributor);
			session.put("categoryName", categoryName);
			session.put("price", price);
			session.put("stock", stock);
		} else {
			setErrorMessage("未入力の項目があります");
			result = ERROR;
		}
		return result;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setItemDistributor(String itemDistributor) {
		this.itemDistributor = itemDistributor;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
