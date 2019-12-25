package com.internousdev.ecsite2.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.ecsite2.dao.ItemListDAO;
import com.internousdev.ecsite2.dao.MCategoryDAO;
import com.internousdev.ecsite2.dto.ItemInfoDTO;
import com.internousdev.ecsite2.dto.MCategoryDTO;
import com.opensymphony.xwork2.ActionSupport;

public class SearchItemAction extends ActionSupport implements SessionAware {
	private String categoryId;
	private String keywords;
	private String jspName;
	private String itemSort;
	private Map<String, Object> session;
	private ArrayList<ItemInfoDTO> itemInfoList = new ArrayList<ItemInfoDTO>();
	private boolean emptyListFlg = false;

	/** struts2 の validate メソッドを使って バリデーション・ロジックを追加
	 *  addFieldError メソッドでエラーメッセージが追加されると、execute メソッドの処理はスキップされて、"input" とという文字列が呼び出し元に返される。
	 *  したがって、struts.xml に result="input" の場合の遷移先を設定する必要がある。
	 *  addFieldError メソッドによって設定されたエラーメッセージを表示するためには、該当するform部品に Strutsタグを使用する必要がある。
	 */
	@Override
	public void validate() {
		// 特定の遷移元jsp名を取得
		session.put("jspName", jspName);
		if(keywords.length() < 0 || keywords.length() > 50 || keywords.matches("^[ｦ-ﾟ]+$") || (!keywords.matches("^[a-zA-Z0-9ぁ-んァ-ヶー 　々〇〻\u3400-\u9FFF\uF900-\uFAFF\uD840-\uD87F\uDC00-\uDFFF]+$") && keywords != null && !"".equals(keywords))) {
			addFieldError("keywords", "検索ワードに使用できる文字は、\n半角英数字・漢字・全角カタカナ・ひらがな・スペースです。");
		}
	}

	public String execute() {
		String result = ERROR;

		if(categoryId == null) {
			categoryId = "1";
		}
		if(itemSort == null) {
			itemSort = "商品ID";
		}
		// スペースなどの処理
		if(StringUtils.isBlank(keywords)) {
			// キーワードがnull,"", "　"のときに空文字に設定する
			keywords = "";
		} else {
			// 正規表現の s{2,} は、半角スペース、タブ、改行のいずれかが2以上繰り返している場合を示す
			keywords = keywords.replaceAll("　", " ").replaceAll("\\s{2,}", " ").trim();
		}
		ItemListDAO itemInfoDAO = new ItemListDAO();
		switch(categoryId) {
		case "1":
			try {
				itemInfoList = itemInfoDAO.getItemInfoByKeywords(keywords.split(" "), itemSort); // keywords を" "で分割
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			break;
		default:
			try {
				itemInfoList = itemInfoDAO.getItemInfoByKeywordsANDCategoryId(keywords.split(" "), categoryId, itemSort);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			break;
		}
		// 一致する商品がなかった旨のメッセージをjspで表示させるためにitemListFlgで管理
		if(itemInfoList.size() <= 0) {
			emptyListFlg = true;
		}
		session.put("itemInfoList", itemInfoList);

		// 商品検索のためのカテゴリー情報を取得し、セッションに格納
		if(!session.containsKey("mCategoryList")) {
			MCategoryDAO mCategoryDAO = new MCategoryDAO();
			ArrayList<MCategoryDTO> mCategoryList = new ArrayList<MCategoryDTO>();
			try {
				mCategoryList = mCategoryDAO.getMCategoryList();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			session.put("mCategoryList", mCategoryList);
		}
		result = SUCCESS;
		return result;
	}

	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords= keywords;
	}

	public String getJspName() {
		return jspName;
	}
	public void setJspName(String jspName) {
		this.jspName = jspName;
	}

	public String getItemSort() {
		return itemSort;
	}

	public void setItemSort(String itemSort) {
		this.itemSort = itemSort;
	}

	public ArrayList<ItemInfoDTO> getItemInfoList() {
		return itemInfoList;
	}

	public boolean getEmptyListFlg() {
		return emptyListFlg;
	}

	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
