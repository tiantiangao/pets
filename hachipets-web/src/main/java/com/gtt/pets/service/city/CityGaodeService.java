package com.gtt.pets.service.city;

import com.gtt.pets.bean.city.CityGaodeDTO;

/**
 * @author tiantiangao
 */
public interface CityGaodeService {

	CityGaodeDTO loadByCityName(String cityName);

}
