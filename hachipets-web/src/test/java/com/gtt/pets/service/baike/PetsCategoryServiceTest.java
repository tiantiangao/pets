package com.gtt.pets.service.baike;

import com.gtt.pets.bean.baike.PetsCategoryDTO;
import com.gtt.pets.dao.AbstractTest;
import com.gtt.pets.service.impl.baike.PetsCategoryServiceImpl;
import junit.framework.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-8-1 Time: 上午7:23 To change
 * this template use File | Settings | File Templates.
 */
public class PetsCategoryServiceTest extends AbstractTest {

    @Autowired
    private PetsCategoryServiceImpl petsCategoryService;

    @Test
    public void test() {
        PetsCategoryDTO category = petsCategoryService.loadById(0);
        Assert.assertNull(category);

        category = petsCategoryService.loadById(10);
        Assert.assertNotNull(category);

        List<PetsCategoryDTO> rootCategories = petsCategoryService.findRootCategories();
        for (PetsCategoryDTO rootCategory : rootCategories) {
            print(rootCategory);
            List<PetsCategoryDTO> children = petsCategoryService.findByParentId(rootCategory.getId());
            for (PetsCategoryDTO child : children) {
                print(child);
                List<PetsCategoryDTO> nextChildren = petsCategoryService.findByParentId(child.getId());
                for (PetsCategoryDTO nextChild : nextChildren) {
                    print(nextChild);
                }
            }
        }

    }

    private void print(PetsCategoryDTO dto) {
        System.out.print(dto.getId() + "/" + dto.getParentId() + "/" + dto.getName() + "  ");
        List<PetsCategoryDTO> paths = petsCategoryService.findPathByCategoryId(dto.getId());
        for (PetsCategoryDTO path : paths) {
            System.out.print(" > " + path.getId());
        }
        System.out.println("");
    }

}
