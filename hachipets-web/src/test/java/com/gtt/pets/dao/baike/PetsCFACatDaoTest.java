package com.gtt.pets.dao.baike;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsCFACat;
import com.gtt.pets.entity.baike.PetsFCISection;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午9:30 To change
 * this template use File | Settings | File Templates.
 */
public class PetsCFACatDaoTest extends AbstractTest {

    @Autowired
    private PetsCFACatDao petsCFACatDao;

    @Test
    public void testFindAll() {
        List<PetsCFACat> list = petsCFACatDao.findAll();
        notNull(list);
        for (PetsCFACat cat : list) {
            print(cat);
        }
    }

    private void print(PetsCFACat cat) {
        System.out.println(cat.getName()+"/"+cat.getEnName()+"/"+cat.getCfaLink()+"/"+cat.getPetId());
    }

}
