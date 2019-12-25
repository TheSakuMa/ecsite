package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite2.util.DBConnector;
import com.internousdev.ecsite2.util.DateUtil;

public class BuyItemCompleteDAO {

	public int buyItemInfo(int item_transaction_id, int total_price, int total_count, String user_master_id, String pay) throws SQLException {
		int res = 0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();

		String insert = "INSERT INTO user_buy_item_transaction (item_transaction_id, total_price, total_count, user_master_id, pay, insert_date) VALUES(?,?,?,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(insert);
			ps.setInt(1, item_transaction_id);
			ps.setInt(2, total_price);
			ps.setInt(3, total_count);
			ps.setString(4, user_master_id);
			ps.setString(5, pay);
			ps.setString(6, dateUtil.getDate());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

	public int updateItemStock(int total_count, int item_transaction_id) throws SQLException {
		int res = 0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String update = "UPDATE item_info SET item_stock = (item_stock - ?) WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(update);
			ps.setInt(1, total_count);
			ps.setInt(2, item_transaction_id);
			res = ps.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

	public int deleteCartItemafterBuy(String user_master_id, int item_transaction_id) throws SQLException {
		int res = 0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		String empty = "DELETE FROM cart WHERE user_master_id = ? AND item_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(empty);
			ps.setString(1, user_master_id);
			ps.setInt(2, item_transaction_id);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

}
