package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.CartDAO;
import com.internousdev.ecsite2.dto.CartDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware {
	private ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();
	private CartDAO cartDAO = new CartDAO();
	private Map<String, Object> session;
	int sum = 0;
	private String emptyMessage;
	private String searchErrorMessage;

	public String execute() throws SQLException {
		String result = SUCCESS;

		if(!session.containsKey("login_user_id") && !session.containsKey("tempUserId")) {
			return "sessionError";
		}

		// 検索エラーメッセージの受け渡し
		if(session.containsKey("searchErrorMessage")) {
			searchErrorMessage = session.get("searchErrorMessage").toString();
			session.remove("searchErrorMessage");
		}

		String login_user_id = null;
		if(session.containsKey("login_user_id")) {
			login_user_id = session.get("login_user_id").toString();
		} else if(session.containsKey("tempUserId")) {
			login_user_id = session.get("tempUserId").toString();
		} else {
			return ERROR;
		}

		// データベースから商品情報を取得する
		cartList = cartDAO.getCartInfo(login_user_id);
		// 配列のサイズが 0 より大きい場合に、sum を算出し、cartList と共にセッションに格納
		if(cartList.size() > 0) {
			session.put("cartList", cartList);
			for(CartDTO s: cartList) {
				sum += s.getItemPrice() * s.getTotalCount();
			}
			session.put("sum", sum);
		} else {
			setEmptyMessage("カートが空です。");
		}
		return result;
	}

	public String getEmptyMessage() {
		return emptyMessage;
	}
	public void setEmptyMessage(String emptyMessage) {
		this.emptyMessage = emptyMessage;
	}

	public String getSearchErrorMessage() {
		return searchErrorMessage;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
