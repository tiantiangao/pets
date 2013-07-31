package com.gtt.pets.dao.movie;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;

/**
 * 推荐电影Dao
 * 
 * @author tiantiangao
 */
public interface PetsMovieRecommendDao extends GenericDao{

	/**
	 * 查找最新推荐电影
	 * 
	 * @return
	 */
	@DaoAction(action = DaoActionType.LOAD)
	Integer findRecommendMovieID();

}
