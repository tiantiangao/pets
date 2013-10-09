package com.gtt.pets.entity.baike;

import com.gtt.pets.entity.BaseEntity;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-10-8 Time: 下午8:14 To change
 * this template use File | Settings | File Templates.
 */
public class PetsTypeAttrName extends BaseEntity {

	/**
	 * 分组
	 */
	private int group;
	/**
	 * 属性名称
	 */
	private String attrName;
	/**
	 * 显示名称(html)
	 */
	private String showName;

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public String getShowName() {
		return showName;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}
}
