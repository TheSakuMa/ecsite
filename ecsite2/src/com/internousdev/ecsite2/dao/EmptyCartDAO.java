package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite2.util.DBConnector;

public class EmptyCartDAO {

	public int emptyCartAll(String login_user_id) {
		int res = 0;

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "DELETE FROM cart WHERE user_master_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login_user_id);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}

	public int emptyCartEach(String itemId, String login_user_id) {
		int res = 0;

		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "DELETE FROM cart WHERE item_id = ? AND user_master_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, itemId);
			ps.setString(2, login_user_id);
			res = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return res;
	}
}
