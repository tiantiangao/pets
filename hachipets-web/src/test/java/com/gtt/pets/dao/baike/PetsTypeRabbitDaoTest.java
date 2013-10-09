package com.gtt.pets.dao.baike;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsTypeFish;
import com.gtt.pets.entity.baike.PetsTypeRabbit;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-10-8 Time: 下午9:31 To change
 * this template use File | Settings | File Templates.
 */
public class PetsTypeRabbitDaoTest extends AbstractTest {

	@Autowired
	private PetsTypeRabbitDao petsTypeRabbitDao;

	@Test
	public void test() {
		PetsTypeRabbit entity = petsTypeRabbitDao.findByTypeID(1);
		print(entity);
	}

	private void print(PetsTypeRabbit entity) {
		if (entity == null) {
			return;
		}
		List<String> list = new ArrayList<String>();
		list.add(entity.getId() + "");
		list.add(entity.getTypeId() + "");
		list.add(entity.getBodyType() + "");
		list.add(entity.getEatPattern() + "");
		list.add(entity.getEatPattern() + "");
		list.add(entity.getOther());
		String info = StringUtils.join(list, "---");
		System.out.println(info);
	}

}
