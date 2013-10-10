package com.gtt.pets.dao.baike;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsTypeAttrName;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-10-8 Time: 下午9:31 To change
 * this template use File | Settings | File Templates.
 */
public class PetsTypeAttrNameDaoTest extends AbstractTest {

	@Autowired
	private PetsTypeAttrNameDao petsTypeAttrNameDao;

	@Test
	public void test() {
		List<PetsTypeAttrName> list = petsTypeAttrNameDao.findAll();
		print(list);
	}

	private void print(List<PetsTypeAttrName> list) {
		if (list == null) {
			return;
		}
		List<String> result = new ArrayList<String>();
		for (PetsTypeAttrName name : list) {
			result.add(name.getId() + "");
			result.add(name.getGroup() + "");
			result.add(name.getAttrName() + "");
			result.add(name.getShowName() + "");
			result.add(name.getSingleLine() + "");
			String info = StringUtils.join(result, "---");
			System.out.println(info);
		}

	}

}
