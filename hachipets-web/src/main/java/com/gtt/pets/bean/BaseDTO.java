package com.gtt.pets.bean;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-31 Time: 下午11:06 To change
 * this template use File | Settings | File Templates.
 */
public abstract class BaseDTO implements Serializable {

	protected static final long serialVersionUID = 1L;

	@Override
	public Object clone() {
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
		}
		return null;
	}

}
