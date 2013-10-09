package com.gtt.pets.dao.baike;

import java.util.List;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.pets.entity.baike.PetsTypeAttrName;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-10-8 Time: 下午8:45 To change
 * this template use File | Settings | File Templates.
 */
public interface PetsTypeAttrNameDao extends GenericDao {

	@DaoAction(action = DaoActionType.QUERY)
	List<PetsTypeAttrName> findAll();

}
