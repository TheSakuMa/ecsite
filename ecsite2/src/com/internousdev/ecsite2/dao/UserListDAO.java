package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite2.dto.UserInfoDTO;
import com.internousdev.ecsite2.util.DBConnector;

public class UserListDAO {

	public ArrayList<UserInfoDTO> getUserList() throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<UserInfoDTO> userInfoList = new ArrayList<UserInfoDTO>();

		String sql = "SELECT * FROM user_info";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserInfoDTO userInfoDTO = new UserInfoDTO();
				userInfoDTO.setId(rs.getString("id"));
				userInfoDTO.setLoginId(rs.getString("login_id"));
				userInfoDTO.setLoginPassword(rs.getString("login_pass"));
				userInfoDTO.setUserName(rs.getString("user_name"));
				userInfoDTO.setInsert_date(rs.getString("insert_date"));
				userInfoList.add(userInfoDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return userInfoList;
	}

}
