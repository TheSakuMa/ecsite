package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite2.dto.MyPageDTO;
import com.internousdev.ecsite2.util.DBConnector;
import com.internousdev.ecsite2.util.DateUtil;

public class MyPageDAO {

	/**
	 * ログインユーザーの購入履歴を表示するために、データベースからデータを取得し、ArrayListに格納する
	 * @param user_master_id
	 * @return ArrayList<MyPageDTO>型
	 * @throws SQLException
	 */
	public ArrayList<MyPageDTO> getMyPageItemsInfo(String user_master_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<MyPageDTO> myPageDTO = new ArrayList<MyPageDTO>();

		// 20191111 多種類商品表示に伴い、変更
		String sql = "SELECT ubit.id, iit.item_name, ubit.total_price, ubit.total_count, ubit.pay, ubit.insert_date "
				+ "FROM user_buy_item_transaction ubit "
				+ "RIGHT JOIN item_info iit "
				+ "ON ubit.item_transaction_id = iit.id "
				+ "WHERE ubit.user_master_id=? AND ubit.delete_date IS NULL "
				+ "ORDER BY insert_date DESC";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			// ps.setString(1, item_transaction_id);
			ps.setString(1, user_master_id);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				MyPageDTO dto = new MyPageDTO();
				dto.setId(rs.getString("id"));
				dto.setItemName(rs.getString("item_name"));
				dto.setTotalPrice(rs.getString("total_price"));
				dto.setTotalCount(rs.getString("total_count"));
				dto.setPayment(rs.getString("pay"));
				dto.setInsert_date(rs.getString("insert_date"));
				// DTOをArrayListに格納
				myPageDTO.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return myPageDTO;
	}

	/**
	 * MyPage上にユーザー名を表示するため、データベースから情報を取得する
	 * @param login_user_id
	 * @return String型のユーザー名
	 */
	public String getMyPageUserInfo(String login_user_id) {
		String userName = null;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "SELECT user_name FROM user_info WHERE login_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login_user_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				userName = rs.getString("user_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return userName;
	}

	/**
	 * 該当ユーザーの購入履歴全削除
	 * @param user_master_id
	 * @return int型, update に成功した件数
	 * @throws SQLException
	 */
	public int buyItemHistoryDelete(String user_master_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();

		// 20191111 多種類商品表示対応に伴い変更
		String sql = "UPDATE user_buy_item_transaction SET delete_date = ? WHERE user_master_id=? AND delete_date IS NULL";

		PreparedStatement ps;
		int result = 0;
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, dateUtil.getDate());
			ps.setString(2, user_master_id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return result;
	}

	/**
	 * user_buy_item_transaction テーブルのIDをもとに該当する購入履歴を論理的に削除
	 * @param id (user_buy_item_transactionテーブルのid)
	 * @return int型, 削除に成功した件数（正常は1）
	 * @throws SQLException
	 */
	public int DeleteBuyItem(String user_buy_item_transaction_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();
		int result = 0;

		String sql = "UPDATE user_buy_item_transaction SET delete_date=? WHERE id=? AND delete_date IS NULL";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, dateUtil.getDate());
			ps.setString(2, user_buy_item_transaction_id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return result;
	}
}
