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
package com.gtt.pets.web.action.bbs;

import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.web.action.BaseAction;

/**
 * 宠物社区
 *
 * @author tiantiangao
 */

public class PetsBBSAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Override
    public String execute() throws Exception {
        setChannel(ChannelType.CHANNEL_BBS);
        return SUCCESS;
    }
}