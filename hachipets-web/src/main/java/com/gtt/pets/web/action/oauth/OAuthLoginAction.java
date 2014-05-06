package com.gtt.pets.web.action.oauth;

import com.google.common.base.Preconditions;
import com.gtt.kenshin.oauth.OAuthClient;
import com.gtt.pets.service.GlobalService;
import com.gtt.pets.web.action.BaseAction;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * @author tiantiangao
 */
public class OAuthLoginAction extends BaseAction {

	private static final String INDEX = "INDEX";
	private String type;

	@Autowired
	private OAuthClient oAuthClient;
	@Autowired
	private GlobalService globalService;
	private String authUri;

	@Override
	protected String doExecute() throws Exception {
		try {
			Preconditions.checkArgument(isNotBlank(type));

			String redir = globalService.get("oAuthRedirectUri");
			redir = redir + "?type=" + type;

			authUri = oAuthClient.buildAuthorizeRequest(type, redir, RandomStringUtils.random(8));
			if (isBlank(authUri)) {
				return INDEX;
			}
			return SUCCESS;
		} catch (Exception e) {
			return INDEX;
		}
	}

	public String getAuthUri() {
		return authUri;
	}

	public void setType(String type) {
		this.type = type;
	}
}
