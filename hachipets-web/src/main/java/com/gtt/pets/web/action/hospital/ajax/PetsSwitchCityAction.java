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
package com.gtt.pets.web.action.hospital.ajax;

import com.gtt.pets.bean.city.CityGaodeDTO;
import com.gtt.pets.constants.Cookies;
import com.gtt.pets.service.city.CityGaodeService;
import com.gtt.pets.util.WebUtils;
import com.gtt.pets.web.action.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.Cookie;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 宠物医院
 *
 * @author tiantiangao
 */
public class PetsSwitchCityAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String city;

	@Autowired
	private CityGaodeService cityGaodeService;

	private int code = 500;

	@Override
	public String doExecute() throws Exception {

		if (StringUtils.isBlank(city)) {
			return SUCCESS;
		}

		CityGaodeDTO cityGaode = cityGaodeService.loadByCityName(city);
		if (cityGaode != null) {
			updateCookie(cityGaode);
			code = 200;
		}

		return SUCCESS;
	}

	private void updateCookie(CityGaodeDTO city) {
		String cookieValue = "";
		try {
			cookieValue = URLEncoder.encode(city.getCityName(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
		}
		Cookie cookie = new Cookie(Cookies.Hospital_City, cookieValue);
		cookie.setPath("/");
		cookie.setDomain(WebUtils.getCookieDomain());
		cookie.setMaxAge(31536000);
		resp.addCookie(cookie);
	}

	public int getCode() {
		return code;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
