package com.gtt.pets.dao.baike;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-10-8
 * Time: 下午9:31
 * To change this template use File | Settings | File Templates.
 */
public class PetsTypeDaoTest extends AbstractTest {

	@Autowired
	private PetsTypeDao petsTypeDao;

	@Test
	public void test() {
		PetsType petsType = petsTypeDao.loadByID(1);
		assertThat(petsType).isNotNull();

		PageModel<PetsType> model = petsTypeDao.findByRootCategory(10, 1, 20);
		assertThat(model).isNotNull();
		assertThat(model.getRecordCount()).isGreaterThan(0);

		model = petsTypeDao.findByCategory(103, 1, 20);
		assertThat(model).isNotNull();
		assertThat(model.getRecordCount()).isGreaterThan(0);
	}


}
