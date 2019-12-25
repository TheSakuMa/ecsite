package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.MyPageDAO;
import com.internousdev.ecsite2.dto.MyPageDTO;
import com.opensymphony.xwork2.ActionSupport;

public class MyPageAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private MyPageDAO myPageDAO = new MyPageDAO();
	private ArrayList<MyPageDTO> myPageList = new ArrayList<MyPageDTO>();
	private String myPageUserName;
	private String deleteFlg;
	private String deleteItemFlg;
	private String message;
	private String buyItemHistoryId;

	public String execute() throws SQLException {

		if(!session.containsKey("loginedFlg") || Integer.parseInt(session.get("loginedFlg").toString()) != 1) {
			return ERROR;
		}

		String user_master_id = session.get("login_user_id").toString();
		myPageList = myPageDAO.getMyPageItemsInfo(user_master_id);

		int result_delete = 0;
		// 全権削除かチェック
		if("1".equals(deleteFlg)) { // .equalsの左辺は、nullになりえないものでないといけない（nullはequals()メソッドを持っていないから）
			delete();
		} else if("1".equals(deleteItemFlg)) { // 削除ボタンを押すまでは、deleteItemFlg が参照する値は、null
			//1件削除かチェック
			result_delete = deleteItem();
		}

		// 個別削除した後もmyPage.jspにおいて購入履歴情報を表示するので、下記のような条件が必要
		if(deleteFlg == null || result_delete > 0) {
			myPageUserName = myPageDAO.getMyPageUserInfo(user_master_id);
			myPageList = myPageDAO.getMyPageItemsInfo(user_master_id);
			if (myPageList.size() == 0) {
				setMessage("購入情報はありません。");
			}
		}

		String result = SUCCESS;
		return result;
	}

	public void delete() throws SQLException {
		String user_master_id = session.get("login_user_id").toString();

		int res = myPageDAO.buyItemHistoryDelete(user_master_id);

		if(res > 0) {
			myPageList = null;
		} else if(res == 0) {
			setMessage("商品情報の削除に失敗しました。");
		}
	}

	public int deleteItem() throws SQLException {
		int res = myPageDAO.DeleteBuyItem(buyItemHistoryId);
		return res;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	public void setDeleteItemFlg(String deleteItemFlg) {
		this.deleteItemFlg = deleteItemFlg;
	}
	public ArrayList<MyPageDTO> getMyPageList() {
		return myPageList;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	// フォームで送られてきた name=id の要素の value の値（user_buy_item_transactionテーブルのid）を取得する
	public void setBuyItemHistoryId(String buyItemHistoryId) {
		this.buyItemHistoryId = buyItemHistoryId;
	}

	public String getMyPageUserName() {
		return myPageUserName;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
