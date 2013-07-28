package com.gtt.pets.dao.movie;

import com.gtt.kenshin.test.base.AbstractTest;
import com.gtt.pets.entity.movie.PetsMoviePlay;
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
public class PetsMoviePlayDaoTest extends AbstractTest {

    @Autowired
    private PetsMoviePlayDao petsMoviePlayDao;

    @Test
    public void test() {
        notNull(petsMoviePlayDao);

        int movieId = 1;
        List<PetsMoviePlay> movieInfoList = petsMoviePlayDao.findListByMovieId(movieId);
        notNull(movieInfoList);
        for (PetsMoviePlay movie : movieInfoList) {
            System.out.println(movie.getMovieId() + "/" + movie.getPlayType() + "/" + movie.getAddress());
        }
    }

}
