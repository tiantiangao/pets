package com.gtt.pets.service.impl.baike;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.baike.*;
import com.gtt.pets.dao.baike.*;
import com.gtt.pets.entity.baike.*;
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

	private Map<Integer, Map<String, PetsTypeAttrNameDTO>> typeAttrNameMap;
	private Object lock = new Object();
	@Autowired
	private PetsTypeDao petsTypeDao;
	@Autowired
	private PetsTypeCommonDao petsTypeCommonDao;
	@Autowired
	private PetsTypeDogDao petsTypeDogDao;
	@Autowired
	private PetsTypeCatDao petsTypeCatDao;
	@Autowired
	private PetsTypeFishDao petsTypeFishDao;
	@Autowired
	private PetsTypeRabbitDao petsTypeRabbitDao;
	@Autowired
	private PetsTypeAttrNameDao petsTypeAttrNameDao;
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
		if (typeId < 1) {
			return null;
		}

		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_TYPE_COMMON, typeId);
			PetsTypeCommonDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsTypeCommon entity = petsTypeCommonDao.findByTypeID(typeId);
			if (entity == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsTypeCommonDTO.class, entity);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("load type common by id failed", e);
			return null;
		}
	}

	@Override
	public PetsTypeDogDTO loadTypeDogByID(int typeId) {
		if (typeId < 1) {
			return null;
		}

		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_TYPE_DOG, typeId);
			PetsTypeDogDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsTypeDog entity = petsTypeDogDao.findByTypeID(typeId);
			if (entity == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsTypeDogDTO.class, entity);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("load type dog by id failed", e);
			return null;
		}
	}

	@Override
	public PetsTypeCatDTO loadTypeCatByID(int typeId) {
		if (typeId < 1) {
			return null;
		}

		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_TYPE_CAT, typeId);
			PetsTypeCatDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsTypeCat entity = petsTypeCatDao.findByTypeID(typeId);
			if (entity == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsTypeCatDTO.class, entity);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("load type cat by id failed", e);
			return null;
		}
	}

	@Override
	public PetsTypeFishDTO loadTypeFishByID(int typeId) {
		if (typeId < 1) {
			return null;
		}

		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_TYPE_FISH, typeId);
			PetsTypeFishDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsTypeFish entity = petsTypeFishDao.findByTypeID(typeId);
			if (entity == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsTypeFishDTO.class, entity);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("load type fish by id failed", e);
			return null;
		}
	}

	@Override
	public PetsTypeRabbitDTO loadTypeRabbitByID(int typeId) {
		if (typeId < 1) {
			return null;
		}

		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_TYPE_RABBIT, typeId);
			PetsTypeRabbitDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsTypeRabbit entity = petsTypeRabbitDao.findByTypeID(typeId);
			if (entity == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsTypeRabbitDTO.class, entity);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("load type reabbit by id failed", e);
			return null;
		}
	}

	@Override
	public Map<String, PetsTypeAttrNameDTO> loadTypeAttrNameMapByGroup(int group) {
		if (group < 0) {
			return null;
		}

		initCacheMap();
		return typeAttrNameMap.get(group);
	}

	private void initCacheMap() {
		if (typeAttrNameMap == null) {
			synchronized (lock) {
				if (typeAttrNameMap == null) {
					typeAttrNameMap = new ConcurrentHashMap<Integer, Map<String, PetsTypeAttrNameDTO>>();
					List<PetsTypeAttrName> attrNameList = petsTypeAttrNameDao.findAll();
					for (PetsTypeAttrName attrName : attrNameList) {
						int group = attrName.getGroup();

						if (!typeAttrNameMap.containsKey(group)) {
							typeAttrNameMap.put(group, new ConcurrentHashMap<String, PetsTypeAttrNameDTO>());
						}

						typeAttrNameMap.get(group).put(attrName.getAttrName(),
								DTOUtils.toDTO(PetsTypeAttrNameDTO.class, attrName));
					}
				}
			}
		}
	}
}
