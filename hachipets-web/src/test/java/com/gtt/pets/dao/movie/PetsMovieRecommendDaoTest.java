package com.gtt.pets.dao.movie;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.kenshin.test.base.AbstractTest;

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
