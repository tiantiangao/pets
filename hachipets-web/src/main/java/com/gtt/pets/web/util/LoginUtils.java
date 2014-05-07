package com.gtt.pets.web.util;

import com.gtt.kenshin.web.util.EncryptionUtils;
import com.gtt.pets.constants.Constants;
import com.gtt.pets.constants.Cookies;
import com.gtt.pets.util.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author tiantiangao
 */
public class LoginUtils {

	private static final int EXPIRE_DATE = 31;
	private static final int SECONDS_OF_DAY = 86400;
	private static final char SPLIT = '|';
	private static final String EQUAL = "=";
	private static final String SEMICOLON = ";";

	/**
	 * 登录
	 */
	public static void signon(int accountId, boolean keepLogin) {
		ServletActionContext.getRequest().setAttribute(Constants.CONTEXT_USER_ID, accountId);
		String token = EncryptionUtils.encrypt(buildAccountToken(accountId, keepLogin));
		addAccountCookie(token, keepLogin);
	}

	/**
	 * 退出登录
	 */
	public static void signout() {
		signout(ServletActionContext.getRequest(), ServletActionContext.getResponse());
	}

	/**
	 * 退出登录
	 */
	public static void signout(HttpServletRequest request, HttpServletResponse response) {
		// 从request中移除CONTEXT_USER_ID
		if (request != null) {
			request.removeAttribute(Constants.CONTEXT_USER_ID);
		}
		// 删除cookie[Account]
		addCookie(response, Cookies.Account, "", 0);
	}

	/**
	 * 从token中解析用户ID
	 */
	public static int parseUserId(String token) {
		try {
			String decrypt = EncryptionUtils.decrypt(token);
			String userId = resolveAccountToken(decrypt);
			if (StringUtils.isBlank(userId)) {
				return 0;
			} else {
				return Integer.parseInt(userId);
			}
		} catch (Exception e) {
			return 0;
		}
	}

	/**
	 * 构建token
	 */
	private static String buildAccountToken(int accountId, boolean keepLogin) {
		// 参数检查
		if (accountId < 1) {
			return "";
		}

		// 计算过期时间
		Calendar expireTime = Calendar.getInstance();
		if (keepLogin) {
			// 保持登录时,　token中存储过期时间为31天(cookie过期时间也是31天)
			expireTime.add(Calendar.DAY_OF_MONTH, EXPIRE_DATE);
		} else {
			// 未保持登录时, token中存储过期时间为8小时(cookie过期时间为会话cookie)
			expireTime.add(Calendar.HOUR_OF_DAY, 8);
		}

		StringBuffer sb = new StringBuffer();
		sb.append(accountId);
		sb.append(SPLIT);
		sb.append("0");
		sb.append(SPLIT);
		sb.append("0");
		sb.append(SPLIT);
		sb.append(expireTime.getTime().getTime() / 1000);
		sb.append(SPLIT);
		sb.append(keepLogin ? '1' : '0');

		return sb.toString();
	}

	/**
	 * 解析token
	 *
	 * @param token
	 * @return
	 */
	private static String resolveAccountToken(String token) {
		// 以'|'分隔token，确认格式
		String[] tokens = token.split("[|]");
		if (tokens == null || tokens.length < 5) {
			return null;
		}

		// 解析用户ID
		String userId = tokens[0];
		try {
			if (Integer.parseInt(userId) < 1) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}

		// 解析过期时间
		try {
			Double expired = Double.valueOf(tokens[3]);
			long currentSeconds = Calendar.getInstance().getTime().getTime() / 1000;
			if (expired.intValue() < currentSeconds) {
				// 判断token中的过期时间
				return null;
			}
		} catch (Exception e) {
			return null;
		}

		return userId;
	}

	private static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setDomain(WebUtils.getCookieDomain());
		cookie.setPath("/");
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	private static void addAccountCookie(String token, boolean keepLogin) {
		SimpleDateFormat cookieFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss zzz", Locale.ENGLISH);
		cookieFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

		int maxAge = -1;// 默认为会话cookie
		if (keepLogin && !isBlank(token)) {
			maxAge = EXPIRE_DATE * SECONDS_OF_DAY;
		}

		// 为给cookie添加HttpOnly属性，手动设置header[Set-Cookie]
		StringBuilder cookie = new StringBuilder();
		cookie.append(Cookies.Account);
		cookie.append(EQUAL);
		cookie.append(token);
		cookie.append(SEMICOLON);

		cookie.append("Domain");
		cookie.append(EQUAL);

		cookie.append(WebUtils.getCookieDomain());
		cookie.append(SEMICOLON);

		if (maxAge > 0) {
			cookie.append("Expires");
			cookie.append(EQUAL);
			Calendar now = Calendar.getInstance();
			now.add(Calendar.SECOND, maxAge);
			cookie.append(cookieFormat.format(now.getTime()));
			cookie.append(SEMICOLON);
		}

		cookie.append("Path");
		cookie.append(EQUAL);
		cookie.append("/");
		cookie.append(SEMICOLON);

		cookie.append("HttpOnly");

		ServletActionContext.getResponse().addHeader("Set-Cookie", cookie.toString());
	}
}
