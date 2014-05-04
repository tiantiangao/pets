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

import com.google.common.collect.Lists;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.hospital.HospitalDTO;
import com.gtt.pets.constants.Cookies;
import com.gtt.pets.service.GlobalService;
import com.gtt.pets.service.hospital.PetsHospitalService;
import com.gtt.pets.web.action.BaseAction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

/**
 * 宠物医院
 *
 * @author tiantiangao
 */
public class PetsCityHospitalSearchAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PetsHospitalService petsHospitalService;
	@Autowired
	private GlobalService globalService;

	private double southWestLat;
	private double southWestLng;
	private double northEastLat;
	private double northEastLng;
	private int page = 1;

	private int code = 500;
	private int pageCount;
	private List<HospitalDTO> list;

	@Override
	public String doExecute() throws Exception {

		String city = getCookieValue(Cookies.Hospital_City);
		try {
			if (StringUtils.isNotBlank(city)) {
				city = URLDecoder.decode(city, "UTF-8");
			}
		} catch (UnsupportedEncodingException e) {
		}

		if (StringUtils.isBlank(city)) {
			return SUCCESS;
		}

		PageModel<HospitalDTO> model = petsHospitalService
				.findHospitalByCityAndBounds(city, southWestLat, southWestLng, northEastLat, northEastLng, page);
		code = 200;
		list = Lists.newArrayList();
		if (model == null || CollectionUtils.isEmpty(model.getRecords())) {
			return SUCCESS;
		}

		pageCount = model.getPageCount();
		list = model.getRecords();

		return SUCCESS;
	}


	public int getCode() {
		return code;
	}

	public void setSouthWestLat(double southWestLat) {
		this.southWestLat = southWestLat;
	}

	public void setSouthWestLng(double southWestLng) {
		this.southWestLng = southWestLng;
	}

	public void setNorthEastLat(double northEastLat) {
		this.northEastLat = northEastLat;
	}

	public void setNorthEastLng(double northEastLng) {
		this.northEastLng = northEastLng;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPage() {
		return page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public List<HospitalDTO> getList() {
		return list;
	}
}
