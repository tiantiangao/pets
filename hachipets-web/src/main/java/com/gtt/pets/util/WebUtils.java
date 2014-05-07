package com.gtt.pets.util;

import com.gtt.kenshin.web.util.SpringLocator;
import com.gtt.pets.service.GlobalService;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author tiantiangao
 */
public class WebUtils {

	private static final String DEFAULT_COOKIE_DOMAIN = "hachipets.com";

	/**
	 * 电子邮件验证的正则表达式
	 */
	private final static String EmailPattern = "^([\\w-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([\\w-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";

	public static String getCookieDomain() {
		GlobalService globalService = SpringLocator.getBean(GlobalService.class);
		String cookieDomain = globalService.get("cookieDomain");
		if (StringUtils.isBlank(cookieDomain)) {
			cookieDomain = DEFAULT_COOKIE_DOMAIN;
		}
		return cookieDomain;
	}

	public static boolean isValidEmail(String email) {
		return Pattern.matches(EmailPattern, email);
	}
}
