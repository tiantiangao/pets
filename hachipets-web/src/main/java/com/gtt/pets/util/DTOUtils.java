package com.gtt.pets.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

/**
 * 
 * @author tiantiangao
 */
public class DTOUtils {

	public static <T> T toDTO(Class<T> dtoClazz, Object entity) {
		try {
			if (entity == null) {
				return null;
			}
			T dto = (T) dtoClazz.newInstance();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		} catch (Exception e) {
			return null;
		}
	}

	public static <T> List<T> toDTOList(Class<T> dtoClazz, List<?> entityList) {
		List<T> dtoList = new ArrayList<T>();
		try {
			if (CollectionUtils.isEmpty(entityList)) {
				return dtoList;
			}

			for (Object entity : entityList) {
				T dto = (T) dtoClazz.newInstance();
				BeanUtils.copyProperties(entity, dto);
				dtoList.add(dto);
			}

			return dtoList;
		} catch (Exception e) {
			return dtoList;
		}
	}

}
