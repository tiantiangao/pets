package com.gtt.pets.dao.baike;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsFCISectionDog;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午9:30 To change
 * this template use File | Settings | File Templates.
 */
public class PetsFCISectionDogDaoTest extends AbstractTest {

    @Autowired
    private PetsFCISectionDogDao petsFCISectionDogDao;

    @Test
    public void testFindAll() {
        List<PetsFCISectionDog> list = petsFCISectionDogDao.findBySectionId(1);
        notNull(list);
        for (PetsFCISectionDog dog : list) {
            print(dog);
        }
    }

    private void print(PetsFCISectionDog dog) {
        System.out.println(dog.getSectionId() + "/" + dog.getPetId() + "/" + dog.getName() + "/" + dog.getEnName() + "/" + dog.getOrigin() + "/" + dog.getFciNo() + "/" + dog.getPicUrl());
    }

}
