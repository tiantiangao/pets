package com.gtt.pets.dao.baike;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsFCISection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午9:30 To change
 * this template use File | Settings | File Templates.
 */
public class PetsFCISectionDaoTest extends AbstractTest {

    @Autowired
    private PetsFCISectionDao petsFCISectionDao;

    @Test
    public void testLoadById() {
        PetsFCISection section = petsFCISectionDao.loadById(1);
        notNull(section);
        print(section);
    }

    @Test
    public void testFindAll() {
        List<PetsFCISection> sectionList = petsFCISectionDao.findAll();
        notNull(sectionList);
        for (PetsFCISection section : sectionList) {
            print(section);
        }
    }

    private void print(PetsFCISection section) {
        System.out.println(section.getGroupId() + "/" + section.getSectionId() + "/" + section.getSectionName() + "/" + section.getSectionEnName());
    }

}
