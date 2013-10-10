package com.gtt.pets.service.impl.baike;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.pets.bean.CacheKeyHolder;
import com.gtt.pets.bean.baike.PetsFCIGroupDTO;
import com.gtt.pets.bean.baike.PetsFCISectionDTO;
import com.gtt.pets.bean.baike.PetsFCISectionDogDTO;
import com.gtt.pets.dao.baike.PetsFCIGroupDao;
import com.gtt.pets.dao.baike.PetsFCISectionDao;
import com.gtt.pets.dao.baike.PetsFCISectionDogDao;
import com.gtt.pets.entity.baike.PetsFCIGroup;
import com.gtt.pets.entity.baike.PetsFCISection;
import com.gtt.pets.entity.baike.PetsFCISectionDog;
import com.gtt.pets.service.baike.PetsBaikeFCIService;
import com.gtt.pets.service.impl.BaseService;
import com.gtt.pets.util.DTOUtils;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-10-5 Time: 下午1:23 To change
 * this template use File | Settings | File Templates.
 */
@Service
public class PetsBaikeFCIServiceImpl extends BaseService implements PetsBaikeFCIService {

	@Autowired
	private PetsFCIGroupDao petsFCIGroupDao;
	@Autowired
	private PetsFCISectionDao petsFCISectionDao;
	@Autowired
	private PetsFCISectionDogDao petsFCISectionDogDao;
	@Autowired
	private CacheService cacheService;

	@Override
	public PetsFCIGroupDTO loadGroupById(int groupId) {
		if (groupId < 1) {
			return null;
		}
		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_FCI_GROUP, groupId);
			PetsFCIGroupDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsFCIGroup group = petsFCIGroupDao.loadById(groupId);
			if (group == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsFCIGroupDTO.class, group);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("load fci group by id failed", e);
			return null;
		}
	}

	@Override
	public List<PetsFCIGroupDTO> findAllGroup() {
		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_FCI_GROUP_LIST);
			List<PetsFCIGroupDTO> dtoList = cacheService.get(cacheKey);
			if (dtoList != null) {
				return dtoList;
			}

			// no cache, load from db
			List<PetsFCIGroup> groupList = petsFCIGroupDao.findAll();
			if (CollectionUtils.isEmpty(groupList)) {
				dtoList = new ArrayList<PetsFCIGroupDTO>();
			} else {
				dtoList = DTOUtils.toDTOList(PetsFCIGroupDTO.class, groupList);
			}

			// add cache
			cacheService.add(cacheKey, dtoList);

			return dtoList;
		} catch (Exception e) {
			LOGGER.error("find all fci group failed", e);
			return new ArrayList<PetsFCIGroupDTO>();
		}
	}

	@Override
	public PetsFCISectionDTO loadSectionById(int sectionId) {
		if (sectionId < 1) {
			return null;
		}
		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_FCI_SECTION, sectionId);
			PetsFCISectionDTO dto = cacheService.get(cacheKey);
			if (dto != null) {
				return dto;
			}

			// no cache, load from db
			PetsFCISection section = petsFCISectionDao.loadById(sectionId);
			if (section == null) {
				return null;
			}
			dto = DTOUtils.toDTO(PetsFCISectionDTO.class, section);

			// add cache
			cacheService.add(cacheKey, dto);

			return dto;
		} catch (Exception e) {
			LOGGER.error("load fci section by id failed", e);
			return null;
		}
	}

	@Override
	public List<PetsFCISectionDTO> findAllSection() {
		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_FCI_SECTION_LIST);
			List<PetsFCISectionDTO> dtoList = cacheService.get(cacheKey);
			if (dtoList != null) {
				return dtoList;
			}

			// no cache, load from db
			List<PetsFCISection> sectionList = petsFCISectionDao.findAll();
			if (CollectionUtils.isEmpty(sectionList)) {
				dtoList = new ArrayList<PetsFCISectionDTO>();
			} else {
				dtoList = DTOUtils.toDTOList(PetsFCISectionDTO.class, sectionList);
			}

			// add cache
			cacheService.add(cacheKey, dtoList);

			return dtoList;
		} catch (Exception e) {
			LOGGER.error("find all fci section failed", e);
			return new ArrayList<PetsFCISectionDTO>();
		}
	}

	@Override
	public List<PetsFCISectionDogDTO> findAllDogListBySectionId(int sectionId) {
		try {
			// load from cache
			CacheKey cacheKey = new CacheKey(CacheKeyHolder.BAIKE_FCI_SECTION_DOG_LIST);
			List<PetsFCISectionDogDTO> dtoList = cacheService.get(cacheKey);
			if (dtoList != null) {
				return dtoList;
			}

			// no cache, load from db
			List<PetsFCISectionDog> dogList = petsFCISectionDogDao.findBySectionId(sectionId);
			if (CollectionUtils.isEmpty(dogList)) {
				dtoList = new ArrayList<PetsFCISectionDogDTO>();
			} else {
				dtoList = DTOUtils.toDTOList(PetsFCISectionDogDTO.class, dogList);
			}

			// add cache
			cacheService.add(cacheKey, dtoList);

			return dtoList;
		} catch (Exception e) {
			LOGGER.error("find all fci section dog list failed", e);
			return new ArrayList<PetsFCISectionDogDTO>();
		}
	}

}
