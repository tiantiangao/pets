package com.gtt.pets.dao.movie;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.kenshin.test.base.AbstractTest;
import com.gtt.pets.entity.movie.PetsMovie;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午9:30
 * To change this template use File | Settings | File Templates.
 */
public class PetsMovieDaoTest extends AbstractTest {

    @Autowired
    private PetsMovieDao petsMovieDao;

    @Test
    public void test() {
        notNull(petsMovieDao);

        PageModel model = petsMovieDao.findMovieList("addTime", true, 1, 2);
        notNull(model);
        List<PetsMovie> records = (List<PetsMovie>) model.getRecords();
        if (records != null) {
            for (PetsMovie movie : records) {
                System.out.println(movie.getId() + " - " + movie.getName() + " - " + movie.getDesc() + " - " + movie.getDirector() + " - " + movie.getActor() + " - " + movie.getRegion() + " - " + movie.getLength()
                        + " - " + movie.getRelease() + " - " + movie.getAddTime());
            }
        }
    }

}
