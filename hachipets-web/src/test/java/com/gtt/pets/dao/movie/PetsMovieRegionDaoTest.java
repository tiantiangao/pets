package com.gtt.pets.dao.movie;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.movie.PetsMovieRegion;
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
public class PetsMovieRegionDaoTest extends AbstractTest {

    @Autowired
    private PetsMovieRegionDao petsMovieRegionDao;

    @Test
    public void test() {
        notNull(petsMovieRegionDao);
        List<PetsMovieRegion> regionList = petsMovieRegionDao.findMovieRegionList();
        notNull(regionList);
        for (PetsMovieRegion region : regionList) {
            System.out.println(region.getId() + ":" + region.getRegion());
        }
        String region = petsMovieRegionDao.findMovieRegionById(100);
        System.out.println(region);
    }

}
