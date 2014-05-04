package com.gtt.pets.bean.hospital;

import java.io.Serializable;
import java.util.List;

/**
 * 高德城市信息
 *
 * @author tiantiangao
 */
public class HospitalDTO implements Serializable, Cloneable {

	private int shopId;
	private String shopName;
	private String address;
	private String telephone;
	private double lat;
	private double lng;
	private String cityName;
	private String ratingUrl;
	private int reviewCount;
	private int avgPrice;
	private String dpShopUrl;
	private String photoUrl;
	private String sPhotoUrl;
	/* 优惠券信息 */
	private boolean hasCoupon;
	private String couponDesc;
	private String couponUrl;
	/* 团购信息 */
	private boolean hasDeal;
	private List<DPDealDTO> dealList;

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

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public int getShopId() {
		return shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRatingUrl() {
		return ratingUrl;
	}

	public void setRatingUrl(String ratingUrl) {
		this.ratingUrl = ratingUrl;
	}

	public int getReviewCount() {
		return reviewCount;
	}

	public void setReviewCount(int reviewCount) {
		this.reviewCount = reviewCount;
	}

	public String getDpShopUrl() {
		return dpShopUrl;
	}

	public void setDpShopUrl(String dpShopUrl) {
		this.dpShopUrl = dpShopUrl;
	}

	public boolean isHasCoupon() {
		return hasCoupon;
	}

	public void setHasCoupon(boolean hasCoupon) {
		this.hasCoupon = hasCoupon;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public String getCouponUrl() {
		return couponUrl;
	}

	public void setCouponUrl(String couponUrl) {
		this.couponUrl = couponUrl;
	}

	public boolean isHasDeal() {
		return hasDeal;
	}

	public void setHasDeal(boolean hasDeal) {
		this.hasDeal = hasDeal;
	}

	public List<DPDealDTO> getDealList() {
		return dealList;
	}

	public void setDealList(List<DPDealDTO> dealList) {
		this.dealList = dealList;
	}

	public int getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(int avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}

	public String getsPhotoUrl() {
		return sPhotoUrl;
	}

	public void setsPhotoUrl(String sPhotoUrl) {
		this.sPhotoUrl = sPhotoUrl;
	}
}
