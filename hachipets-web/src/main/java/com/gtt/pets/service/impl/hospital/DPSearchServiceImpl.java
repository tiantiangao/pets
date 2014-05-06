package com.gtt.pets.service.impl.hospital;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.gtt.kenshin.dao.model.PageModel;
import com.gtt.kenshin.log.KenshinLogger;
import com.gtt.kenshin.log.KenshinLoggerFactory;
import com.gtt.pets.bean.hospital.DPDealDTO;
import com.gtt.pets.bean.hospital.HospitalDTO;
import com.gtt.pets.service.impl.open.DPOpenAPIImpl;
import com.gtt.pets.service.open.DPOpenAPI;
import com.gtt.pets.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author tiantiangao
 */
@Service
public class DPSearchServiceImpl implements DPSearchService {

	private static final KenshinLogger LOGGER = KenshinLoggerFactory.getLogger(DPSearchServiceImpl.class);

	private static final String UTF_8 = "UTF-8";
	private static final String URL_DP_SEARCH = "http://www.dianping.com/search/map/ajax/json";
	private static final int PAGE_SIZE = 20;

	private static final ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private DPOpenAPI dpOpenAPI = new DPOpenAPIImpl();

	@Override
	public PageModel<HospitalDTO> search(int dpCityId, int dpCategoryId, double southWestLat, double southWestLng,
										 double northEastLat, double northEastLng, int page) {
		PageModel<HospitalDTO> model = new PageModel<HospitalDTO>();
		model.setPageSize(PAGE_SIZE);
		model.setPage(page);

		String result =
				searchByLngLat(dpCityId, dpCategoryId, southWestLat, southWestLng, northEastLat, northEastLng, page);
		if (StringUtils.isBlank(result)) {
			return model;
		}

		List<Integer> shopIdList = parseShopIds(result, model);

		String shopResult = batchGetShopInfo(shopIdList);

		model.setRecords(parseShopInfo(shopResult));
		return model;
	}

	private List<HospitalDTO> parseShopInfo(String shopResult) {
		List<HospitalDTO> result = Lists.newArrayList();

		if (StringUtils.isBlank(shopResult)) {
			return result;
		}

		try {
			Map<String, Object> map = objectMapper.readValue(shopResult, Map.class);
			Object bizList = map.get("businesses");
			if (bizList == null) {
				return result;
			}
			for (Map<String, Object> shopMap : ((List<Map<String, Object>>) bizList)) {
				HospitalDTO hospitalDTO = new HospitalDTO();
				hospitalDTO.setCityName(getStringValue(shopMap, "city"));
				hospitalDTO.setShopId(getIntValue(shopMap, "business_id"));
				hospitalDTO.setShopName(getStringValue(shopMap, "name"));
				hospitalDTO.setAddress(getStringValue(shopMap, "address"));
				hospitalDTO.setTelephone(getStringValue(shopMap, "telephone"));
				hospitalDTO.setLat(getDoubleValue(shopMap, "latitude"));
				hospitalDTO.setLng(getDoubleValue(shopMap, "longitude"));
				hospitalDTO.setRatingUrl(getStringValue(shopMap, "rating_s_img_url"));
				hospitalDTO.setReviewCount(getIntValue(shopMap, "review_count"));
				hospitalDTO.setAvgPrice(getIntValue(shopMap, "avg_price"));
				hospitalDTO.setPhotoUrl(getStringValue(shopMap, "photo_url"));
				hospitalDTO.setsPhotoUrl(getStringValue(shopMap, "s_photo_url"));
				hospitalDTO.setDpShopUrl(getStringValue(shopMap, "business_url"));

				int hasCoupon = getIntValue(shopMap, "has_coupon");
				if (hasCoupon == 1) {
					hospitalDTO.setHasCoupon(true);
					hospitalDTO.setCouponDesc(getStringValue(shopMap, "coupon_description"));
					hospitalDTO.setCouponUrl(getStringValue(shopMap, "coupon_url"));
				}

				int hasDeal = getIntValue(shopMap, "has_deal");
				if (hasDeal == 1) {
					hospitalDTO.setHasDeal(true);
					parseDealInfo(hospitalDTO, shopMap.get("deals"));
				}

				result.add(hospitalDTO);
			}

		} catch (IOException e) {
		}

		return result;
	}

