package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite2.util.DBConnector;

public class UserListDeleteCompleteDAO {
	public int deleteUserList() throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int res = 0;

		String sql = "DELETE FROM user_info WHERE admin_flg = '0'";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

}
