package com.gtt.pets.web.action.oauth;

import com.google.common.base.Preconditions;
import com.gtt.kenshin.oauth.OAuthClient;
import com.gtt.kenshin.oauth.ThirdUserInfo;
import com.gtt.pets.service.GlobalService;
import com.gtt.pets.web.action.BaseAction;
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

	private String type;

	@Override
	protected String doExecute() throws Exception {
		try {
			Preconditions.checkArgument(isNotBlank(type));

			String redir = globalService.get("oAuthRedirectUri");
			redir = redir + "?type=" + type;
			ThirdUserInfo thirdUserInfo = oAuthClient.getUserInfo(type, req, redir);
			if (thirdUserInfo != null) {
				System.out.println(thirdUserInfo.getAccessToken() + " ----- " + thirdUserInfo.getThirdUserId());
			}

			return SUCCESS;
		} catch (Exception e) {
			return SUCCESS;
		}
	}

	public void setType(String type) {
		this.type = type;
	}
}
