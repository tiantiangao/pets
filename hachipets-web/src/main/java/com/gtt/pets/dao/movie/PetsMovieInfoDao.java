package com.gtt.pets.dao.movie;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.pets.entity.movie.PetsMovieInfo;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午10:10
 * To change this template use File | Settings | File Templates.
 */
public interface PetsMovieInfoDao extends GenericDao {

    @DaoAction(action = DaoActionType.QUERY)
    List<PetsMovieInfo> findListByMovieId(@DaoParam("movieId") int movieId);

}
