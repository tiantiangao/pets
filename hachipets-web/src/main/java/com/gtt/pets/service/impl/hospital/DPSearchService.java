package com.gtt.pets.service.impl.hospital;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.hospital.HospitalDTO;

/**
 * @author tiantiangao
 */
public interface DPSearchService {

	PageModel<HospitalDTO> search(int dpCityId, int dpCategoryId, double southWestLat, double southWestLng,
								  double northEastLat, double northEastLng, int page);
}
