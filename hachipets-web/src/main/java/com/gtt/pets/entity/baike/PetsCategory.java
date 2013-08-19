package com.gtt.pets.entity.baike;

import com.gtt.pets.entity.BaseEntity;

/**
 * 宠物分类
 * 
 * @author tiantiangao
 */
public class PetsCategory extends BaseEntity {

	/**
	 * 分类ID
	 */
	private int id;
	/**
	 * 父分类ID
	 */
	private int parentId;
	/**
	 * 分类名称
	 */
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
