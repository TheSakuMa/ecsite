package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite2.dto.LoginDTO;
import com.internousdev.ecsite2.util.DBConnector;

public class LoginDAO {

	/**
	 * ログインIDとログインPASS で該当ユーザーが存在するかを判定する。
	 * ログイン処理とパスワード再設定処理で使用している。
	 * @param loginUserId
	 * @param loginPassword
	 * @return 一致するデータがある場合は true、ない場合は false
	 */
	public boolean isExistsUserInfo(String loginUserId, String loginPassword) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		boolean result = false;

		String sql = "SELECT COUNT(*) as count FROM user_info WHERE login_id = ? AND login_pass = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUserId);
			ps.setString(2, loginPassword);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				if(rs.getInt("count") > 0) {
					result = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 *  ログインIDとログインPASS で一致するユーザー情報を取得する。
	 * @param loginUserId
	 * @param loginPassword
	 * @return 該当ユーザー情報が格納された LoginDTO 型
	 */
	public LoginDTO getLoginUserInfo(String loginUserId, String loginPassword) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		LoginDTO loginDTO = new LoginDTO();

		String sql = "SELECT * FROM user_info WHERE login_id = ? AND login_pass = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUserId);
			ps.setString(2, loginPassword);

			ResultSet rs = ps.executeQuery();

			if(rs.next()) {
				loginDTO.setLoginId(rs.getString("login_id"));
				loginDTO.setLoginPassword(rs.getString("login_pass"));
				loginDTO.setUserName(rs.getString("user_name"));
				loginDTO.setAdminFlg(rs.getString("admin_flg"));

				if(rs.getString("login_id") != null) {
					loginDTO.setLoginFlg(true);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loginDTO;
	}

}
