package com.gtt.pets.service.baike;

import com.gtt.pets.bean.baike.PetsCFACatDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-7
 * Time: 下午4:04
 * To change this template use File | Settings | File Templates.
 */
public interface PetsBaikeCFAService {

    List<PetsCFACatDTO> findAll();

}
