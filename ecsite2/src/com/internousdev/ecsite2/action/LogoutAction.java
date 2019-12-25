package com.internousdev.ecsite2.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;

	public String execute() {
		// session.clear をする前に、ユーザーＩＤ保存機能のために再度セッション管理化する値を変数に代入する
		/**
		 *  null の可能性がある場合は、String クラスの valueOf メソッドを使うと便利。
		 *  String.valuOf(session.get("saveUserIdFlg")) という記述が2つ用いると冗長になるので、
		 *  preSaveUserIdFlg という変数に代入している。
		 */
		String loginUserId = String.valueOf(session.get("login_user_id"));
		String preSaveUserIdFlg = String.valueOf(session.get("saveUserIdFlg"));
		boolean saveUserIdFlg = "null".equals(preSaveUserIdFlg)? false: Boolean.valueOf(preSaveUserIdFlg);

		session.clear();
		// saveUserIdFlg が true の場合に、セッションで格納し、ログアウト後にも保持させる
		if(saveUserIdFlg) {
			session.put("saveUserIdFlg", saveUserIdFlg);
			session.put("login_user_id", loginUserId);
		}
		return SUCCESS;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
