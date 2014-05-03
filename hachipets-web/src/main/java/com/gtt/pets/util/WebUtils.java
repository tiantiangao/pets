package com.gtt.pets.util;

import com.gtt.kenshin.web.util.SpringLocator;
import com.gtt.pets.service.GlobalService;
import org.apache.commons.lang3.StringUtils;

/**
 * @author tiantiangao
 */
public class WebUtils {

	private static final String DEFAULT_COOKIE_DOMAIN = "hachipets.com";

	public static String getCookieDomain() {
		GlobalService globalService = SpringLocator.getBean(GlobalService.class);
		String cookieDomain = globalService.get("cookieDomain");
		if (StringUtils.isBlank(cookieDomain)) {
			cookieDomain = DEFAULT_COOKIE_DOMAIN;
		}
		return cookieDomain;
	}
}
