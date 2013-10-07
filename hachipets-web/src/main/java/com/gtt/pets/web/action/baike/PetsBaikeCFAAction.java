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

import com.gtt.pets.bean.baike.PetsCFACatDTO;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.baike.PetsBaikeCFAService;
import com.gtt.pets.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 宠物百科
 *
 * @author tiantiangao
 */
public class PetsBaikeCFAAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PetsBaikeCFAService petsBaikeCFAService;
    private List<PetsCFACatDTO> catList;

    @Override
    protected String doExecute() throws Exception {
        setChannel(ChannelType.CHANNEL_BAIKE);

        catList = petsBaikeCFAService.findAll();
        return SUCCESS;
    }

    public List<PetsCFACatDTO> getCatList() {
        return catList;
    }
}
