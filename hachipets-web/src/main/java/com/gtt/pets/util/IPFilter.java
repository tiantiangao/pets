package com.gtt.pets.util;

import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.kenshin.web.util.SpringLocator;
import com.gtt.pets.service.GlobalService;
import com.mysql.jdbc.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tiantiangao
 */
public class IPFilter {

	private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(IPFilter.class);

	/**
	 * IP过滤规则中的分隔符
	 */
	private static final String SPLIT_IP_FILTER = "[|]";

	/**
	 * IP过滤规则中用来模板匹配的字符
	 */
	private static final String CHAR_FUZZY_MATCH = "*";

	private static IPFilter instance = null;
	private static Object lock = new Object();

	private List<String> filterList = null;

	private IPFilter() {
		initIPFilterList();
	}

	/**
	 * 是否需要过滤该IP
	 *
	 * @param ip
	 * @return
	 */
	public boolean isFilter(String ip) {
		if (StringUtils.isNullOrEmpty(ip)) {
			return true;
		}
		for (String filter : filterList) {
			if (ip.trim().startsWith(filter)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 初始化IP过滤列表
	 */
	private void initIPFilterList() {
		try {
			filterList = new ArrayList<String>();
			// 从Lion中获取最新的值
			GlobalService globalService = SpringLocator.getBean(GlobalService.class);
			String value = globalService.get("userIpFilter");
			if (StringUtils.isNullOrEmpty(value)) {
				return;
			}

			// 解析IP过滤配置信息
			String[] ipList = value.split(SPLIT_IP_FILTER);
			for (String ip : ipList) {
				if (StringUtils.isNullOrEmpty(ip)) {
					continue;
				}
				ip = ip.trim();
				if (ip.endsWith(CHAR_FUZZY_MATCH)) {
					ip = ip.substring(0, ip.length() - 1);
				}
				addToFilterList(ip);
			}
		} catch (Exception e) {
			LOGGER.error("init IPFilter failed", e);
		}
	}

	private void addToFilterList(String ip) {
		if (filterList == null) {
			filterList = new ArrayList<String>();
		}
		filterList.add(ip);
	}

	public static final IPFilter instance() {
		if (instance == null) {
			synchronized (lock) {
				if (instance == null) {
					instance = new IPFilter();
				}
			}
		}
		return instance;
	}

}
