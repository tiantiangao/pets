package com.gtt.pets.util;

import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.kenshin.oauth.impl.util.LogProxy;

/**
 * @author tiantiangao
 */
public class OAuthLogProxyImpl implements LogProxy {

	private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(OAuthLogProxyImpl.class);

	@Override
	public void info(String msg) {
		LOGGER.info(msg);
	}

	@Override
	public void err(String msg, Exception e) {
		LOGGER.error(msg, e);
	}
}
