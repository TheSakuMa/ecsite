package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite2.dto.ItemDetailsDTO;
import com.internousdev.ecsite2.util.DBConnector;

public class ItemDetailsDAO {
	public ItemDetailsDTO getItemDetails(int id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ItemDetailsDTO itemDetailsDTO = new ItemDetailsDTO();

		String getItemInfoSQL = "SELECT * FROM item_info WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(getItemInfoSQL);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				itemDetailsDTO.setId(rs.getInt("id"));
				itemDetailsDTO.setItemName(rs.getString("item_name"));
				itemDetailsDTO.setCategoryId(rs.getInt("category_id"));
				itemDetailsDTO.setItemPrice(rs.getInt("item_price"));
				itemDetailsDTO.setItemStock(rs.getInt("item_stock"));
				itemDetailsDTO.setImagePath(rs.getString("image_path"));
				itemDetailsDTO.setImageName(rs.getString("image_name"));
				itemDetailsDTO.setItemDetail(rs.getString("item_detail"));
				itemDetailsDTO.setItemDistributor(rs.getString("item_distributor"));
				itemDetailsDTO.setInsert_date(rs.getString("insert_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return itemDetailsDTO;
	}

	/**
	 * getItemDetails メソッドで取得した商品情報のカテゴリーIDを元に、カテゴリー名を取得する
	 * @param categoryId
	 * @return String型の category_name
	 */
	public String getItemCategoryName(int categoryId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String resultCategoryName = null;

		String getCategoryNameSQL = "SELECT category_name FROM m_category WHERE category_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(getCategoryNameSQL);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				resultCategoryName = rs.getString("category_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return resultCategoryName;
	}

}
