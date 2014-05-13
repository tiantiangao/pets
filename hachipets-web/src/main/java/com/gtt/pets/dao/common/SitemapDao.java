package com.gtt.pets.dao.common;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.entity.common.SitemapEntity;

/**
 * @author tiantiangao
 */
public interface SitemapDao extends GenericDao {

	@DaoAction(action = DaoActionType.PAGE)
	PageModel<SitemapEntity> pageSiteMap(@DaoParam("page") int page, @DaoParam("max") int max);

}
