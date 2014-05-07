package com.gtt.pets.entity.account;

import com.gtt.pets.entity.BaseEntity;

/**
 * @author tiantiangao
 */
public class ThirdUserEntity extends BaseEntity {

	private int accountId;
	private int thirdType;
	private String thirdId;
	private String token;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getThirdType() {
		return thirdType;
	}

	public void setThirdType(int thirdType) {
		this.thirdType = thirdType;
	}

	public String getThirdId() {
		return thirdId;
	}

	public void setThirdId(String thirdId) {
		this.thirdId = thirdId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
