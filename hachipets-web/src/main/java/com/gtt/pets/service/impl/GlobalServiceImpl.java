package com.gtt.pets.service.impl;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.dao.GlobalConfigDao;
import com.gtt.pets.service.GlobalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 全局配置服务
 *
 * @author tiantiangao
 */
@Service
public class GlobalServiceImpl extends BaseService implements GlobalService {

	@Autowired
	private GlobalConfigDao globalConfigDao;
	@Autowired
	private CacheService cacheService;

	@Override
	public String get(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}

		// load from cache
		CacheKey cacheKey = new CacheKey(CacheKeyHolder.GLOBAL_CONFIG, name);
		String config = cacheService.get(cacheKey);
		if (null != config) {
			return config;
		}

		// no cache, load from db
		config = globalConfigDao.get(name);

		if (StringUtils.isBlank(config)) {
			config = "";
		}

		// add cache
		cacheService.add(cacheKey, config);

		return config;
	}
}
