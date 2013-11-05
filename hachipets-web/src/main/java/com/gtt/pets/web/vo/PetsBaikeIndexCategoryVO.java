package com.gtt.pets.web.vo;

import com.gtt.pets.bean.baike.PetsCategoryDTO;

/**
 * 宠物百科首页分类对象VO
 * 
 * @author tiantiangao
 */
public class PetsBaikeIndexCategoryVO {

	private PetsCategoryDTO category;
	private boolean checked;

	public PetsCategoryDTO getCategory() {
		return category;
	}

	public void setCategory(PetsCategoryDTO category) {
		this.category = category;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}
}
