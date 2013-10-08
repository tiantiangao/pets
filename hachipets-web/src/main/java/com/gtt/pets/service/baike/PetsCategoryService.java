package com.gtt.pets.service.baike;

import java.util.List;

import com.gtt.pets.bean.baike.PetsCategoryDTO;

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

	/**
	 * 获取指定分类的完整父路径
	 * 
	 * @param categoryId
	 * @return
	 */
	List<PetsCategoryDTO> findPathByCategoryId(int categoryId);

	/**
	 * 获取所有根分类
	 * 
	 * @return
	 */
	List<PetsCategoryDTO> findRootCategories();
}
