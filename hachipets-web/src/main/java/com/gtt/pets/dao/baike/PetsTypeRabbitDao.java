package com.gtt.pets.dao.baike;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.pets.entity.baike.PetsTypeRabbit;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-8
 * Time: 下午8:45
 * To change this template use File | Settings | File Templates.
 */
public interface PetsTypeRabbitDao extends GenericDao {

    @DaoAction(action = DaoActionType.LOAD)
    PetsTypeRabbit findByTypeID(@DaoParam("typeId") int typeId);

}
