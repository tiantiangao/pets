package com.gtt.pets.service.impl.baike;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.baike.PetsCategoryDTO;
import com.gtt.pets.dao.baike.PetsCategoryDao;
import com.gtt.pets.entity.baike.PetsCategory;
import com.gtt.pets.service.baike.PetsCategoryService;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午3:19 To change
 * this template use File | Settings | File Templates.
 */
@Service
public class PetsCategoryServiceImpl implements PetsCategoryService {

	private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(PetsCategoryServiceImpl.class);
	@Autowired
	private PetsCategoryDao petsCategoryDao;
	@Autowired
	private CacheService cacheService;

	@Override
	public PetsCategoryDTO loadById(int id) {
		if (id < 1) {
			return null;
		}

		try {
			// load from cache
			CacheKey categoryCacheKey = new CacheKey(CacheKeyHolder.CATEGORY, id);
			PetsCategoryDTO categoryDTO = cacheService.get(categoryCacheKey);
			if (categoryDTO != null) {
				return categoryDTO;
			}

			// no cache, load from db
			PetsCategory petsCategory = petsCategoryDao.loadById(id);
			if (petsCategory == null) {
				return null;
			}
			categoryDTO = toDTO(petsCategory);

			// add cache
			cacheService.add(categoryCacheKey, categoryDTO);

			return categoryDTO;
		} catch (Exception e) {
			LOGGER.error("load category by id failed", e);
			return null;
		}
	}

	private PetsCategoryDTO toDTO(PetsCategory petsCategory) {
		PetsCategoryDTO dto = new PetsCategoryDTO();
		BeanUtils.copyProperties(petsCategory, dto);
		return dto;
	}

	@Override
	public List<PetsCategoryDTO> findByParentId(int parentId) {
		if (parentId < 1) {
			return new ArrayList<PetsCategoryDTO>();
		}

		try {
			// load from cache
			CacheKey categoryListCacheKey = new CacheKey(CacheKeyHolder.CATEGORY_LIST, parentId);
			List<PetsCategoryDTO> dtoList = cacheService.get(categoryListCacheKey);
			if (dtoList != null) {
				return dtoList;
			}

			// no cache, load from db
			List<PetsCategory> categoryList = petsCategoryDao.findByParentId(parentId);
			if (CollectionUtils.isEmpty(categoryList)) {
				dtoList = new ArrayList<PetsCategoryDTO>();
			} else {
				dtoList = toDTOList(categoryList);
			}

			// add cache
			cacheService.add(categoryListCacheKey, dtoList);
			return dtoList;
		} catch (Exception e) {
			LOGGER.error("load category list by parentId failed: " + parentId, e);
			return null;
		}
	}

	private List<PetsCategoryDTO> toDTOList(List<PetsCategory> categoryList) {
		List<PetsCategoryDTO> dtoList = new ArrayList<PetsCategoryDTO>();
		for (PetsCategory category : categoryList) {
			dtoList.add(toDTO(category));
		}

		return dtoList;
	}
}
