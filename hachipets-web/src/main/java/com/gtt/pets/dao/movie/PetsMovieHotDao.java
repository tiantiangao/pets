package com.gtt.pets.dao.movie;

import java.util.List;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;

/**
 * 
 * @author tiantiangao
 */
public interface PetsMovieHotDao extends GenericDao {

	/**
	 * 查找电影热门榜
	 * 
	 * @return
	 */
	@DaoAction(action = DaoActionType.QUERY)
	List<Integer> findHotList();

}
