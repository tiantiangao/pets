package com.gtt.pets.web.action.oauth;

import com.google.common.base.Preconditions;
import com.gtt.kenshin.oauth.OAuthClient;
import com.gtt.kenshin.oauth.ThirdUserInfo;
import com.gtt.kenshin.web.util.EncryptionUtils;
import com.gtt.pets.bean.account.AccountDTO;
import com.gtt.pets.bean.account.ThirdUserDTO;
import com.gtt.pets.service.GlobalService;
import com.gtt.pets.service.account.AccountService;
import com.gtt.pets.util.IPUtils;
import com.gtt.pets.util.WebUtils;
import com.gtt.pets.web.action.BaseAction;
import com.gtt.pets.web.util.LoginUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author tiantiangao
 */
public class TLoginAction extends BaseAction {

	@Autowired
	private OAuthClient oAuthClient;
	@Autowired
	private GlobalService globalService;
	@Autowired
	private AccountService accountService;

	private String type;
	private String userNickname;

	private boolean postBack;
	private String email;
	private String uid;
	private String token;

	private String err;

	@Override
	protected String doExecute() throws Exception {
		try {
			Preconditions.checkArgument(isNotBlank(type));
			Preconditions.checkArgument(ThirdType.getThirdValue(type) > 0);

			if (!postBack) {
				String redir = globalService.get("oAuthRedirectUri");
				redir = redir + "?type=" + type;
				ThirdUserInfo thirdUserInfo = oAuthClient.getUserInfo(type, req, redir);
				if (thirdUserInfo == null) {
					return "fail";
				}
				AccountDTO accountDTO =
						accountService.loadByThirdId(ThirdType.getThirdValue(type), thirdUserInfo.getThirdUserId());
				if (accountDTO != null) {
					ThirdUserDTO thirdUserDTO =
							accountService.loadByAccountID(accountDTO.getAccountId(), ThirdType.getThirdValue(type));

					if (!thirdUserDTO.getToken().equals(thirdUserInfo.getAccessToken())) {
						updateToken(accountDTO.getAccountId(), ThirdType.getThirdValue(type),
								thirdUserInfo.getThirdUserId(), thirdUserInfo.getAccessToken());
					}
					return signon(accountDTO.getAccountId());
				} else {
					// get nickname
					userNickname = oAuthClient.getUserNickname(type, thirdUserInfo);

					// check nickname exist
					accountDTO = accountService.loadByNickname(userNickname);
					if (accountDTO == null) {
						// not exit, auto reg
						int accountId = accountService.addAccount(userNickname, null, ThirdType.getThirdValue(type),
								thirdUserInfo.getThirdUserId(), thirdUserInfo.getAccessToken(), IPUtils.getUserIP(req));
						if (accountId > 0) {
							return signon(accountId);
						}
					} else {
						// exist, to reg page
						err = "nickname_exist";
					}
					uid = EncryptionUtils.encrypt(thirdUserInfo.getThirdUserId());
					token = EncryptionUtils.encrypt(thirdUserInfo.getAccessToken());
				}

				return SUCCESS;
			} else {

				if (StringUtils.isBlank(userNickname)) {
					err = "nickname_empty";
					return SUCCESS;
				}

				String decryptUid = EncryptionUtils.decrypt(uid);
				String decryptToken = EncryptionUtils.decrypt(token);

				if (StringUtils.isBlank(decryptUid) || StringUtils.isBlank(decryptToken)) {
					err = "system_err";
					return SUCCESS;
				}

				// check third id
				AccountDTO accountDTO = accountService.loadByThirdId(ThirdType.getThirdValue(type), decryptUid);

				if (accountDTO != null) {
					// check uid and token
					ThirdUserDTO thirdUserDTO =
							accountService.loadByAccountID(accountDTO.getAccountId(), ThirdType.getThirdValue(type));
					if (thirdUserDTO == null || !thirdUserDTO.getThirdId().equals(decryptUid)) {
						err = "system_err";
						return SUCCESS;
					} else {
						// update token
						if (!thirdUserDTO.getToken().equals(decryptToken)) {
							updateToken(accountDTO.getAccountId(), ThirdType.getThirdValue(type), decryptUid,
									decryptToken);
						}
						return signon(accountDTO.getAccountId());
					}
				}

				accountDTO = accountService.loadByNickname(userNickname);

				if (accountDTO != null) {
					err = "nickname_exist";
					return SUCCESS;
				}
				if (StringUtils.isNotBlank(email) && !WebUtils.isValidEmail(email)) {
					err = "email_invalid";
					return SUCCESS;
				}

				int accountId = accountService
						.addAccount(userNickname, email, ThirdType.getThirdValue(type), decryptUid, decryptToken,
								IPUtils.getUserIP(req));
				if (accountId > 0) {
					return signon(accountId);
				} else {
					err = "system_err";
					return SUCCESS;
				}
			}
		} catch (Exception e) {
			return "fail";
		}
	}

	private void updateToken(int accountId, int thirdType, String thirdUserId, String accessToken) {
		accountService.updateToken(accountId, thirdType, thirdUserId, accessToken);
	}

	private String signon(int accountId) {
		LoginUtils.signon(accountId, false);
		return "index";
	}

	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}

	public String getUserNickname() {
		return userNickname;
	}

	public String getType() {
		return type;
	}

	public void setPostBack(boolean postBack) {
		this.postBack = postBack;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getEmail() {
		return email;
	}

	public String getUid() {
		return uid;
	}

	public String getToken() {
		return token;
	}

	public String getErr() {
		return err;
	}

	public void setType(String type) {
		this.type = type;
	}
}
