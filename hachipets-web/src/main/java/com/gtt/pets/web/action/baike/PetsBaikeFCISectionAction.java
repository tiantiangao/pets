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
import com.gtt.pets.bean.baike.PetsFCISectionDogDTO;
import com.gtt.pets.constants.ChannelType;
import com.gtt.pets.service.baike.PetsBaikeFCIService;
import com.gtt.pets.web.action.BaseAction;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 宠物百科
 *
 * @author tiantiangao
 */
public class PetsBaikeFCISectionAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    private static final String RESULT_FCI = "FCI";

    @Autowired
    private PetsBaikeFCIService petsBaikeFCIService;

    // 输入
    private int sectionId;

    // 输出
    private PetsFCIGroupDTO group;
    private PetsFCISectionDTO section;

    private PetsFCIGroupDTO prevGroup;
    private PetsFCISectionDTO prevSection;

    private PetsFCIGroupDTO nextGroup;
    private PetsFCISectionDTO nextSection;

    private List<PetsFCISectionDogDTO> dogList;

    @Override
    protected String doExecute() throws Exception {
        setChannel(ChannelType.CHANNEL_BAIKE);

        // check param
        if (sectionId < 1) {
            return RESULT_FCI;
        }

        // load section info
        section = petsBaikeFCIService.loadSectionById(sectionId);
        if (section == null) {
            return RESULT_FCI;
        }

        // load group info
        group = petsBaikeFCIService.loadGroupById(section.getGroupId());
        if (group == null) {
            return RESULT_FCI;
        }

        // load prev section info
        int prevSectionId = sectionId - 1;
        prevSection = petsBaikeFCIService.loadSectionById(prevSectionId);
        if (prevSection != null && section.getGroupId() != prevSection.getGroupId()) {
            prevGroup = petsBaikeFCIService.loadGroupById(prevSection.getGroupId());
        }

        // load next section info
        int nextSectionId = sectionId + 1;
        nextSection = petsBaikeFCIService.loadSectionById(nextSectionId);
        if (nextSection != null && section.getGroupId() != nextSection.getGroupId()) {
            nextGroup = petsBaikeFCIService.loadGroupById(nextSection.getGroupId());
        }

        // load dog list
        dogList = petsBaikeFCIService.findAllDogListBySectionId(sectionId);

        return SUCCESS;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public PetsFCIGroupDTO getGroup() {
        return group;
    }

    public PetsFCISectionDTO getSection() {
        return section;
    }

    public List<PetsFCISectionDogDTO> getDogList() {
        return dogList;
    }

    public PetsFCISectionDTO getPrevSection() {
        return prevSection;
    }

    public PetsFCISectionDTO getNextSection() {
        return nextSection;
    }

    public PetsFCIGroupDTO getPrevGroup() {
        return prevGroup;
    }

    public PetsFCIGroupDTO getNextGroup() {
        return nextGroup;
    }
}
