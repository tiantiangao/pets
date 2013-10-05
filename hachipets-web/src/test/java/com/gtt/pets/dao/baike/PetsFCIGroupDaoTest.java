package com.gtt.pets.dao.baike;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsCategory;
import com.gtt.pets.entity.baike.PetsFCIGroup;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午9:30 To change
 * this template use File | Settings | File Templates.
 */
public class PetsFCIGroupDaoTest extends AbstractTest {

	@Autowired
	private PetsFCIGroupDao petsFCIGroupDao;

	@Test
	public void testLoadById() {
		PetsFCIGroup group = petsFCIGroupDao.loadById(1);
		notNull(group);
		print(group);
	}

    @Test
	public void testFindAll() {
		List<PetsFCIGroup> groupList= petsFCIGroupDao.findAll();
		notNull(groupList);
		for (PetsFCIGroup group: groupList) {
			print(group);
		}
	}

	private void print(PetsFCIGroup group) {
		System.out.println(group.getGroupId() + "/" + group.getGroupName() + "/" + group.getGroupEnName());
	}

}
