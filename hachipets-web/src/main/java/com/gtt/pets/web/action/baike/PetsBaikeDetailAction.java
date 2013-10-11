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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.pets.bean.baike.*;
import com.gtt.pets.constants.CategoryType;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.baike.PetsBaikeCFAService;
import com.gtt.pets.service.baike.PetsBaikeFCIService;
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
	private PetsBaikeFCIService petsBaikeFCIService;
	@Autowired
	private PetsBaikeCFAService petsBaikeCFAService;
	@Autowired
	private PetsTypeService petsTypeService;
	// 输入
	private int id;
	// 输出
	private PetsTypeDTO type;
	private List<PetsCategoryDTO> categoryPath;
	private List<PetsTypeAttrVO> attrList;
	private List<PetsTypeFeatureVO> featureList;
	private String desc;
	private Map<String, Object> extraInfo;

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
		featureList = new ArrayList<PetsTypeFeatureVO>();
		extraInfo = new HashMap<String, Object>();
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
			initCatAttr(attrNameMap);
			break;
		case CategoryType.CATEGORY_FISH:
			initFishAttr(attrNameMap);
			break;
		case CategoryType.CATEGORY_RABBIT:
			initRabbitAttr(attrNameMap);
			break;
		default:
			initCommonAttr(attrNameMap);
			break;
		}
	}

	private void initCommonAttr(Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		PetsTypeCommonDTO common = petsTypeService.loadTypeCommonByID(type.getId());
		if (common == null) {
			return;
		}

		// fill other desc
		desc = common.getOther();
	}

	private void initRabbitAttr(Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		PetsTypeRabbitDTO rabbit = petsTypeService.loadTypeRabbitByID(type.getId());
		if (rabbit == null) {
			return;
		}

		// fill attr info
		addAttr("rabbitWeight", show(rabbit.getWeight()), attrNameMap);
		addAttr("rabbitBodyType", show(rabbit.getBodyType()), attrNameMap);
		addAttr("rabbitEatPattern", show(rabbit.getEatPattern()), attrNameMap);

		// fill other desc
		desc = rabbit.getOther();
	}

	private void initFishAttr(Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		PetsTypeFishDTO fish = petsTypeService.loadTypeFishByID(type.getId());
		if (fish == null) {
			return;
		}

		// fill attr info
		addAttr("fishClimaticZone", show(fish.getClimaticZone()), attrNameMap);
		addAttr("fishTemperatur", show(fish.getTemperature()), attrNameMap);
		addAttr("fishWaterPH", show(fish.getWaterPH()), attrNameMap);

		// fill other desc
		desc = fish.getOther();
	}

	private void initCatAttr(Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		PetsTypeCatDTO cat = petsTypeService.loadTypeCatByID(type.getId());
		if (cat == null) {
			return;
		}

		// fill attr info
		addAttr("catBodyType", show(cat.getBodyType()), attrNameMap);
		addAttr("catWeight", show(cat.getWeight()), attrNameMap);

		int cfaId = cat.getCfa();
		PetsCFACatDTO cfaCatDTO = petsBaikeCFAService.loadById(cfaId);
		if (cfaCatDTO != null) {
			addAttr("catCFA", cfaCatDTO.getEnName(), attrNameMap);
			extraInfo.put("catCFALink", cfaCatDTO.getCfaLink());
		}

		// fill feature info
		addFeature("catFeatureStick", cat.getFeatureStick(), attrNameMap);
		addFeature("catFeatureFeed", cat.getFeatureFeed(), attrNameMap);
		addFeature("catFeatureBark", cat.getFeatureBark(), attrNameMap);
		addFeature("catFeatureFallHair", cat.getFeatureFallHair(), attrNameMap);
		addFeature("catFeatureOdor", cat.getFeatureOdor(), attrNameMap);
		addFeature("catFeatureBeauty", cat.getFeatureBeauty(), attrNameMap);
		addFeature("catFeatureChildFriendly", cat.getFeatureChildFriendly(), attrNameMap);
		addFeature("catFeatureStrangerFriendly", cat.getFeatureStrangerFriendly(), attrNameMap);
		addFeature("catFeatureAnimalFriendly", cat.getFeatureAnimalFriendly(), attrNameMap);
		addFeature("catFeatureSport", cat.getFeatureSport(), attrNameMap);
		addFeature("catFeatureTrained", cat.getFeatureTrained(), attrNameMap);
		addFeature("catFeatureDrool", cat.getFeatureDrool(), attrNameMap);
		addFeature("catFeatureCold", cat.getFeatureCold(), attrNameMap);
		addFeature("catFeatureHot", cat.getFeatureHot(), attrNameMap);
		addFeature("catFeatureCity", cat.getFeatureCity(), attrNameMap);

		// fill other desc
		desc = cat.getOther();
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

		// fill attr info
		addAttr("dogFunction", show(dog.getFunction()), attrNameMap);
		addAttr("dogHairLength", show(dog.getHairLength()), attrNameMap);
		addAttr("dogHeight", show(dog.getHeight()), attrNameMap);
		addAttr("dogWeight", show(dog.getWeight()), attrNameMap);
		addAttr("dogLife", show(dog.getLife()), attrNameMap);
		addAttr("dogColor", show(dog.getColor()), attrNameMap);

		int fciGroupID = dog.getFciGroup();
		PetsFCIGroupDTO fciGroupDTO = petsBaikeFCIService.loadGroupById(fciGroupID);
		if (fciGroupDTO != null) {
			addAttr("dogFCIGroup", fciGroupDTO.getGroupName(), attrNameMap);
			extraInfo.put("dogFCIGroupID", fciGroupID);
		}

		int fciSectionID = dog.getFciSection();
		PetsFCISectionDTO fciSectionDTO = petsBaikeFCIService.loadSectionById(fciSectionID);
		if (fciSectionDTO != null) {
			addAttr("dogFCISection", fciSectionDTO.getSectionName(), attrNameMap);
			extraInfo.put("dogFCISectionID", fciSectionID);
		}

		// fill feature info
		addFeature("dogFeatureStick", dog.getFeatureStick(), attrNameMap);
		addFeature("dogFeatureFeed", dog.getFeatureFeed(), attrNameMap);
		addFeature("dogFeatureBark", dog.getFeatureBark(), attrNameMap);
		addFeature("dogFeatureFallHair", dog.getFeatureFallHair(), attrNameMap);
		addFeature("dogFeatureOdor", dog.getFeatureOdor(), attrNameMap);
		addFeature("dogFeatureBeauty", dog.getFeatureBeauty(), attrNameMap);
		addFeature("dogFeatureChildFriendly", dog.getFeatureChildFriendly(), attrNameMap);
		addFeature("dogFeatureStrangerFriendly", dog.getFeatureStrangerFriendly(), attrNameMap);
		addFeature("dogFeatureAnimalFriendly", dog.getFeatureAnimalFriendly(), attrNameMap);
		addFeature("dogFeatureSport", dog.getFeatureSport(), attrNameMap);
		addFeature("dogFeatureTrained", dog.getFeatureTrained(), attrNameMap);
		addFeature("dogFeatureDrool", dog.getFeatureDrool(), attrNameMap);
		addFeature("dogFeatureCold", dog.getFeatureCold(), attrNameMap);
		addFeature("dogFeatureHot", dog.getFeatureHot(), attrNameMap);
		addFeature("dogFeatureCity", dog.getFeatureCity(), attrNameMap);

		// fill other desc
		desc = dog.getOther();
	}

	/**
	 * 添加特征信息
	 * 
	 * @param name
	 * @param value
	 * @param attrNameMap
	 */
	private void addFeature(String name, int value, Map<String, PetsTypeAttrNameDTO> attrNameMap) {
		PetsTypeFeatureVO featureVO = new PetsTypeFeatureVO();
		featureVO.setName(attrNameMap.get(name).getShowName());
		featureVO.setValue(value);
		featureList.add(featureVO);
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
		attrVO.setAttrName(name);
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

	public String getDesc() {
		return desc;
	}

	public Map<String, Object> getExtraInfo() {
		return extraInfo;
	}
}
