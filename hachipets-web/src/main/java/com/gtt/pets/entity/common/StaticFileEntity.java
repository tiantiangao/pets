package com.gtt.pets.entity.common;

import com.gtt.pets.entity.BaseEntity;

/**
 * @author tiantiangao
 */
public class StaticFileEntity extends BaseEntity {

	private String url;
	private String md5;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}
}
