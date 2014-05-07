package com.gtt.pets.web.action.oauth;

import com.gtt.pets.web.action.BaseAction;
import com.gtt.pets.web.util.LoginUtils;

/**
 * @author tiantiangao
 */
public class LogoutAction extends BaseAction {

	@Override
	protected String doExecute() throws Exception {
		LoginUtils.signout();
		return SUCCESS;
	}
}
