package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite2.util.DBConnector;
import com.internousdev.ecsite2.util.DateUtil;

public class ItemCreateCompleteDAO {

	/**
	 *
	 * @param itemName
	 * @param itemDistributor
	 * @param price
	 * @param stock
	 * @param categoryId
	 * @throws SQLException
	 */
	public void createItem(String itemName, String itemDistributor, String price, String stock, String categoryId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();

		String sql = "INSERT INTO item_info (item_name, item_distributor, item_price, item_stock, category_id, insert_date) VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, itemName);
			ps.setString(2, itemDistributor);
			ps.setString(3, price);
			ps.setString(4, stock);
			ps.setString(5, categoryId);
			ps.setString(6, dateUtil.getDate());
			ps.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
	}

}
