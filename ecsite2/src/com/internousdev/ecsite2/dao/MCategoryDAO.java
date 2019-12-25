package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.internousdev.ecsite2.dto.MCategoryDTO;
import com.internousdev.ecsite2.util.DBConnector;

public class MCategoryDAO {
	public ArrayList<MCategoryDTO> getMCategoryList() throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		ArrayList<MCategoryDTO> mCategoryList = new ArrayList<MCategoryDTO>();

		String sql = "SELECT * FROM m_category";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				MCategoryDTO mCategoryDTO = new MCategoryDTO();
				mCategoryDTO.setId(rs.getInt("id"));
				mCategoryDTO.setCategoryId(rs.getInt("category_id"));
				mCategoryDTO.setCatagoryName(rs.getString("category_name"));
				mCategoryDTO.setCategoryDesc(rs.getString("category_description"));
				mCategoryList.add(mCategoryDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return mCategoryList;
	}

	public String getMCategoryId(String categoryName) throws SQLException {
		String categoryId = null;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();

		String sql = "SELECT category_id FROM m_category WHERE category_name = ?";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, categoryName);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				categoryId = rs.getString("category_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return categoryId;
	}
}
