package com.gtt.pets.service.impl.baike;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.baike.PetsCFACatDTO;
import com.gtt.pets.dao.baike.PetsCFACatDao;
import com.gtt.pets.entity.baike.PetsCFACat;
import com.gtt.pets.service.baike.PetsBaikeCFAService;
import com.gtt.pets.service.impl.BaseService;
import com.gtt.pets.util.DTOUtils;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-10-7 Time: 下午4:05 To change
 * this template use File | Settings | File Templates.
 */
@Service
public class PetsBaikeCFAServiceImpl extends BaseService implements PetsBaikeCFAService {

	@Autowired
	private PetsCFACatDao petsCFACatDao;
	@Autowired
	private CacheService cacheService;

	@Override
	public List<PetsCFACatDTO> findAll() {
		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_CFA_CAT_LIST);
			List<PetsCFACatDTO> dtoList = cacheService.get(cacheKey);
			if (dtoList != null) {
				return dtoList;
			}

			// no cache, load from db
			List<PetsCFACat> catList = petsCFACatDao.findAll();
			if (CollectionUtils.isEmpty(catList)) {
				dtoList = new ArrayList<PetsCFACatDTO>();
			} else {
				dtoList = DTOUtils.toDTOList(PetsCFACatDTO.class, catList);
			}

			// add cache
			cacheService.add(cacheKey, dtoList);

			return dtoList;
		} catch (Exception e) {
			LOGGER.error("find all cfa cat failed", e);
			return new ArrayList<PetsCFACatDTO>();
		}
	}

	@Override
	public PetsCFACatDTO loadById(int cfaId) {
		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_CFA_CAT);
			PetsCFACatDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsCFACat cat = petsCFACatDao.loadById(cfaId);
			if (cat == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsCFACatDTO.class, cat);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("load cfa cat by id failed", e);
			return null;
		}
	}
}
