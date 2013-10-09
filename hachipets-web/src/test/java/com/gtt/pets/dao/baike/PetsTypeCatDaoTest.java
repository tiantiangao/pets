package com.gtt.pets.dao.baike;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsTypeCat;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-10-8 Time: 下午9:31 To change
 * this template use File | Settings | File Templates.
 */
public class PetsTypeCatDaoTest extends AbstractTest {

	@Autowired
	private PetsTypeCatDao petsTypeCatDao;

	@Test
	public void test() {
		PetsTypeCat entity = petsTypeCatDao.findByTypeID(1);
		print(entity);
	}

	private void print(PetsTypeCat entity) {
		if (entity == null) {
			return;
		}
		List<String> list = new ArrayList<String>();
		list.add(entity.getId() + "");
		list.add(entity.getTypeId() + "");
		list.add(entity.getBodyType() + "");
		list.add(entity.getWeight() + "");
		list.add(entity.getCfa() + "");
		list.add(entity.getFeatureStick() + "");
		list.add(entity.getFeatureFeed() + "");
		list.add(entity.getFeatureBark() + "");
		list.add(entity.getFeatureFallHair() + "");
		list.add(entity.getFeatureOdor() + "");
		list.add(entity.getFeatureBeauty() + "");
		list.add(entity.getFeatureChildFriendly() + "");
		list.add(entity.getFeatureStrangerFriendly() + "");
		list.add(entity.getFeatureAnimalFriendly() + "");
		list.add(entity.getFeatureSport() + "");
		list.add(entity.getFeatureTrained() + "");
		list.add(entity.getFeatureDrool() + "");
		list.add(entity.getFeatureDrool() + "");
		list.add(entity.getFeatureCold() + "");
		list.add(entity.getFeatureHot() + "");
		list.add(entity.getFeatureCity() + "");
		list.add(entity.getOther());
		String info = StringUtils.join(list, "---");
		System.out.println(info);
	}

}
