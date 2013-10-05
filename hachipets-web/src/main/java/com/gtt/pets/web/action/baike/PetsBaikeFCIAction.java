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

import com.gtt.pets.bean.baike.PetsFCIGroupDTO;
import com.gtt.pets.bean.baike.PetsFCISectionDTO;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.baike.PetsBaikeFCIService;
import com.gtt.pets.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宠物百科
 *
 * @author tiantiangao
 */
public class PetsBaikeFCIAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Autowired
    private PetsBaikeFCIService petsBaikeFCIService;

    // 输出
    private List<PetsFCIGroupDTO> groupList;
    private Map<String, List<PetsFCISectionDTO>> sectionMap;

    @Override
    protected String doExecute() throws Exception {
        setChannel(ChannelType.CHANNEL_BAIKE);

        groupList = petsBaikeFCIService.findAllGroup();
        List<PetsFCISectionDTO> sectionList = petsBaikeFCIService.findAllSection();
        sectionMap = new HashMap<String, List<PetsFCISectionDTO>>();
        for (PetsFCISectionDTO section : sectionList) {
            String groupId = String.valueOf(section.getGroupId());
            if (sectionMap.containsKey(groupId)) {
                sectionMap.get(groupId).add(section);
            } else {
                List<PetsFCISectionDTO> list = new ArrayList<PetsFCISectionDTO>();
                list.add(section);
                sectionMap.put(groupId, list);
            }
        }

        return SUCCESS;
    }

    public List<PetsFCIGroupDTO> getGroupList() {
        return groupList;
    }

    public Map<String, List<PetsFCISectionDTO>> getSectionMap() {
        return sectionMap;
    }
}
