package com.gtt.pets.service.baike;

import com.gtt.pets.bean.baike.PetsFCIGroupDTO;
import com.gtt.pets.bean.baike.PetsFCISectionDTO;
import com.gtt.pets.entity.baike.PetsFCISection;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
public interface PetsBaikeFCIService {

    PetsFCIGroupDTO loadGroupById(int groupId);

    List<PetsFCIGroupDTO> findAllGroup();

    PetsFCISectionDTO loadSectionById(int sectionId);

    List<PetsFCISectionDTO> findAllSection();

}
