package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.MCategoryDAO;
import com.internousdev.ecsite2.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

// セッションを保持したままHome画面に遷移させる
public class GoHomeAction extends ActionSupport implements SessionAware {

	private Map<String, Object> session;
	private String searchErrorMessage;
	private List<String> itemSortList = new ArrayList<String>() {
		{
			add("ID順");
			add("値段が高い順");
			add("値段が安い順");
		}
	};

	public String execute() throws SQLException {

		session.put("itemSortList", itemSortList);

		if(!session.containsKey("loginedFlg")) {
			session.put("loginedFlg", 0);
		}
		// 検索エラーメッセージの受け渡し
		if(session.containsKey("searchErrorMessage")) {
			searchErrorMessage = session.get("searchErrorMessage").toString();
			session.remove("searchErrorMessage");
		}
		/**
		 *  String.valueOf() メソッドは、引数にとった Object 引数の文字列を返す
		 *  .toString() メソッドとの違いは、引数が null だった場合に "null" に等しい文字列が返るという点で、
		 *  それ以外の場合は、toString() の値が返される。
		 */
		String tempLogined = String.valueOf(session.get("loginedFlg"));
		int loginedFlg = "null".equals(tempLogined)? 0: Integer.parseInt(tempLogined);
		if(!session.containsKey("tempUserId") && loginedFlg == 0) {
			session.put("tempUserId", getRandomId());
		}

		// 商品検索のためのカテゴリー情報を取得し、セッションに格納
		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			ArrayList<MCategoryDTO> mCategoryList = new ArrayList<MCategoryDTO>();
			mCategoryList = mCategoryDAO.getMCategoryList();
			session.put("mCategoryList", mCategoryList);
		}

		return SUCCESS;
	}

	public String getRandomId() {
		String value = "";
		StringBuilder tempUserId = new StringBuilder();
		int n;
		for(int i = 1; i <= 16; i++) {
			// Random クラスのnextInt() メソッドで、int型の擬似乱数を生成
			Random rand = new Random();
			n = rand.nextInt(9); // 0-9 の範囲のint型の擬似乱数を生成
			// 引数に渡した擬似乱数の文字列表現を追加していく
			tempUserId.append(n);
		}
		value = tempUserId.toString();
		return value;
	}

	public String getSearchErrorMessage() {
		return searchErrorMessage;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
