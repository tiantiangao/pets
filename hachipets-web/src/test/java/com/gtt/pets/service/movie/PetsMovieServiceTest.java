package com.gtt.pets.service.movie;

import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.pets.bean.media.PetsMovieDTO;
import com.gtt.pets.dao.movie.PetsMovieDao;
import com.gtt.pets.dao.movie.PetsMovieHotDao;
import com.gtt.pets.dao.movie.PetsMovieRecommendDao;
import com.gtt.pets.entity.movie.PetsMovie;
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

		PetsMovieDTO mockDTO = Mockito.mock(PetsMovieDTO.class);
		Mockito.when(cacheService.get(Mockito.any(CacheKey.class))).thenReturn(mockDTO);
		movieId = 1;
		dto = petsMediaService.loadByMovieID(movieId);
		Assert.assertNotNull(dto);
		Assert.assertEquals(mockDTO, dto);

		Mockito.when(cacheService.get(Mockito.any(CacheKey.class))).thenReturn(null);
		Mockito.when(petsMovieDao.loadById(movieId)).thenReturn(null);
		dto = petsMediaService.loadByMovieID(movieId);
		Assert.assertNull(dto);

		String name = "name";
		String desc = "desc";
		String director = "director";
		String actor = "actor";
		String pic = "pic";
		String region = "region";
		int length = 90;
		Date release = Calendar.getInstance().getTime();
		int year = 2009;

		PetsMovie petsMovie = new PetsMovie();
		petsMovie.setId(movieId);
		petsMovie.setName(name);
		petsMovie.setDesc(desc);
		petsMovie.setDirector(director);
		petsMovie.setActor(actor);
		petsMovie.setPic(pic);
		petsMovie.setRegion(region);
		petsMovie.setLength(length);
		petsMovie.setRelease(release);
		petsMovie.setYear(year);
		Mockito.when(petsMovieDao.loadById(movieId)).thenReturn(petsMovie);
		dto = petsMediaService.loadByMovieID(movieId);
		Assert.assertNotNull(dto);
		Assert.assertEquals(movieId, dto.getId());
		Assert.assertEquals(name, dto.getName());
		Assert.assertEquals(desc, dto.getDesc());
		Assert.assertEquals(director, dto.getDirector());
		Assert.assertEquals(actor, dto.getActor());
		Assert.assertEquals(pic, dto.getPic());
		Assert.assertEquals(region, dto.getRegion());
		Assert.assertEquals(length, dto.getLength());
		Assert.assertEquals(release, dto.getRelease());
		Assert.assertEquals(year, dto.getYear());

	}

}
