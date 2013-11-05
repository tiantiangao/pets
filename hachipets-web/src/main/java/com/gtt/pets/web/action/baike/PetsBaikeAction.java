/**
 * Project: bag
 *
 * File Created at 2013-6-14
 * $Id$
 *
 * Copyright 2010 dianping.com.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Dianping Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license agreement you entered into
 * with dianping.com.
 */
package com.gtt.pets.web.action.baike;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.pets.bean.baike.PetsCategoryDTO;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.baike.PetsCategoryService;
import com.gtt.pets.web.action.BaseAction;
import com.gtt.pets.web.vo.PetsBaikeIndexCategoryGroupVO;
import com.gtt.pets.web.vo.PetsBaikeIndexCategoryVO;
import org.springframework.util.CollectionUtils;

/**
 * 宠物百科
 * 
 * @author tiantiangao
 */

public class PetsBaikeAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final String BAIKE_INDEX_RESULT = "baikeIndex";
	@Autowired
	private PetsCategoryService petsCategoryService;
	// 输入
	private int categoryId = 10;// 默认为狗狗分类
	// 输出
	private List<PetsCategoryDTO> rootCategories;
	private List<PetsBaikeIndexCategoryGroupVO> categoryGroupList;

	@Override
	public String doExecute() throws Exception {
		setChannel(ChannelType.CHANNEL_BAIKE);

		if (categoryId < 0) {
			return BAIKE_INDEX_RESULT;
		}

		PetsCategoryDTO category = petsCategoryService.loadById(categoryId);
		if (category == null) {
			return BAIKE_INDEX_RESULT;
		}

		List<PetsCategoryDTO> path = new ArrayList<PetsCategoryDTO>();
		if (category.getParentId() == 0) {
			path.add(category);
		} else {
			path = petsCategoryService.findPathByCategoryId(categoryId);
		}
		preparePageCategoryData(path);
		return SUCCESS;
	}

	private void preparePageCategoryData(List<PetsCategoryDTO> path) {
		rootCategories = petsCategoryService.findRootCategories();
		categoryGroupList = new ArrayList<PetsBaikeIndexCategoryGroupVO>();

		// first level category list
		int firstCategoryId = path.get(0).getId();
		categoryGroupList.add(generateCategoryGroupVO(rootCategories, firstCategoryId, "宠物", false, 0));

		// second level category list
		List<PetsCategoryDTO> secondCategoryList = petsCategoryService.findByParentId(firstCategoryId);
		int secondCategoryId = path.size() > 1 ? path.get(1).getId() : 0;
		categoryGroupList
				.add(generateCategoryGroupVO(secondCategoryList, secondCategoryId, "类型", true, firstCategoryId));

		// third level category List
		List<PetsCategoryDTO> thirdCategoryList;
		if (secondCategoryId != 0) {
			thirdCategoryList = petsCategoryService.findByParentId(secondCategoryId);
		} else {
			thirdCategoryList = new ArrayList<PetsCategoryDTO>();
			for (PetsCategoryDTO secondCategory : secondCategoryList) {
				thirdCategoryList.addAll(petsCategoryService.findByParentId(secondCategory.getId()));
			}
		}
		if(CollectionUtils.isEmpty(thirdCategoryList)){
			return;
		}
		int thirdCategoryId = path.size() > 2 ? path.get(2).getId() : 0;
		categoryGroupList
				.add(generateCategoryGroupVO(thirdCategoryList, thirdCategoryId, "分类", true, secondCategoryId));
	}

	private PetsBaikeIndexCategoryGroupVO generateCategoryGroupVO(List<PetsCategoryDTO> categoryList,
			int checkedCategoryId, String title, boolean withAllTag, int allTagParentCategoryId) {
		PetsBaikeIndexCategoryGroupVO categoryGroupVO = new PetsBaikeIndexCategoryGroupVO();
		List<PetsBaikeIndexCategoryVO> categoryVOList = new ArrayList<PetsBaikeIndexCategoryVO>();
		if (withAllTag) {
			PetsBaikeIndexCategoryVO categoryVO = new PetsBaikeIndexCategoryVO();
			PetsCategoryDTO allTagCategory = new PetsCategoryDTO();
			allTagCategory.setId(allTagParentCategoryId);
			allTagCategory.setName("全部");
			categoryVO.setCategory(allTagCategory);
			categoryVO.setChecked(checkedCategoryId == 0);
			categoryVOList.add(categoryVO);
		}
		for (PetsCategoryDTO categoryDTO : categoryList) {
			PetsBaikeIndexCategoryVO categoryVO = new PetsBaikeIndexCategoryVO();
			categoryVO.setCategory(categoryDTO);
			categoryVO.setChecked(checkedCategoryId == categoryDTO.getId());
			categoryVOList.add(categoryVO);
		}
		categoryGroupVO.setCategoryList(categoryVOList);
		categoryGroupVO.setTitle(title);
		return categoryGroupVO;
	}

	public List<PetsBaikeIndexCategoryGroupVO> getCategoryGroupList() {
		return categoryGroupList;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
}
