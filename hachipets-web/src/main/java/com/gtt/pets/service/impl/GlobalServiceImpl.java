package com.gtt.pets.service.impl;

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

	@Override
	public String get(String name) {
		if (StringUtils.isBlank(name)) {
			return null;
		}

		return globalConfigDao.get(name);
	}
}
