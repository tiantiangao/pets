package com.gtt.pets.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * 实体基类
 * 
 * @author tiantiangao
 */
public abstract class BaseEntity implements Serializable {

	protected static final long serialVersionUID = 1L;

	/**
	 * 自增ID
	 */
	private long id;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}
