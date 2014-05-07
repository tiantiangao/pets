package com.gtt.pets.entity.account;

import com.gtt.pets.entity.BaseEntity;

/**
 * @author tiantiangao
 */
public class AccountEntity extends BaseEntity {

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
