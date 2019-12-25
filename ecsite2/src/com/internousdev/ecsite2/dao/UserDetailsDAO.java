package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite2.dto.UserDetailsDTO;
import com.internousdev.ecsite2.util.DBConnector;

public class UserDetailsDAO {
	public UserDetailsDTO getUserInfo(int id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		UserDetailsDTO userDetailsDTO = new UserDetailsDTO();

		String sql = "SELECT * FROM user_info WHERE id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userDetailsDTO.setId(rs.getInt("id"));
				userDetailsDTO.setLoginUserId(rs.getString("login_id"));
				userDetailsDTO.setLoginPassword(rs.getString("login_pass"));
				userDetailsDTO.setUserName(rs.getString("user_name"));
				userDetailsDTO.setInsert_date(rs.getString("insert_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return userDetailsDTO;
	}
}
