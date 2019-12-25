package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite2.dao.ItemListDAO;
import com.internousdev.ecsite2.dto.ItemInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class ItemListAction extends ActionSupport {
	private ItemListDAO itemListDAO = new ItemListDAO();
	private ArrayList<ItemInfoDTO> itemInfoList = new ArrayList<ItemInfoDTO>();
	public String execute() throws SQLException {
		itemInfoList = itemListDAO.getItemInfo();
		// 商品情報がない場合（つまり、itemInfoListのサイズが0の場合）、
		// itemInfoList を nullにしてitemList.jspに渡す
		if(itemInfoList.size() == 0) {
			itemInfoList = null;
			// 商品情報がない旨のメッセージは、jsp側で struts2 のif文を利用して itemList = null の場合に表示させる
		}
		String result = SUCCESS;
		return result;
	}

	public ArrayList<ItemInfoDTO> getItemInfoList() {
		return itemInfoList;
	}

}


