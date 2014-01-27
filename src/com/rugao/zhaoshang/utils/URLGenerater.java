package com.rugao.zhaoshang.utils;

public class URLGenerater {
	private static final String rex = "=\\?";

	public static String makeUrl(String whichApi, String... params) {
		if (whichApi != null && whichApi.length() > 0 && params != null
				&& params.length > 0) {
			for (String param : params) {
				whichApi = whichApi.replaceFirst(rex, "=" + param);
			}
		} else {
			throw new IllegalArgumentException();
		}
		StringBuffer sb = new StringBuffer();
		sb.append(Constants.DOMAIN).append(whichApi);
		return sb.toString();
	}
}
