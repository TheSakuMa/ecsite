package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite2.dto.ItemInfoDTO;
import com.internousdev.ecsite2.util.DBConnector;

public class ItemListDAO {

	public ArrayList<ItemInfoDTO> getItemInfo() throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<ItemInfoDTO> itemInfoList = new ArrayList<ItemInfoDTO>();

		String sql = "SELECT * FROM item_info WHERE delete_date IS NULL";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ItemInfoDTO itemInfoDTO = new ItemInfoDTO();
				itemInfoDTO.setId(rs.getInt("id"));
				itemInfoDTO.setItemName(rs.getString("item_name"));
				itemInfoDTO.setPrice(rs.getString("item_price"));
				itemInfoDTO.setStock(rs.getString("item_stock"));
				itemInfoDTO.setImage_path(rs.getString("image_path"));
				itemInfoDTO.setImage_name(rs.getString("image_name"));
				itemInfoDTO.setInsert_date(rs.getString("insert_date"));
				itemInfoList.add(itemInfoDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return itemInfoList;
	}

	public ArrayList<ItemInfoDTO> getItemInfoByKeywords(String[] keywordsList, String itemSort) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<ItemInfoDTO> itemInfoList = new ArrayList<ItemInfoDTO>();

		String sql = "SELECT * FROM item_info WHERE delete_date IS NULL";
		// 1つ目のキーワードかどうかを分岐するためのフラグ
		boolean initialFlag = true;
		// ""（空文字）の場合は、検索ワードが入力されていないと同義（SearchItemAction で処理済のため）
		if(!keywordsList[0].equals("")) {
			for(String keywords: keywordsList) {
				if(initialFlag) {
					sql += " AND ((item_name like '%" + keywords + "%')";
					initialFlag = false;
				} else {
					sql += " OR (item_name like '%" + keywords + "%')";
				}
			}
			sql += ")";
		}
		if("ID順".equals(itemSort)) {
			sql += " ORDER BY id asc";
		} else if("値段が高い順".equals(itemSort)) {
			sql += " ORDER BY item_price desc";
		} else if("値段が安い順".equals(itemSort)) {
			sql += " ORDER BY item_price asc";
		}

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ItemInfoDTO itemInfoDTO = new ItemInfoDTO();
				itemInfoDTO.setId(rs.getInt("id"));
				itemInfoDTO.setItemName(rs.getString("item_name"));
				itemInfoDTO.setPrice(rs.getString("item_price"));
				itemInfoDTO.setStock(rs.getString("item_stock"));
				itemInfoDTO.setImage_path(rs.getString("image_path"));
				itemInfoDTO.setImage_name(rs.getString("image_name"));
				itemInfoDTO.setInsert_date(rs.getString("insert_date"));
				itemInfoList.add(itemInfoDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return itemInfoList;
	}

	public ArrayList<ItemInfoDTO> getItemInfoByKeywordsANDCategoryId(String[] keywordsList, String categoryId, String itemSort) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<ItemInfoDTO> itemInfoList = new ArrayList<ItemInfoDTO>();

		String sql = "SELECT * FROM item_info WHERE category_id = ? AND delete_date IS NULL";

		boolean initialFlag = true;

		if(!keywordsList[0].equals("")) {
			for(String keywords: keywordsList) {
				if(initialFlag) {
					sql += " AND (item_name like '%"+ keywords +"%'";
					initialFlag = false;
				} else {
					sql += " OR item_name like '%" + keywords  +"%'";
				}
			}
			sql += ")";
		}
		if("ID順".equals(itemSort)) {
			sql += " ORDER BY id asc";
		} else if("値段が高い順".equals(itemSort)) {
			sql += " ORDER BY item_price desc";
		} else if("値段が安い順".equals(itemSort)) {
			sql += " ORDER BY item_price asc";
		}

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoryId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				ItemInfoDTO itemInfoDTO = new ItemInfoDTO();
				itemInfoDTO.setId(rs.getInt("id"));
				itemInfoDTO.setItemName(rs.getString("item_name"));
				itemInfoDTO.setPrice(rs.getString("item_price"));
				itemInfoDTO.setStock(rs.getString("item_stock"));
				itemInfoDTO.setImage_path(rs.getString("image_path"));
				itemInfoDTO.setImage_name(rs.getString("image_name"));
				itemInfoDTO.setInsert_date(rs.getString("insert_date"));
				itemInfoList.add(itemInfoDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return itemInfoList;
	}

}
