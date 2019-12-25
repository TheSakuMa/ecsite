package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.CartDAO;
import com.internousdev.ecsite2.dao.EmptyCartDAO;
import com.internousdev.ecsite2.dto.CartDTO;
import com.opensymphony.xwork2.ActionSupport;

public class EmptyCartAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private String deleteMessage;
	private ArrayList<CartDTO> cartList = new ArrayList<CartDTO>();
	private CartDAO cartDAO = new CartDAO();
	private int sum = 0;
	private String emptyMessage;
	private String eachFlg;
	// checked されたname="checkList" のデータ（商品ID）が setter で配列に格納されていく
	private String[] checkList;

	public String execute() throws SQLException {
		String result = SUCCESS;
		EmptyCartDAO emptyCartDAO = new EmptyCartDAO();

		// session 内の値を代入するのは、execute メソッド内で行う
		String login_user_id = null;
		if(!session.containsKey("login_user_id")) {
			if(!session.containsKey("tempUserId")) {
				return ERROR;
			} else {
				login_user_id = session.get("tempUserId").toString();
			}
		} else {
			login_user_id = session.get("login_user_id").toString();
		}

		int res = 0;
		if("1".equals(eachFlg)) { // 個別削除フラグで分岐
			if(checkList == null) {
				result = ERROR;
				setDeleteMessage("削除する商品を選択してください。");
			} else {
				// 個別削除を行う
				for(String s:checkList) {
					res += emptyCartDAO.emptyCartEach(s, login_user_id);
				}
				// 削除に成功した数（res）がcheckListに格納された要素数と同じ数かどうか
				if(res == checkList.length) {
					// 個別削除に成功したら、cart.jspに更新されたカート情報を表示させるために、
					// 新たにデータベースからカート情報を取得し、cartListに格納する
					session.remove("cartList");
					cartList = cartDAO.getCartInfo(login_user_id);
					if(cartList.size() > 0) {
						session.put("cartList", cartList);
						for(CartDTO s: cartList) {
							sum += s.getItemPrice() * s.getTotalCount();
						}
						session.put("sum", sum);
					} else {
						setEmptyMessage("カートが空です。");
					}
				} else {
					result = ERROR;
					setDeleteMessage("削除に失敗しました1");
				}
			}
		} else if("0".equals(eachFlg)) {
			// 全件削除
			res = emptyCartDAO.emptyCartAll(login_user_id);
			if(res > 0) {
				session.remove("cartList");
				setEmptyMessage("カートが空です。");
			} else {
				result = ERROR;
				setDeleteMessage("削除に失敗しました2");
			}
		}
		return result;
	}

	public String getDeleteMessage() {
		return deleteMessage;
	}
	public void setDeleteMessage(String deleteMessage) {
		this.deleteMessage = deleteMessage;
	}

	public String getEmptyMessage() {
		return emptyMessage;
	}
	public void setEmptyMessage(String emptyMessage) {
		this.emptyMessage = emptyMessage;
	}

	public void setEachFlg(String eachFlg) {
		this.eachFlg = eachFlg;
	}

	public String[] getCheckList() {
		return checkList;
	}

	public void setCheckList(String[] checkList) {
		this.checkList = checkList;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
