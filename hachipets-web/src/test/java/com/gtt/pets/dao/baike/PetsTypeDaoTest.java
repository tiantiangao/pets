package com.gtt.pets.dao.baike;

import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.entity.baike.PetsType;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

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
        print(petsType);
    }

    private void print(PetsType type) {
        if (type == null) {
            return;
        }
        List<String> list = new ArrayList<String>();
        list.add(type.getId() + "");
        list.add(type.getName());
        list.add(type.getCategoryId() + "");
        list.add(type.getRootCategoryId() + "");
        list.add(type.getEnName());
        list.add(type.getAlias());
        list.add(type.getOrigin());
        list.add(type.getPicUrl());
        list.add(type.getThumbPicUrl());
        list.add(type.getDesc());
        String info = StringUtils.join(list, "---");
        System.out.println(info);
    }

}
