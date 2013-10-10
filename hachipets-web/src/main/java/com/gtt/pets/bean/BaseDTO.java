package com.gtt.pets.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-31 Time: 下午11:06 To change
 * this template use File | Settings | File Templates.
 */
public abstract class BaseDTO implements Serializable, Cloneable {

	protected static final long serialVersionUID = 1L;
	/**
	 * 自增ID
	 */
	private int id;
	/**
	 * 添加时间
	 */
	private Date addTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}

}
