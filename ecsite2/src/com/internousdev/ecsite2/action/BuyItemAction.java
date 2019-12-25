package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.BuyItemDAO;
import com.internousdev.ecsite2.dao.CartDAO;
import com.internousdev.ecsite2.dao.DestinationDAO;
import com.internousdev.ecsite2.dto.CartDTO;
import com.internousdev.ecsite2.dto.DestinationDTO;
import com.opensymphony.xwork2.ActionSupport;

public class BuyItemAction extends ActionSupport implements SessionAware {
	private String loginUserId;
	private Map<String, Object> session;
	private String pay;
	private int sum = 0;
	private String payment;
	private String message;
	private CartDAO cartDAO = new CartDAO();
	private BuyItemDAO buyItemDAO = new BuyItemDAO();
	private DestinationDAO destinationDAO = new DestinationDAO();
	private List<DestinationDTO> destinationList = new ArrayList<DestinationDTO>();

	public String execute() throws SQLException {
		String result = SUCCESS;

		int loginedFlg = !session.containsKey("loginedFlg")? 0: Integer.parseInt(session.get("loginedFlg").toString());
		if(loginedFlg == 1) {
			loginUserId = session.get("login_user_id").toString();
		} else {
			if(!session.containsKey("tempUserId")) {
				// ログインフラグも仮ユーザーＩＤもセッションに格納されていなかったら、Error 画面に遷移させる
				return ERROR;
			}
			// 仮ユーザーIDがセッションに格納されていたら、login.jsp に遷移させる
			return "login";
		}

		// 決済方法設定・セッション管理化
		if("1".equals(pay)) {
			payment = "現金払い";
			session.put("pay", payment);
		} else if("2".equals(pay)) {
			payment = "クレジットカード払い";
			session.put("pay", payment);
		}
		// 在庫再確認
		ArrayList<CartDTO> cartList = cartDAO.getCartInfo(loginUserId);
		for(CartDTO s: cartList) {
			int itemStock = 0;
			itemStock = buyItemDAO.getBuyItemInfo(s.getItemId()).getItemStock();
			if(itemStock < s.getTotalCount()) {
				setMessage("在庫が不足しています。");
				return ERROR;
			}
			session.remove("cartList");
			session.put("cartList", cartList);
		}
		// 宛先情報表示のための処理
		try {
			destinationList = destinationDAO.getDestinationInfo(loginUserId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int getSum() {
		return sum;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	public String getPayment() {
		return payment;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public List<DestinationDTO> getDestinationList() {
		return destinationList;
	}

}
