package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.internousdev.ecsite2.util.DBConnector;

public class UserCreateConfirmDAO {

	// 入力された login_id が既に登録されているかを確認するためのメソッド
	public boolean userExists(String loginUserId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		boolean result = false;
		// 【重要】COUNTで該当するデータの件数を数えつつ、AS count で該当するデータを count という名前で取得する
		// AS count なしだと、取得される情報は件数だけなので、エラーになる
		String sql = "SELECT COUNT(*) AS count FROM user_info WHERE login_id=?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUserId);
			// executeQuery メソッドの実行結果は、ResultSetオブジェクトを返す
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				if (rs.getInt("count") > 0) {
					result = true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
