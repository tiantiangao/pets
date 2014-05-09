package com.gtt.pets.dao.common;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.pets.entity.common.StaticFileEntity;

import java.util.List;

/**
 * @author tiantiangao
 */
public interface StaticFileDao extends GenericDao {

	@DaoAction(action = DaoActionType.QUERY)
	List<StaticFileEntity> findStaticFileMD5();

}
