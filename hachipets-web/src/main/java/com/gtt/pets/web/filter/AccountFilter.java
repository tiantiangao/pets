package com.gtt.pets.web.filter;

import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.constants.Constants;
import com.gtt.pets.constants.Cookies;
import com.gtt.pets.web.util.LoginUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tiantiangao
 */
public class AccountFilter implements Filter {

    private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(AccountFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // 解析用户cookie
        setUserInfo(request, response);

        // 检查登录用户是否在黑名单
        //		if (isInBlackList(request)) {
        //			logout(request, response);
        //			return;
        //		}

        chain.doFilter(request, response);
    }

    /**
     * set user id in cookie
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void setUserInfo(ServletRequest request, ServletResponse response) throws IOException {
        String encryptUserInfoString = getCookie((HttpServletRequest) request, Cookies.Account);

        if (StringUtils.isEmpty(encryptUserInfoString)) {
            return;
        }

        try {
            int userId = LoginUtils.parseUserId(encryptUserInfoString);
            request.setAttribute(Constants.CONTEXT_USER_ID, userId);
        } catch (Exception e) {
            // may be invalid token
            LoginUtils.signout((HttpServletRequest) request, (HttpServletResponse) response);
        }
    }

    private String getCookie(HttpServletRequest request, String name) {
        Cookie cookies[] = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (name.equals(cookie.getName())) {
                return cookie != null ? cookie.getValue() : null;
            }
        }
        return null;
    }

//	private boolean isInBlackList(ServletRequest request) {
//		int userId = Integer.parseInt((String) request.getAttribute(Constants.CONTEXT_USER_ID));
//
//		// 用户不存在
//		if (userId <= 0) {
//			return false;
//		}
//
//		// 排除不需要检查的请求
//		String uri = (String) request.getAttribute(ORIGINAL_URL);
//		// 未使用url rewrite过滤器，直接从request中获取当前的requestURI
//		if (StringUtils.isBlank(uri)) {
//			uri = ((HttpServletRequest) request).getRequestURI();
//		}
//
//		// 取出黑名单阻止需要过滤的地址
//		String logoutExcludePattern = "";
//		if (StringUtils.isNotBlank(logoutExcludePattern)) {
//			Matcher matcher = Pattern.compile(logoutExcludePattern, Pattern.CASE_INSENSITIVE).matcher(uri);
//			if (matcher.matches()) {
//				// 匹配到当前地址，不需要再跳转到/logout
//				return false;
//			}
//		}
//
//		// 查找黑名单列表
//		return userBlackListService.isInBlackList(userID);
//	}

    /**
     * 强制退出
     *
     * @param request
     * @param response
     * @throws IOException
     */
    private void logout(ServletRequest request, ServletResponse response) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.sendRedirect(httpResponse.encodeRedirectURL("/logout"));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void destroy() {
    }

}
