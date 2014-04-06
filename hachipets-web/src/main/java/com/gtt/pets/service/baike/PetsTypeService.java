package com.gtt.pets.service.baike;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.baike.*;

import java.util.Map;

/**
 * 宠物类型服务
 *
 * @author tiantiangao
 */
public interface PetsTypeService {

	PetsTypeDTO loadTypeByID(int typeId);

	PetsTypeCommonDTO loadTypeCommonByID(int typeId);

	PetsTypeDogDTO loadTypeDogByID(int typeId);

	PetsTypeCatDTO loadTypeCatByID(int typeId);

	PetsTypeFishDTO loadTypeFishByID(int typeId);

	PetsTypeRabbitDTO loadTypeRabbitByID(int typeId);

	Map<String, PetsTypeAttrNameDTO> loadTypeAttrNameMapByGroup(int group);

	PageModel<PetsTypeDTO> findTypeByCategory(int category, int page, int max);

}
