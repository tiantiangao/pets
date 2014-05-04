/**
 * Project: bag
 *
 * File Created at 2013-6-14
 * $Id$
 *
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.gtt.pets.web.action.hospital;

import com.gtt.pets.bean.city.CityGaodeDTO;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.constants.Cookies;
import com.gtt.pets.service.GlobalService;
import com.gtt.pets.service.city.CityGaodeService;
import com.gtt.pets.util.WebUtils;
import com.gtt.pets.web.action.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 宠物医院
 *
 * @author tiantiangao
 */
public class PetsHospitalAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private GlobalService globalService;
	@Autowired
	private CityGaodeService cityGaodeService;
	private CityGaodeDTO hospitalCity;

	@Override
	public String doExecute() throws Exception {
		setChannel(ChannelType.CHANNEL_HOSPITAL);

		checkCity();

		return SUCCESS;
	}

	private void checkCity() {
		String city = getCookieValue(Cookies.Hospital_City);
		try {
			if (StringUtils.isNotBlank(city)) {
				city = URLDecoder.decode(city, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
		}

		// 优先从cookie中解析上一次所在城市
		if (StringUtils.isNotBlank(city)) {
			hospitalCity = cityGaodeService.loadByCityName(city);
		}

		// cookie无数据或数据异常, 加载默认城市
		if (hospitalCity == null) {
			city = globalService.get("HospitalDefaultCity");
			hospitalCity = cityGaodeService.loadByCityName(city);
		}

		updateCookie();
	}

	private void updateCookie() {
		String cookieValue = "";
		try {
			cookieValue = URLEncoder.encode(hospitalCity.getCityName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		Cookie cookie = new Cookie(Cookies.Hospital_City, cookieValue);
		cookie.setPath("/");
		cookie.setDomain(WebUtils.getCookieDomain());
		cookie.setMaxAge(31536000);
		resp.addCookie(cookie);
	}

	public CityGaodeDTO getHospitalCity() {
		return hospitalCity;
	}
}
