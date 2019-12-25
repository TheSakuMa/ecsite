package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite2.util.DBConnector;


public class UserDeleteCompleteDAO {

	public int deleteUser(int id) throws SQLException { // 削除した件数を返す
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int res = 0;
		String sql = "DELETE FROM user_info WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

}
