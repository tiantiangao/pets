package com.gtt.pets.dao.movie;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.movie.PetsMovieYear;
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
public class PetsMovieYearDaoTest extends AbstractTest {

    @Autowired
    private PetsMovieYearDao petsMovieYearDao;

    @Test
    public void test() {
        notNull(petsMovieYearDao);
        List<PetsMovieYear> yearList = petsMovieYearDao.findMovieYearList();
        notNull(yearList);
        for (PetsMovieYear year : yearList) {
            System.out.println(year.getId() + ":" + year.getYear());
        }
        Integer year = petsMovieYearDao.findMovieYearByYearID(1);
        System.out.println(year);
    }

}
