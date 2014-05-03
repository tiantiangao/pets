package com.gtt.pets.service.impl.city;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.city.CityGaodeDTO;
import com.gtt.pets.dao.city.CityGaodeDao;
import com.gtt.pets.entity.city.CityGaode;
import com.gtt.pets.service.city.CityGaodeService;
import com.gtt.pets.util.DTOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author tiantiangao
 */
@Service
public class CityGaodeServiceImpl implements CityGaodeService {

	private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(CityGaodeServiceImpl.class);

	@Autowired
	private CityGaodeDao cityGaodeDao;
	@Autowired
	private CacheService cacheService;

	@Override
	public CityGaodeDTO loadByCityName(String cityName) {
		if (StringUtils.isBlank(cityName)) {
			return null;
		}
		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.CITY_GAODE, cityName);
			CityGaodeDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			CityGaode entity = cityGaodeDao.loadByCityName(cityName);
			if (entity == null) {
				return null;
			}
			dto = DTOUtils.toDTO(CityGaodeDTO.class, entity);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("loadByCityName: cityName = [" + cityName + "]", e);
			return null;
		}
	}
}
