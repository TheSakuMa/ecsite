package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite2.dto.CartDTO;
import com.internousdev.ecsite2.util.DBConnector;
import com.internousdev.ecsite2.util.DateUtil;

public class CartDAO {

	/**
	 *  1.初回かどうかチェック
	 * @param id
	 * @param login_user_id
	 * @return
	 */
	public boolean checkExists(int id, String login_user_id) {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		boolean res = false;

		String check = "SELECT COUNT(*) AS exist FROM cart WHERE item_id = ? AND user_master_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(check);
			ps.setInt(1, id);
			ps.setString(2, login_user_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				if(rs.getInt("exist") > 0) {
					res = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * 2.カートに入れた個数を取得
	 * @param id
	 * @param login_user_id
	 * @return
	 * @throws SQLException
	 */
	public CartDTO getTotalCount(int id, String login_user_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		CartDTO cartDTO = new CartDTO();

		String sql = "SELECT total_count FROM cart WHERE item_id = ? AND user_master_id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, login_user_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cartDTO.setTotalCount(rs.getInt("total_count"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartDTO;
	}

	/**
	 *  3.商品在庫を取得
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public CartDTO getItemStock(int itemId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		CartDTO cartDTO = new CartDTO();

		String sql = "SELECT item_stock FROM item_info WHERE id = ?";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, itemId);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				cartDTO.setItemStock(rs.getInt("item_stock"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartDTO;
	}

	/**
	 *  4.カートに初回追加
	 * @param id
	 * @param count
	 * @param login_user_id
	 * @return
	 * @throws SQLException
	 */
	public int initialAdd(int id, int count, String login_user_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();
		int res = 0;

		String sql = "INSERT INTO cart (item_id, total_count, user_master_id, insert_date) VALUES (?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, count);
			ps.setString(3, login_user_id);
			ps.setString(4, dateUtil.getDate());
			res = ps.executeUpdate();
		} catch (Exception  e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

	/**
	 *  5.カートに数量を追加する
	 * @param id
	 * @param count
	 * @param login_user_id
	 * @return
	 * @throws SQLException
	 */
	public int updateCart(int id, int count, String login_user_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();
		int res = 0;

		String update = "UPDATE cart SET total_count = (total_count + ?), update_date = ? WHERE item_id = ? AND user_master_id = ?";

		try {
			PreparedStatement ps3 = con.prepareStatement(update);
			ps3.setInt(1, count);
			ps3.setString(2, dateUtil.getDate());
			ps3.setInt(3, id);
			ps3.setString(4, login_user_id);
			res = ps3.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

	/**
	 *  6.カート内の商品情報をcartテーブルとitem_infoテーブルから取得し、ArrayList で Action に返す
	 * @param login_user_id
	 * @return
	 * @throws SQLException
	 */
	public ArrayList<CartDTO> getCartInfo(String login_user_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();

		// 並び順に改善の余地有り
		String sql = "SELECT cart.item_id, iit.item_name, cart.total_count, iit.item_price "
				+ "FROM cart INNER JOIN item_info iit ON cart.item_id = iit.id "
				+ "WHERE cart.user_master_id = ? ORDER BY update_date DESC";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login_user_id);
			ResultSet rs = ps.executeQuery();

			while(rs.next()) {
				// while文の中でインスタンス化しないと上書きされる？からNG！
				CartDTO cartDTO = new CartDTO();
				cartDTO.setItemId(rs.getInt("item_id"));
				cartDTO.setItemName(rs.getString("item_name"));
				cartDTO.setTotalCount(rs.getInt("total_count"));
				cartDTO.setItemPrice(rs.getInt("item_price"));
				cartList.add(cartDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartList;
	}

	/**
	 *  LoginAction にて使用。
	 *  保持している仮ユーザーＩＤで挿入されたカート情報をログインしたユーザーＩＤに更新する。
	 * @param tempUserId
	 * @param login_user_id
	 * @return
	 * @throws SQLException
	 */
	public int linktoLoginUserId(String tempUserId, String login_user_id) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		int res = 0;

		String sql = "UPDATE cart SET user_master_id = ?, update_date = now() WHERE user_master_id = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, login_user_id);
			ps.setString(2, tempUserId);
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}
}
