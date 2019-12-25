package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.CartDAO;
import com.internousdev.ecsite2.dto.CartDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AddtoCartAction extends ActionSupport implements SessionAware {
	private int id;
	private int count;
	private int sum = 0;
	private Map<String, Object> session;
	private CartDAO addtoCartDAO = new CartDAO();
	private ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();
	private String message;

	public String execute() throws SQLException {
		String result = ERROR;

		if(!session.containsKey("login_user_id") && !session.containsKey("tempUserId")) {
			return "sessionError";
		}

		int res = 0;
		String login_user_id = null;

		String tempLogined = session.get("loginedFlg").toString();

		int loginedFlg = tempLogined==null? 0: Integer.parseInt(tempLogined);

		if(loginedFlg == 1) {
			login_user_id = session.get("login_user_id").toString();
		} else {
			login_user_id = session.get("tempUserId").toString();
		}

		// 商品在庫数を取得
		CartDTO stockCountDTO = new CartDTO();
		stockCountDTO = addtoCartDAO.getItemStock(id);

		if(!(id == 0)) {
			// jsp から送られてきた id を元に在庫チェック
			if(addtoCartDAO.checkExists(id, login_user_id) == false) {
				// カートに該当item_idが存在した場合
				// 商品在庫数とカートに入れようとしている数量を比較
				if(stockCountDTO.getItemStock() >= count) {
					res = addtoCartDAO.initialAdd(id, count, login_user_id);
					if(res > 0) {
						result = SUCCESS;
					} else {
						setMessage("追加に失敗しました。");
					}
				} else {
					setMessage("在庫が足りません。");
				}
			} else {
				// カートに該当item_idが存在した場合
				// カートに入れた個数を取得
				CartDTO totalCountDTO = new CartDTO();
				totalCountDTO = addtoCartDAO.getTotalCount(id, login_user_id);
				if (stockCountDTO.getItemStock() >= totalCountDTO.getTotalCount() + count) {
					res = addtoCartDAO.updateCart(id, count, login_user_id);
					if(res > 0) {
						result = SUCCESS;
					} else {
						setMessage("追加に失敗しました。");
					}
				} else {
					setMessage("在庫が足りません。");
				}
			}
		}
		// カート内の商品を取得
		cartList = addtoCartDAO.getCartInfo(login_user_id);

		if(cartList.size() > 0) {
			session.put("cartList", cartList);
			// 拡張for文でsumに各商品の小計を加算していって、合計を算出する
			for(CartDTO s:cartList) {
				sum += s.getItemPrice() * s.getTotalCount();
			}
			// カートと購入確認画面への表示のために、セッションとして管理
			session.put("sum", sum);
		}
		return result;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

	public int getSum() {
		return sum;
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
}
