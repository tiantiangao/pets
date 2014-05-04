package com.gtt.pets.bean.hospital;

import com.gtt.pets.bean.BaseDTO;

/**
 * @author tiantiangao
 */
public class DPDealDTO extends BaseDTO {

	private String dealDesc;
	private String dealUrl;

	public String getDealDesc() {
		return dealDesc;
	}

	public void setDealDesc(String dealDesc) {
		this.dealDesc = dealDesc;
	}

	public String getDealUrl() {
		return dealUrl;
	}

	public void setDealUrl(String dealUrl) {
		this.dealUrl = dealUrl;
	}
}
