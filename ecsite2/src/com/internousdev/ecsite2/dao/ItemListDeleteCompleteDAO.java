package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.internousdev.ecsite2.util.DBConnector;
import com.internousdev.ecsite2.util.DateUtil;

public class ItemListDeleteCompleteDAO {

	public int deleteItemList() throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();
		int res = 0;

		// String sql = "DELETE FROM item_info"; // => 論理削除に変更
		String sql = "UPDATE item_info SET delete_date = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dateUtil.getDate());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

}
