package com.gtt.pets.dao.city;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.pets.entity.city.CityGaode;

/**
 * 高德城市数据DAO
 *
 * @author tiantiangao
 */
public interface CityGaodeDao extends GenericDao {

	@DaoAction(action = DaoActionType.LOAD)
	CityGaode loadByCityName(@DaoParam("cityName") String cityName);

}