	private void parseDealInfo(HospitalDTO hospitalDTO, Object dealList) {
		List<DPDealDTO> dealDTOList = Lists.newArrayList();
		if (dealList == null) {
			hospitalDTO.setDealList(dealDTOList);
			return;
		}

		for (Map<String, Object> dealMap : ((List<Map<String, Object>>) dealList)) {
			DPDealDTO dealDTO = new DPDealDTO();
			dealDTO.setDealDesc(getStringValue(dealMap, "description"));
			dealDTO.setDealUrl(getStringValue(dealMap, "url"));
			dealDTOList.add(dealDTO);
		}

		hospitalDTO.setDealList(dealDTOList);
	}

	private String batchGetShopInfo(List<Integer> shopIdList) {
		return dpOpenAPI.getBatchShop(shopIdList);
	}

	private String searchByLngLat(int dpCityId, int dpCategoryId, double southWestLat, double southWestLng,
								  double northEastLat, double northEastLng, int page) {
		Map<String, String> params = Maps.newHashMap();
		params.put("cityId", String.valueOf(dpCityId));
		params.put("shopType", String.valueOf(80));
		params.put("categoryId", String.valueOf(dpCategoryId));
		params.put("shopSortItem", String.valueOf(1));
		params.put("sortMode", String.valueOf(2));
		params.put("glong1", String.valueOf(southWestLng));
		params.put("glat1", String.valueOf(southWestLat));
		params.put("glong2", String.valueOf(northEastLng));
		params.put("glat2", String.valueOf(northEastLat));
		params.put("page", String.valueOf(page));
		return HttpUtils.executeGet(URL_DP_SEARCH, params);
	}

	private List<Integer> parseShopIds(String result, PageModel<HospitalDTO> model) {
		List<Integer> shopIdList = Lists.newArrayList();

		if (StringUtils.isBlank(result)) {
			return shopIdList;
		}

		try {
			Map<String, Object> map = objectMapper.readValue(result, Map.class);

			// parse page count
			Integer pageCount = 0;
			try {
				pageCount = (Integer) map.get("pageCount");
			} catch (Exception e) {
			}
			model.setRecordCount(pageCount == 0 ? 0 : pageCount * PAGE_SIZE);

			// parse shop list
			Object shopList = map.get("shopRecordBeanList");
			if (shopList == null) {
				return shopIdList;
			}

			for (Map<String, Object> shop : ((List<Map<String, Object>>) shopList)) {
				shopIdList.add(getIntValue(shop, "shopId"));
			}
		} catch (IOException e) {
		}
		return shopIdList;
	}

	private int getIntValue(Map<String, Object> map, String key) {
		if (!map.containsKey(key)) {
			return 0;
		}
		try {
			return (Integer) map.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	private double getDoubleValue(Map<String, Object> map, String key) {
		if (!map.containsKey(key)) {
			return 0;
		}

		try {
			return (Double) map.get(key);
		} catch (Exception e) {
			return 0;
		}
	}

	private String getStringValue(Map<String, Object> map, String key) {
		if (!map.containsKey(key)) {
			return "";
		}
		try {
			return (String) map.get(key);
		} catch (Exception e) {
			return "";
		}
	}


	public static void main(String[] args) {
		DPSearchServiceImpl s = new DPSearchServiceImpl();
		PageModel<HospitalDTO> search =
				s.search(1, 25148, 31.000091991371804, 121.19935908789091, 31.366650495461126, 121.7754546689456, 1);
		System.out.println(search);
	}
}
