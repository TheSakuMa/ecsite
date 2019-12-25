package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.BuyItemCompleteDAO;
import com.internousdev.ecsite2.dao.CartDAO;
import com.internousdev.ecsite2.dto.CartDTO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemConfirmAction extends ActionSupport implements SessionAware {
	private String loginUserId = null;
	private Map<String, Object> session;
	private CartDAO cartDAO = new CartDAO();
	private BuyItemCompleteDAO buyItemCompleteDAO = new BuyItemCompleteDAO();

	public String execute() {
		String result = SUCCESS;
		int loginedFlg = !session.containsKey("loginedFlg")? 0: Integer.parseInt(session.get("loginedFlg").toString());
		if(loginedFlg == 1) {
			loginUserId = session.get("login_user_id").toString();
		} else {
			return "login";
		}
		ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();
		try {
			cartList = cartDAO.getCartInfo(loginUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 拡張For文で各要素の各データそれぞれで buyItemInfo メソッドを実行する
		for(CartDTO s: cartList) {
			int totalPrice = s.getItemPrice() * s.getTotalCount();
			int buyItemInfoRes = 0;
			int updateItemStockRes = 0;

			try {
				buyItemInfoRes = buyItemCompleteDAO.buyItemInfo(s.getItemId(), totalPrice, s.getTotalCount(), loginUserId, session.get("pay").toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(buyItemInfoRes > 0) {
				try {
					updateItemStockRes = buyItemCompleteDAO.updateItemStock(s.getTotalCount(), s.getItemId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				return ERROR;
			}
			if(updateItemStockRes > 0) {
				try {
					buyItemCompleteDAO.deleteCartItemafterBuy(loginUserId, s.getItemId());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} else {
				return ERROR;
			}
		}
		session.remove("cartList");
		session.remove("pay");
		session.remove("sum");
		return result;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
