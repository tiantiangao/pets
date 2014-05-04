package com.gtt.pets.service.hospital;

import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.pets.bean.hospital.HospitalDTO;

/**
 * Created with IntelliJ IDEA.
 * User: gtt
 * Date: 13-7-28
 * Time: 下午3:19
 * To change this template use File | Settings | File Templates.
 */
public interface PetsHospitalService {

	PageModel<HospitalDTO> findHospitalByCityAndBounds(String cityName, double southWestLat, double southWestLng,
													   double northEastLat, double northEastLng, int page);

}
