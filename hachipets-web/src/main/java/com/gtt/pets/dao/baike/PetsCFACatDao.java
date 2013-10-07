package com.gtt.pets.dao.baike;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.pets.entity.baike.PetsCFACat;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-7
 * Time: 下午3:57
 * To change this template use File | Settings | File Templates.
 */
public interface PetsCFACatDao extends GenericDao{

    @DaoAction(action = DaoActionType.QUERY)
    List<PetsCFACat> findAll();

}
