package com.internousdev.ecsite2.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

	public String getDate() {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		// Dateクラスのインスタンスを日時文字列にフォーマットし、フォーマットされた時刻文字列を返す。
		return simpleDateFormat.format(date);
	}

}
