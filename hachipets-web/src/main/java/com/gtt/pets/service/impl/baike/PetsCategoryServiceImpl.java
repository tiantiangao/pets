package com.gtt.pets.service.impl.baike;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
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
	/**
	 * 分类ID与对象映射表
	 */
	private ConcurrentHashMap<Integer, PetsCategoryDTO> categoryMap;
	/**
	 * 分类ID与其完整父路径上各分类ID(正序, 包含当前分类本身)的映射表
	 */
	private ConcurrentHashMap<Integer, List<Integer>> parentPathMap;
	/**
	 * 分类ID与其所有子分类ID的映射表
	 */
	private ConcurrentHashMap<Integer, List<Integer>> childrenMap;
	/**
	 * 根分类列表
	 */
	private List<PetsCategoryDTO> rootCategoryList;
	/**
	 * 缓存是否已初始化标志
	 */
	private boolean initialized = false;
	/**
	 * 缓存初始化时的同步锁
	 */
	private Object lock = new Object();

	@Override
	public PetsCategoryDTO loadById(int id) {
		if (id < 1) {
			return null;
		}

		try {

			initCache();

			return categoryMap.get(id);
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

			initCache();

			List<Integer> childrenIDList = childrenMap.get(parentId);
			if (CollectionUtils.isEmpty(childrenIDList)) {
				return new ArrayList<PetsCategoryDTO>();
			}

			List<PetsCategoryDTO> result = new ArrayList<PetsCategoryDTO>();
			for (Integer childID : childrenIDList) {
				PetsCategoryDTO dto = categoryMap.get(childID);
				if (dto != null) {
					result.add((PetsCategoryDTO) dto.clone());
				}
			}

			return result;
		} catch (Exception e) {
			LOGGER.error("load category list by parentId failed: " + parentId, e);
			return new ArrayList<PetsCategoryDTO>();
		}
	}

	@Override
	public List<PetsCategoryDTO> findPathByCategoryId(int categoryId) {
		if (categoryId < 1) {
			return new ArrayList<PetsCategoryDTO>();
		}

		try {

			initCache();

			List<Integer> parentPathIDList = parentPathMap.get(categoryId);
			if (CollectionUtils.isEmpty(parentPathIDList)) {
				ArrayList<PetsCategoryDTO> result = new ArrayList<PetsCategoryDTO>();
				if (categoryMap.containsKey(categoryId)) {
					result.add(categoryMap.get(categoryId));
				}
				return result;
			}

			List<PetsCategoryDTO> result = new ArrayList<PetsCategoryDTO>();
			for (Integer childID : parentPathIDList) {
				PetsCategoryDTO dto = categoryMap.get(childID);
				if (dto != null) {
					result.add((PetsCategoryDTO) dto.clone());
				}
			}

			return result;
		} catch (Exception e) {
			LOGGER.error("load parent category path by categoryId failed: " + categoryId, e);
			return new ArrayList<PetsCategoryDTO>();
		}
	}

	@Override
	public List<PetsCategoryDTO> findRootCategories() {
		initCache();

		List<PetsCategoryDTO> result = new ArrayList<PetsCategoryDTO>();
		if (CollectionUtils.isEmpty(rootCategoryList)) {
			return result;
		}

		for (PetsCategoryDTO petsCategoryDTO : rootCategoryList) {
			result.add((PetsCategoryDTO) petsCategoryDTO.clone());
		}

		return result;
	}

	private void initCache() {
		// 检查是否已初始化
		if (initialized) {
			return;
		}

		synchronized (lock) {
			// 避免初次初始化时，执行多次，再判断一次是否已初始化
			if (initialized) {
				return;
			}

			long start = System.currentTimeMillis();

			categoryMap = new ConcurrentHashMap<Integer, PetsCategoryDTO>();
			parentPathMap = new ConcurrentHashMap<Integer, List<Integer>>();
			childrenMap = new ConcurrentHashMap<Integer, List<Integer>>();
			rootCategoryList = new ArrayList<PetsCategoryDTO>();
			Map<Integer, Integer> tempParentIDMap = new HashMap<Integer, Integer>();

			List<PetsCategory> categoryList = petsCategoryDao.findAll();
			List<PetsCategoryDTO> categoryDTOList = toDTOList(categoryList);
			for (PetsCategoryDTO categoryDTO : categoryDTOList) {
				// add to category map
				categoryMap.put(categoryDTO.getId(), categoryDTO);

				int parentId = categoryDTO.getParentId();
				if (parentId == 0) {
					// add to root category list
					rootCategoryList.add(categoryDTO);
					continue;
				}

				// add to parent category's children category id list
				if (!childrenMap.containsKey(parentId)) {
					childrenMap.put(parentId, new ArrayList<Integer>());
				}
				childrenMap.get(parentId).add((categoryDTO.getId()));

				// add to temp parent map
				tempParentIDMap.put(categoryDTO.getId(), parentId);
			}

			for (PetsCategoryDTO categoryDTO : categoryDTOList) {
				if (categoryDTO.getParentId() != 0) {
					ArrayList<Integer> parentPathIdList = new ArrayList<Integer>();
					fillParentCategory(categoryDTO.getId(), parentPathIdList, tempParentIDMap);
					Collections.reverse(parentPathIdList);
					parentPathMap.put(categoryDTO.getId(), parentPathIdList);
				}
			}

			LOGGER.info("init category data..." + (System.currentTimeMillis() - start) + "ms");

			// 设置为已初始化
			this.initialized = true;
		}
	}

	private void fillParentCategory(Integer currentId, List<Integer> idList, Map<Integer, Integer> parentIDMap) {
		idList.add(currentId);
		if (parentIDMap.containsKey(currentId)) {
			fillParentCategory(parentIDMap.get(currentId), idList, parentIDMap);
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
