package com.gtt.pets.dao.movie;

import com.gtt.kenshin.dao.GenericDao;
import com.gtt.kenshin.dao.annotation.DaoAction;
import com.gtt.kenshin.dao.annotation.DaoActionType;
import com.gtt.kenshin.dao.annotation.DaoParam;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.entity.movie.PetsMovie;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午8:46 To change
 * this template use File | Settings | File Templates.
 */
public interface PetsMovieDao extends GenericDao {

    @DaoAction(action = DaoActionType.LOAD)
    PetsMovie loadById(@DaoParam("id") int id);

    @DaoAction(action = DaoActionType.PAGE)
    PageModel findMovieList(@DaoParam("region") String region, @DaoParam("notInRegionList") List<String> notInRegionList,
                            @DaoParam("year") int year, @DaoParam("afterYear") boolean afterYear,
                            @DaoParam("sort") String sort, @DaoParam("asc") boolean asc,
                            @DaoParam("page") int page, @DaoParam("max") int max);

    @DaoAction(action = DaoActionType.QUERY)
    List<PetsMovie> findByIdList(@DaoParam("idList") List<Integer> idList);

    @DaoAction(action = DaoActionType.QUERY)
    List<PetsMovie> findRandomMovieList(@DaoParam("id") int movieId);

}
