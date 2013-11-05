package com.gtt.pets.web.vo;

import java.util.List;

/**
 * 宠物百科首页分类组VO
 * 
 * @author tiantiangao
 */
public class PetsBaikeIndexCategoryGroupVO {

	private String title;
	private List<PetsBaikeIndexCategoryVO> categoryList;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<PetsBaikeIndexCategoryVO> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<PetsBaikeIndexCategoryVO> categoryList) {
		this.categoryList = categoryList;
	}
}
