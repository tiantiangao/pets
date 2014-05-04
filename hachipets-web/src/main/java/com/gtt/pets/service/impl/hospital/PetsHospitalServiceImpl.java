package com.gtt.pets.service.impl.hospital;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.city.CityGaodeDTO;
import com.gtt.pets.bean.hospital.HospitalDTO;
import com.gtt.pets.service.city.CityGaodeService;
import com.gtt.pets.service.hospital.PetsHospitalService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PetsHospitalServiceImpl implements PetsHospitalService {

	@Autowired
	private CityGaodeService cityGaodeService;
	@Autowired
	private DPSearchService dpSearchService;

	@Override
	public PageModel<HospitalDTO> findHospitalByCityAndBounds(String cityName, double southWestLat, double southWestLng,
															  double northEastLat, double northEastLng, int page) {
		if (StringUtils.isBlank(cityName)) {
			return null;
		}

		CityGaodeDTO city = cityGaodeService.loadByCityName(cityName);
		if (city == null || city.getDpCityId() <= 0 || city.getDpCategoryId() <= 0) {
			return null;
		}

		return dpSearchService
				.search(city.getDpCityId(), city.getDpCategoryId(), southWestLat, southWestLng, northEastLat,
						northEastLng, page > 0 ? page : 1);
	}
}
