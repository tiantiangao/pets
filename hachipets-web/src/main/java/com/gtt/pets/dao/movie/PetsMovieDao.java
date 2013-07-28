package com.gtt.pets.dao.movie;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.entity.movie.PetsMovie;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午8:46
 * To change this template use File | Settings | File Templates.
 */
public interface PetsMovieDao extends GenericDao {

    @DaoAction(action = DaoActionType.LOAD)
    PetsMovie loadById(@DaoParam("id") int id);

    @DaoAction(action = DaoActionType.PAGE)
    PageModel findMovieList(@DaoParam("sort") String sort, @DaoParam("asc") boolean asc, @DaoParam("page") int page, @DaoParam("max") int max);

}
