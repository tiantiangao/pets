package com.gtt.pets.dao.baike;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.entity.baike.PetsType;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-8
 * Time: 下午8:45
 * To change this template use File | Settings | File Templates.
 */
public interface PetsTypeDao extends GenericDao {

	@DaoAction(action = DaoActionType.LOAD)
	PetsType loadByID(@DaoParam("id") int id);

	@DaoAction(action = DaoActionType.PAGE)
	PageModel<PetsType> findByRootCategory(@DaoParam("category") int category, @DaoParam("page") int page,
										   @DaoParam("max") int max);

	@DaoAction(action = DaoActionType.PAGE)
	PageModel<PetsType> findByCategory(@DaoParam("category") int category, @DaoParam("page") int page,
									   @DaoParam("max") int max);
}
