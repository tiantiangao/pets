package com.gtt.pets.dao.movie;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.movie.PetsMovieInfo;
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
public class PetsMovieInfoDaoTest extends AbstractTest {

    @Autowired
    private PetsMovieInfoDao petsMovieInfoDao;

    @Test
    public void test() {
        notNull(petsMovieInfoDao);

        int movieId = 1;
        List<PetsMovieInfo> movieInfoList = petsMovieInfoDao.findListByMovieId(movieId);
        notNull(movieInfoList);
        for (PetsMovieInfo movie : movieInfoList) {
            System.out.println(movie.getMovieId() + "/" + movie.getInfoType() + "/" + movie.getRefer());
        }
    }

}
