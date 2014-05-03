package com.gtt.pets.entity.city;

import com.gtt.pets.entity.BaseEntity;

/**
 * 高德城市信息
 *
 * @author tiantiangao
 */
public class CityGaode extends BaseEntity {

	private String cityName;
	private double lat;
	private double lng;
	private int level;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
