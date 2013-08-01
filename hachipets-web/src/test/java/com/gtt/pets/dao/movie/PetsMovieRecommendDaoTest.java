package com.gtt.pets.dao.movie;

import com.gtt.pets.dao.AbstractTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午9:30 To change
 * this template use File | Settings | File Templates.
 */
public class PetsMovieRecommendDaoTest extends AbstractTest {

    @Autowired
    private PetsMovieRecommendDao petsMovieRecommendDao;

    @Test
    public void test() {
        notNull(petsMovieRecommendDao);

        Integer movieID = petsMovieRecommendDao.findRecommendMovieID();
        notNull(movieID);
        System.out.println(movieID);
    }

}
