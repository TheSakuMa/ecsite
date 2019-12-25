package com.internousdev.ecsite2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.internousdev.ecsite2.dto.DestinationDTO;
import com.internousdev.ecsite2.util.DBConnector;
import com.internousdev.ecsite2.util.DateUtil;

public class DestinationDAO {

	public List<DestinationDTO> getDestinationInfo(String loginUserId) throws SQLException {
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		List<DestinationDTO> destinationList = new ArrayList<DestinationDTO>();
		String sql = "SELECT * FROM destination_info WHERE user_id = ? ORDER BY update_date DESC, regist_date DESC";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUserId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				DestinationDTO destinationDTO = new DestinationDTO();
				destinationDTO.setDestinationId(rs.getInt("id"));
				destinationDTO.setUserId(rs.getString("user_id"));
				destinationDTO.setFamilyName(rs.getString("family_name"));
				destinationDTO.setFirstName(rs.getString("first_name"));
				destinationDTO.setPhoneNumber(rs.getString("phone_number"));
				destinationDTO.setAddress(rs.getString("address"));
				destinationList.add(destinationDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return destinationList;
	}

	public int createDestination(String loginUserId, String familyName, String firstName, String phoneNumber, String address) throws SQLException {
		int res = 0;
		DBConnector db = new DBConnector();
		Connection con = db.getConnection();
		DateUtil dateUtil = new DateUtil();

		String sql = "INSERT INTO destination_info (user_id, family_name, first_name, phone_number, address, regist_date) VALUES (?,?,?,?,?,?)";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUserId);
			ps.setString(2, familyName);
			ps.setString(3, firstName);
			ps.setString(4, phoneNumber);
			ps.setString(5, address);
			ps.setString(6, dateUtil.getDate());
			res = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}
		return res;
	}

}
