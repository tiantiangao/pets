package com.gtt.pets.dao.baike;

import java.util.List;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.pets.entity.baike.PetsCategory;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午8:46 To change
 * this template use File | Settings | File Templates.
 */
public interface PetsCategoryDao extends GenericDao {

	@DaoAction(action = DaoActionType.LOAD)
	PetsCategory loadById(@DaoParam("id") int id);

	@DaoAction(action = DaoActionType.QUERY)
	List<PetsCategory> findByParentId(@DaoParam("id") int parentId);

	@DaoAction(action = DaoActionType.QUERY)
	List<PetsCategory> findAll();

}
