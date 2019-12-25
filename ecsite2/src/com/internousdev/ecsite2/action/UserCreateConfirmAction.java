package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.UserCreateConfirmDAO;
import com.opensymphony.xwork2.ActionSupport;

public class UserCreateConfirmAction extends ActionSupport implements SessionAware {

	private String loginUserId;
	private String loginPassword;
	private String userName;
	private Map<String, Object> session;
	private String errorMessage;
	private UserCreateConfirmDAO userCreateConfirmDAO = new UserCreateConfirmDAO();

	public String execute() throws SQLException {
		String result = SUCCESS;

		if(!(loginUserId.equals("")) && !(loginPassword.equals("")) && !(userName.equals(""))) {
			if (userCreateConfirmDAO.userExists(loginUserId)) {
				setErrorMessage("すでに登録されています。");
				result = ERROR;
			} else {
				session.put("loginUserId", loginUserId);
				session.put("loginPassword", loginPassword);
				session.put("userName", userName);
			}
		} else {
			setErrorMessage("未入力の項目があります。");
			result = ERROR;
		}
		return result;
	}

	public String getLoginUserId() {
		return loginUserId;
	}
	public void setLoginUserId(String loginUserId) {
		this.loginUserId = loginUserId;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
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
		if(userName.length() < 1 || userName.length() > 16 || (!userName.matches("^[a-zA-Z0-9ぁ-んァ-ヶー 　々〇〻\u3400-\u9FFF\uF900-\uFAFF\uD840-\uD87F\uDC00-\uDFFF]+$")) || userName.matches("^[ｦ-ﾟ]+$")) {
			addFieldError("userName", "ユーザー名に使用できる文字は、半角英数字・漢字・全角カタカナ・ひらがなです。");
		}
	}

	public Map<String, Object> getSession() {
		return this.session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
