package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite2.util.DBConnector;

public class ResetPasswordDAO {
	public boolean resetPassword(String loginPassword, String newLoginPassword) throws SQLException {
		boolean result = false;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		int res = 0;

		String sql = "UPDATE user_info SET login_pass = ? WHERE login_pass = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, newLoginPassword);
			ps.setString(2, loginPassword);
			res = ps.executeUpdate();
			if(res > 0) {
				result = true;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return result;
	}
}
