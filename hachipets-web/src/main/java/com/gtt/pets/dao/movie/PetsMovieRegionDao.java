package com.gtt.pets.dao.movie;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午8:46 To change
 * this template use File | Settings | File Templates.
 */
public interface PetsMovieRegionDao extends GenericDao {

    @DaoAction(action = DaoActionType.QUERY)
    List<String> findMovieRegionList();

}
