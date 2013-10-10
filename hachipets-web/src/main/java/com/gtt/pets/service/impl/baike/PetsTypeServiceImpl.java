package com.gtt.pets.service.impl.baike;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.baike.*;
import com.gtt.pets.dao.baike.PetsTypeDao;
import com.gtt.pets.entity.baike.PetsType;
import com.gtt.pets.service.baike.PetsTypeService;
import com.gtt.pets.service.impl.BaseService;
import com.gtt.pets.util.DTOUtils;

/**
 * 宠物类型服务实现
 * 
 * @author tiantiangao
 */
@Service
public class PetsTypeServiceImpl extends BaseService implements PetsTypeService {

	@Autowired
	private PetsTypeDao petsTypeDao;
	@Autowired
	private CacheService cacheService;

	@Override
	public PetsTypeDTO loadTypeByID(int typeId) {
		if (typeId < 1) {
			return null;
		}

		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_TYPE, typeId);
			PetsTypeDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsType entity = petsTypeDao.loadByID(typeId);
			if (entity == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsTypeDTO.class, entity);

			// add cache
			cacheService.add(cacheKey, dto);
			return dto;
		} catch (Exception e) {
			LOGGER.error("load type by id failed", e);
			return null;
		}
	}

	@Override
	public PetsTypeCommonDTO loadTypeCommonByID(int typeId) {
		return null;
	}

	@Override
	public PetsTypeDogDTO loadTypeDogByID(int typeId) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public PetsTypeCatDTO loadTypeCatByID(int typeId) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public PetsTypeFishDTO loadTypeFishByID(int typeId) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}

	@Override
	public PetsTypeRabbitDTO loadTypeRabbitByID(int typeId) {
		return null; // To change body of implemented methods use File |
						// Settings | File Templates.
	}
}
