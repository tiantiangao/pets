package com.gtt.pets.service.movie;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gtt.kenshin.cache.CacheService;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.dao.movie.PetsMovieDao;
import com.gtt.pets.dao.movie.PetsMovieHotDao;
import com.gtt.pets.dao.movie.PetsMovieRecommendDao;
import com.gtt.pets.service.impl.media.PetsMediaServiceImpl;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-8-1 Time: 上午7:23 To change
 * this template use File | Settings | File Templates.
 */
@RunWith(PowerMockRunner.class)
public class PetsMovieServiceTest {

	@InjectMocks
	private PetsMediaServiceImpl petsMediaService = new PetsMediaServiceImpl();
	@Mock
	private PetsMovieDao petsMovieDao;
	@Mock
	private PetsMovieHotDao petsMovieHotDao;
	@Mock
	private PetsMovieRecommendDao petsMovieRecommendDao;
	@Mock
	private CacheService cacheService;

	@Test
	public void testLoadByMovieID() {
		int movieId = 0;

		PetsMovieDTO dto = petsMediaService.loadByMovieID(movieId);
		Assert.assertNull(dto);



	}

}
