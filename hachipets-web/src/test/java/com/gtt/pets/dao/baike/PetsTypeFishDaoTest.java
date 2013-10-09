package com.gtt.pets.dao.baike;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsTypeFish;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-10-8 Time: 下午9:31 To change
 * this template use File | Settings | File Templates.
 */
public class PetsTypeFishDaoTest extends AbstractTest {

	@Autowired
	private PetsTypeFishDao petsTypeFishDao;

	@Test
	public void test() {
		PetsTypeFish entity = petsTypeFishDao.findByTypeID(1);
		print(entity);
	}

	private void print(PetsTypeFish entity) {
		if (entity == null) {
			return;
		}
		List<String> list = new ArrayList<String>();
		list.add(entity.getId() + "");
		list.add(entity.getTypeId() + "");
		list.add(entity.getClimaticZone() + "");
		list.add(entity.getTemperature() + "");
		list.add(entity.getWaterPH() + "");
		list.add(entity.getOther());
		String info = StringUtils.join(list, "---");
		System.out.println(info);
	}

}
