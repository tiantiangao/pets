package com.gtt.pets.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tiantiangao
 */
public class IPUtils {

	private static final String UNKNOWN = "unknown";
	private static final String DEFAULT_IP = "0.0.0.0";

	public static String getUserIP(HttpServletRequest request) {
		String userIP = null;
		if (StringUtils.isBlank(userIP) || UNKNOWN.equalsIgnoreCase(userIP)) {
			userIP = request.getRemoteAddr();
		}
		if (StringUtils.isBlank(userIP) || UNKNOWN.equalsIgnoreCase(userIP)) {
			return DEFAULT_IP;
		} else {
			return filterUserIP(userIP);
		}
	}

	/**
	 * @param userIP
	 * @return
	 */
	private static String filterUserIP(String userIP) {
		// IP为空
		if (StringUtils.isBlank(userIP)) {
			return DEFAULT_IP;
		}

		// IP中不包含逗号
		if (!userIP.contains(",")) {
			return userIP.trim();
		}

		// 使用逗号分隔并过滤
		String[] userIPList = userIP.split(",");
		for (String ip : userIPList) {
			ip = ip.trim();
			if (!IPFilter.instance().isFilter(ip)) {
				return ip;
			}
		}

		// 所有IP都与规则匹配，被过滤掉了，则取最后一个IP
		for (int index = userIPList.length - 1; index >= 0; index--) {
			if (StringUtils.isNotBlank(userIPList[index])) {
				return userIPList[index].trim();
			}
		}

		// 仍然无结果, 不太可能吧
		return DEFAULT_IP;
	}
}
