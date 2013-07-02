/**
 * Project: bag
 * 
 * File Created at 2013-6-14
 * $Id$
 * 
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.gtt.bag.web.action;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ParameterAware;
import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

/**
 * Base Action
 * 
 * @author tiantiangao
 * 
 */
public class BaseAction extends ActionSupport implements RequestAware, ParameterAware, Preparable {

	private static final long serialVersionUID = 1L;

	protected Map<String, Object> request;

	protected Map<String, String[]> parameters;

	@Override
	public void prepare() throws Exception {
		request.put("projectName", "A包包");
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public void setParameters(Map<String, String[]> parameters) {
		this.parameters = parameters;
	}

	public String getCookieValue(String cookieName) {
		return getCookieValue(ServletActionContext.getRequest(), cookieName);
	}

	public String getCookieValue(String domainName, String cookieName) {
		return getCookieValue(ServletActionContext.getRequest(), domainName, cookieName);
	}

	private Cookie getCookie(HttpServletRequest request, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				if (name.equals(cookies[i].getName())) {
					return cookies[i];
				}
			}
		}
		return null;
	}

	private String getCookieValue(HttpServletRequest request, String name) {
		Cookie cookie = getCookie(request, name);
		return cookie != null ? cookie.getValue() : null;
	}

	private String getCookieValue(HttpServletRequest request, String domain, String name) {
		Cookie cookie = getCookie(request, domain, name);
		return cookie != null ? cookie.getValue() : null;
	}

	private Cookie getCookie(HttpServletRequest request, String domain, String name) {
		Cookie cookies[] = request.getCookies();
		if (cookies == null)
			return null;
		if (domain == null || domain.equalsIgnoreCase("localhost")) {
			for (Cookie cookie : cookies) {
				if (name.equals(cookie.getName())) {
					return cookie;
				}
			}
		}
		for (Cookie cookie : cookies) {
			if (domain.equals(cookie.getDomain()) && name.equals(cookie.getName())) {
				return cookie;
			}
		}
		return null;
	}

}
