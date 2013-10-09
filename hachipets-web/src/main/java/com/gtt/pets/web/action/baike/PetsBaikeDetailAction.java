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

import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.baike.PetsCategoryService;
import com.gtt.pets.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 宠物百科
 * 
 * @author tiantiangao
 */

public class PetsBaikeDetailAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Autowired
	private PetsCategoryService petsCategoryService;

	// 输入
	private int id;

	// 输出

	@Override
	public String doExecute() throws Exception {
		setChannel(ChannelType.CHANNEL_BAIKE);
		return SUCCESS;
	}
}
