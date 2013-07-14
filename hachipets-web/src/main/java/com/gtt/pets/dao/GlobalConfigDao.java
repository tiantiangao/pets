package com.gtt.pets.dao;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;

/**
 * 全局配置Dao
 * 
 * @author tiantiangao
 */
public interface GlobalConfigDao extends GenericDao {

	@DaoAction(action = DaoActionType.LOAD)
	String get(@DaoParam("name") String name);

}
