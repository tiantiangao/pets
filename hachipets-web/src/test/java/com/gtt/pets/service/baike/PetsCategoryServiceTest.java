package com.gtt.pets.service.baike;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import com.gtt.kenshin.cache.CacheKey;
import com.gtt.kenshin.cache.CacheService;
import com.gtt.pets.bean.baike.PetsCategoryDTO;
import com.gtt.pets.dao.baike.PetsCategoryDao;
import com.gtt.pets.entity.baike.PetsCategory;
import com.gtt.pets.service.impl.baike.PetsCategoryServiceImpl;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-8-1 Time: 上午7:23 To change
 * this template use File | Settings | File Templates.
 */
@RunWith(PowerMockRunner.class)
public class PetsCategoryServiceTest {

	@InjectMocks
	private PetsCategoryServiceImpl petsCategoryService = new PetsCategoryServiceImpl();
	@Mock
	private PetsCategoryDao petsCategoryDao;
	@Mock
	private CacheService cacheService;

	@Test
	public void testLoadById() {
		PetsCategoryDTO category = petsCategoryService.loadById(0);
		Assert.assertNull(category);

		PetsCategoryDTO dto = Mockito.mock(PetsCategoryDTO.class);
		Mockito.when(cacheService.get(Mockito.any(CacheKey.class))).thenReturn(dto);
		category = petsCategoryService.loadById(1);
		Assert.assertNotNull(category);
		Assert.assertEquals(dto, category);

		Mockito.when(cacheService.get(Mockito.any(CacheKey.class))).thenReturn(null);
		Mockito.when(petsCategoryDao.loadById(Mockito.anyInt())).thenReturn(null);
		category = petsCategoryService.loadById(1);
		Assert.assertNull(category);

		int id = 1;
		int parentId = 0;
		String name = "狗狗";

		PetsCategory mockEntity = new PetsCategory();
		mockEntity.setId(id);
		mockEntity.setParentId(parentId);
		mockEntity.setName(name);
		Mockito.when(petsCategoryDao.loadById(Mockito.anyInt())).thenReturn(mockEntity);

		category = petsCategoryService.loadById(1);
		Assert.assertNotNull(category);
		Assert.assertEquals(id, category.getId());
		Assert.assertEquals(parentId, category.getParentId());
		Assert.assertEquals(name, category.getName());
	}

}
