package com.internousdev.ecsite2.action;

import java.sql.SQLException;

// import com.internousdev.ecsite2.dao.ItemListDAO;
import com.internousdev.ecsite2.dao.ItemListDeleteCompleteDAO;
// import com.internousdev.ecsite2.dto.ItemInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ItemListDeleteCompleteAction extends ActionSupport {
	private String message;
	// private ItemListDAO itemListDAO = new ItemListDAO();
	private ItemListDeleteCompleteDAO itemListDeleteCompleteDAO = new ItemListDeleteCompleteDAO();
	// private ArrayList<ItemInfoDTO> itemInfoList = new ArrayList<ItemInfoDTO>();
	public String execute() throws SQLException {
		String result = ERROR;
		// itemInfoList = itemListDAO.getItemInfo();
		int res = itemListDeleteCompleteDAO.deleteItemList();
		if(res > 0) {
			// itemInfoList  = null;
			result = SUCCESS;
		} else {
			setMessage("削除に失敗しました");
		}
		return result;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

//	public ArrayList<ItemInfoDTO> getItemInfoList() {
//		return itemInfoList;
//	}
//	public void setItemInfoList(ArrayList<ItemInfoDTO> itemInfoList) {
//		this.itemInfoList = itemInfoList;
//	}
}
