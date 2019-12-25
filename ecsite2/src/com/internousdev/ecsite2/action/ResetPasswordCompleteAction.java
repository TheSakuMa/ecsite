package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.LoginDAO;
import com.internousdev.ecsite2.dao.ResetPasswordDAO;
import com.opensymphony.xwork2.ActionSupport;

public class ResetPasswordCompleteAction extends ActionSupport implements SessionAware {
	private String loginUserId;
	private String loginPassword;
	private String newLoginPassword;
	private String confirmNewLoginPassword;
	private String errorMessage;
	private Map<String, Object> session;
	private ResetPasswordDAO resetPasswordDAO = new ResetPasswordDAO();

	public String execute() throws SQLException {
		String result = ERROR;
		LoginDAO loginDAO = new LoginDAO();
		if(!newLoginPassword.equals(confirmNewLoginPassword)) {
			setErrorMessage("「新しいログインPASS」と「新しいログインPASS（確認）」が一致しません。");
			return result;
		}
		if(!loginDAO.isExistsUserInfo(loginUserId, loginPassword)) {
			setErrorMessage("ユーザーIDまたはログインPASSが正しくありません。");
			return result;
		}
		if(resetPasswordDAO.resetPassword(loginPassword, newLoginPassword)) {
			result = SUCCESS;
		}
		return result;
	}

	/** struts2 の validate メソッドを使って バリデーション・ロジックを追加
	 * addFieldError メソッドでエラーメッセージが追加されると、execute メソッドの処理はスキップされて、"input" とという文字列が呼び出し元に返される。
	 * したがって、struts.xml に result="input" の場合の遷移先を設定する必要がある。
	 * addFieldError メソッドによって設定されたエラーメッセージを表示するためには、該当するform部品に Strutsタグを使用する必要がある。
	 */
	public void validate() {
		if(loginUserId.length() < 1 || loginUserId.length() > 12 || !loginUserId.matches("^[a-zA-Z0-9]+$")) {
			addFieldError("loginUserId", "ログインIDは、半角英数字1文字以上12文字以内で入力して下さい。");
		}
		if(loginPassword.length() < 1 || loginPassword.length() > 16 || !loginPassword.matches("^[a-zA-Z0-9]+$")) {
			addFieldError("loginPassword", "ログインPASSは、半角英数字1文字以上16文字以内で入力して下さい。");
		}
		if(newLoginPassword.length() < 1 || newLoginPassword.length() > 16 || !newLoginPassword.matches("^[a-zA-Z0-9]+$")) {
			addFieldError("newLoginPassword", "新しいログインPASSは、半角英数字1文字以上16文字以内で入力して下さい。");
		}
		if(confirmNewLoginPassword.length() < 1 || confirmNewLoginPassword.length() > 16 || !confirmNewLoginPassword.matches("^[a-zA-Z0-9]+$")) {
			addFieldError("confirmNewLoginPassword", "新しいログインPASS（確認）は、半角英数字1文字以上16文字以内で入力して下さい。");
		}
	}

	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public void setNewLoginPassword(String newLoginPassword) {
		this.newLoginPassword = newLoginPassword;
	}
	public void setConfirmNewLoginPassword(String confirmNewLoginPassword) {
		this.confirmNewLoginPassword = confirmNewLoginPassword;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
