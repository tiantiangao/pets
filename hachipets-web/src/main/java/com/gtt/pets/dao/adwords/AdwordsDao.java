package com.gtt.pets.dao.adwords;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.pets.entity.adwords.AdwordsEntity;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 14-5-10
 * Time: 下午3:33
 * To change this template use File | Settings | File Templates.
 */
public interface AdwordsDao extends GenericDao {

    @DaoAction(action = DaoActionType.LOAD)
    AdwordsEntity load(@DaoParam("adwordsId") int adwordsId, @DaoParam("typeId") int typeId);

}
