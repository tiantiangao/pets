package com.gtt.pets.bean.account;

import com.gtt.pets.bean.BaseDTO;

/**
 * @author tiantiangao
 */
public class ThirdUserDTO extends BaseDTO {

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
