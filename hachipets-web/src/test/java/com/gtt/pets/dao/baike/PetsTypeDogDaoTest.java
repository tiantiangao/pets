package com.gtt.pets.dao.baike;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsTypeDog;
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
public class PetsTypeDogDaoTest extends AbstractTest {

	@Autowired
	private PetsTypeDogDao petsTypeDogDao;

	@Test
	public void test() {
		PetsTypeDog entity = petsTypeDogDao.findByTypeID(1);
		print(entity);
	}

	private void print(PetsTypeDog entity) {
		if (entity == null) {
			return;
		}
		List<String> list = new ArrayList<String>();
		list.add(entity.getId() + "");
		list.add(entity.getTypeId() + "");
		list.add(entity.getFunction() + "");
		list.add(entity.getHairLength() + "");
		list.add(entity.getHeight() + "");
		list.add(entity.getWeight() + "");
		list.add(entity.getLife() + "");
		list.add(entity.getColor() + "");
		list.add(entity.getFciGroup() + "");
		list.add(entity.getFciSection() + "");
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
