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
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.pets.bean.baike.*;
import com.gtt.pets.constants.CategoryType;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.baike.PetsCategoryService;
import com.gtt.pets.service.baike.PetsTypeService;
import com.gtt.pets.web.action.BaseAction;

/**
 * 宠物百科
 * 
 * @author tiantiangao
 */
public class PetsBaikeDetailAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private static final String INDEX = "baike";
	@Autowired
	private PetsCategoryService petsCategoryService;
	@Autowired
	private PetsTypeService petsTypeService;
	// 输入
	private int id;
	// 输出
	private PetsTypeDTO type;
	private List<PetsCategoryDTO> categoryPath;
	private List<PetsTypeAttrVO> attrList;
	private List<PetsTypeFeatureVO> featureList;

	@Override
	public String doExecute() throws Exception {
		setChannel(ChannelType.CHANNEL_BAIKE);

		if (id < 1) {
			return INDEX;
		}

		// 读取类型信息
		type = petsTypeService.loadTypeByID(id);
		if (type == null) {
			return INDEX;
		}

		// 读取分类路径信息
		categoryPath = petsCategoryService.findPathByCategoryId(type.getCategoryId());

		// 读取属性名映射表
		Map<String, PetsTypeAttrNameDTO> attrNameMap = petsTypeService.loadTypeAttrNameMapByGroup(type
				.getRootCategoryId());
		attrNameMap.putAll(petsTypeService.loadTypeAttrNameMapByGroup(0));

		attrList = new ArrayList<PetsTypeAttrVO>();
		initBasicAttr(attrNameMap);
		initExtraAttr(attrNameMap);

		return SUCCESS;
	}

	/**
	 * 初始化扩展信息
	 * 
	 * @param attrNameMap
	 */
	private void initExtraAttr(Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		switch (type.getRootCategoryId()) {
		case CategoryType.CATEGORY_DOG:
			initDogAttr(attrNameMap);
			break;
		case CategoryType.CATEGORY_CAT:
			break;
		case CategoryType.CATEGORY_FISH:
			break;
		case CategoryType.CATEGORY_RABBIT:
			break;
		default:
			break;
		}
	}

	/**
	 * 初始化宠物狗扩展信息
	 * 
	 * @param attrNameMap
	 */
	private void initDogAttr(Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		PetsTypeDogDTO dog = petsTypeService.loadTypeDogByID(type.getId());
		if (dog == null) {
			return;
		}
		addAttr("dogFunction", show(dog.getFunction()), attrNameMap);
		addAttr("dogHairLength", show(dog.getHairLength()), attrNameMap);
		addAttr("dogHeight", show(dog.getHeight()), attrNameMap);
		addAttr("dogWeight", show(dog.getWeight()), attrNameMap);
		addAttr("dogLife", show(dog.getLife()), attrNameMap);
		addAttr("dogColor", show(dog.getColor()), attrNameMap);
	}

	private String show(String value) {
		return StringUtils.isBlank(value) ? "暂无" : value;
	}

	/**
	 * 初始化公共的基本信息
	 * 
	 * @param attrNameMap
	 */
	private void initBasicAttr(Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		addAttr("name", show(type.getName()), attrNameMap);
		addAttr("enName", show(type.getEnName()), attrNameMap);
		addAttr("alias", show(type.getAlias()), attrNameMap);
		addAttr("origin", show(type.getOrigin()), attrNameMap);
		addAttr("type", categoryPath.get(categoryPath.size() - 1).getName(), attrNameMap);
	}

	/**
	 * 添加属性至属性列表
	 * 
	 * @param name
	 * @param value
	 * @param attrNameMap
	 */
	private void addAttr(String name, String value, Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		PetsTypeAttrVO attrVO = new PetsTypeAttrVO();
		PetsTypeAttrNameDTO attrNameDTO = attrNameMap.get(name);
		attrVO.setName(attrNameDTO.getShowName());
		attrVO.setValue(value);
		attrVO.setSingleLine(attrNameDTO.getSingleLine() == 1);
		attrList.add(attrVO);
	}

	public void setId(int id) {
		this.id = id;
	}

	public PetsTypeDTO getType() {
		return type;
	}

	public List<PetsCategoryDTO> getCategoryPath() {
		return categoryPath;
	}

	public List<PetsTypeAttrVO> getAttrList() {
		return attrList;
	}

	public List<PetsTypeFeatureVO> getFeatureList() {
		return featureList;
	}
}
