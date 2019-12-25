package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.CartDAO;
import com.internousdev.ecsite2.dao.EmptyCartDAO;
import com.internousdev.ecsite2.dao.ItemListDAO;
import com.internousdev.ecsite2.dao.LoginDAO;
import com.internousdev.ecsite2.dto.CartDTO;
import com.internousdev.ecsite2.dto.ItemInfoDTO;
import com.internousdev.ecsite2.dto.LoginDTO;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements SessionAware {
	private String loginUserId;
	private String loginPassword;
	// ユーザーID保存フラグの管理
	private boolean saveUserIdFlg;
	private Map<String, Object> session;
	private LoginDAO loginDAO = new LoginDAO();
	private LoginDTO loginDTO = new LoginDTO();
	private ItemListDAO itemListDAO = new ItemListDAO();
	private String errorMessage;
	private ArrayList<ItemInfoDTO> itemInfoList = new ArrayList<ItemInfoDTO>();

	public String execute() throws SQLException {
		String result = ERROR;



		// 未入力の有無チェック
		if("".equals(loginUserId) || "".equals(loginPassword)) {
			setErrorMessage("未入力の項目があります。");
			return result;
		}
		// ユーザーID保存フラグ情報が既にセッションに格納されていたら、ひとまず削除する
		if(session.containsKey("saveUserIdFlg")) {
			session.remove("saveUserIdFlg");
		}
		// 入力値からユーザ情報が存在するかを確認する
		if(!loginDAO.isExistsUserInfo(loginUserId, loginPassword)) {
			setErrorMessage("一致するユーザーはありません。");
			return result;
		}

		if(!session.containsKey("tempUserId")) {
			return "systemError";
		}

		// カート情報引継ぎ関連の処理
		CartDAO cartDAO = new CartDAO();
		String tempUserId = session.get("tempUserId").toString();
		ArrayList<CartDTO> cartInfoListforTempUser = cartDAO.getCartInfo(tempUserId);
		if(!cartInfoListforTempUser.isEmpty() && cartInfoListforTempUser != null) {
			int changeRes = changeCartInfo(cartInfoListforTempUser, tempUserId);
			if(!(changeRes == cartInfoListforTempUser.size())) {
				return result;
			}
		}
		// ログイン認証とカート情報引継ぎが成功した場合、次の画面で商品情報が必要なため商品情報を取得する
		// データベースから商品情報を取得し、配列に格納するメソッド
		itemInfoList = itemListDAO.getItemInfo();
		if(itemInfoList.size() > 0) {
			// セッションを利用しないと、success以外の場合に商品情報が保持されないので購入の際に困ることになる！
			session.put("itemInfoList", itemInfoList);
			result = SUCCESS;
		} else {
			setErrorMessage("商品情報がありません。");
		}

		// 入力値から管理者かどうかを確認
		loginDTO = loginDAO.getLoginUserInfo(loginUserId, loginPassword);
		if("1".equals(loginDTO.getAdminFlg())) {
			result = "admin";
			session.put("admin_flg", loginDTO.getAdminFlg());
		}
		// 認証に成功したら、tempUserId を削除し、loginUserId をセッションで管理する
		session.remove("tempUserId");
		session.put("login_user_id", loginUserId);
		session.put("loginedFlg", 1);
		// ユーザーＩＤ保存フラグがtrueの場合に、ユーザーＩＤ保存フラグをセッション管理化
		if(saveUserIdFlg) {
			session.put("saveUserIdFlg", true);
		}

		// カートが入っていた場合、cart.jsp に遷移
		if(session.containsKey("cartList")) {
			result = "cart";
		}

		return result;
	}

	public int changeCartInfo(ArrayList<CartDTO> cartInfoListforTempUser, String tempUserId) throws SQLException {
		int recordCount = 0;
		CartDAO cartDAO = new CartDAO();
		EmptyCartDAO emptyCartDAO = new EmptyCartDAO();
		for(CartDTO s: cartInfoListforTempUser) {
			// 既にカート情報に追加済みで再度ログインした場合かどうかをチェック
			if(cartDAO.checkExists(s.getItemId(), loginUserId)) {
				// true の場合、既存のカート情報に、保持している仮ユーザーＩＤで挿入された該当商品の数を加算
				recordCount += cartDAO.updateCart(s.getItemId(), s.getTotalCount(), loginUserId);
				// 仮ユーザーＩＤで登録したものを削除
				emptyCartDAO.emptyCartEach(Integer.toString(s.getItemId()), tempUserId);
			} else {
				// false の場合、保持している仮ユーザーＩＤで挿入されたカート情報をログインしたユーザーＩＤに更新する
				recordCount += cartDAO.linktoLoginUserId(tempUserId, loginUserId);
			}
		}
		return recordCount;
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

	public boolean getSaveUserIdFlg() {
		return saveUserIdFlg;
	}
	public void setSaveUserIdFlg(boolean saveUserIdFlg) {
		this.saveUserIdFlg = saveUserIdFlg;
	}

	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public ArrayList<ItemInfoDTO> getItemInfoList() {
		return itemInfoList;
	}

	public Map<String, Object> getSession() {
		return this.session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
}
