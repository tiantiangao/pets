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
import com.gtt.pets.service.city.CityGaodeService;
import com.gtt.pets.web.action.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 宠物医院
 *
 * @author tiantiangao
 */
public class PetsSwitchCityCheckAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String city;

	@Autowired
	private CityGaodeService cityGaodeService;

	private int code = 500;
	private CityGaodeDTO cityGaode;

	@Override
	public String doExecute() throws Exception {

		if (StringUtils.isBlank(city)) {
			return SUCCESS;
		}

		cityGaode = cityGaodeService.loadByCityName(city);
		if (cityGaode != null) {
			code = 200;
		}

		return SUCCESS;
	}

	public int getCode() {
		return code;
	}

	public CityGaodeDTO getCityGaode() {
		return cityGaode;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
