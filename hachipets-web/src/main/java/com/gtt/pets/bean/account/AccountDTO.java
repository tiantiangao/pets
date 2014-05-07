package com.gtt.pets.bean.account;

import com.gtt.pets.bean.BaseDTO;

/**
 * @author tiantiangao
 */
public class AccountDTO extends BaseDTO {

	private int accountId;
	private String nickname;
	private String email;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
