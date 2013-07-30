package com.gtt.pets.dao.movie;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.kenshin.test.base.AbstractTest;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午9:30 To change
 * this template use File | Settings | File Templates.
 */
public class PetsMovieHotDaoTest extends AbstractTest {

	@Autowired
	private PetsMovieHotDao petsMovieHotDao;

	@Test
	public void test() {
		notNull(petsMovieHotDao);

		List<Integer> hotList = petsMovieHotDao.findHotList();
		notNull(hotList);
		for (Integer movie : hotList) {
			System.out.println(movie);
		}
	}

}
