package com.gtt.pets.dao.baike;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsCategory;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午9:30 To change
 * this template use File | Settings | File Templates.
 */
public class PetsCategoryDaoTest extends AbstractTest {

	@Autowired
	private PetsCategoryDao petsCategoryDao;

	@Test
	public void testLoadById() {
		PetsCategory category = petsCategoryDao.loadById(1);
		notNull(category);
		print(category);
	}

	@Test
	public void testFindByParentId() {
		List<PetsCategory> categoryList = petsCategoryDao.findByParentId(0);
		notNull(categoryList);
		for (PetsCategory category : categoryList) {
			print(category);
		}

		categoryList = petsCategoryDao.findByParentId(1);
		notNull(categoryList);
		for (PetsCategory category : categoryList) {
			print(category);
		}
	}

	private void print(PetsCategory category) {
		System.out.println(category.getId() + "/" + category.getName() + "/" + category.getParentId());
	}

}
