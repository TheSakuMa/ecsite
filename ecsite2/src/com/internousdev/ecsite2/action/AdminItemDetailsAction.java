package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.ItemDetailsDAO;
import com.internousdev.ecsite2.dto.ItemDetailsDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AdminItemDetailsAction extends ActionSupport implements SessionAware {
	private int id;
	private String categoryName;
	private Map<String, Object> session;

	public String execute() throws SQLException {
		String result = SUCCESS;
		ItemDetailsDAO itemDetailsDAO = new ItemDetailsDAO();
		ItemDetailsDTO itemDetailsDTO = itemDetailsDAO.getItemDetails(id);
		session.put("id", itemDetailsDTO.getId());
		session.put("item_name", itemDetailsDTO.getItemName());
		session.put("item_price", itemDetailsDTO.getItemPrice());
		session.put("item_stock", itemDetailsDTO.getItemStock());
		session.put("image_path", itemDetailsDTO.getImagePath());
		session.put("image_name", itemDetailsDTO.getImageName());
		session.put("item_detail", itemDetailsDTO.getItemDetail());
		session.put("item_distributor", itemDetailsDTO.getItemDistributor());
		session.put("insert_date", itemDetailsDTO.getInsert_date());

		int categoryId = itemDetailsDTO.getCategoryId();

		categoryName = itemDetailsDAO.getItemCategoryName(categoryId);
		session.put("categoryName", categoryName);

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
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
