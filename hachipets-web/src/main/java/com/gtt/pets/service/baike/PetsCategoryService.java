package com.gtt.pets.service.baike;

import com.gtt.pets.bean.baike.PetsCategoryDTO;

import java.util.List;

/**
 * Created with IntelliJ IDEA. User: gtt Date: 13-7-28 Time: 下午3:19 To change
 * this template use File | Settings | File Templates.
 */
public interface PetsCategoryService {

	/**
	 * 根据分类ID查找分类信息
	 * 
	 * @param id
	 * @return
	 */
	PetsCategoryDTO loadById(int id);

	/**
	 * 根据父分类ID查找所有子分类
	 * 
	 * @param parentId
	 * @return
	 */
	List<PetsCategoryDTO> findByParentId(int parentId);

}
