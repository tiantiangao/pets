package com.gtt.pets.service.impl.open;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.gtt.pets.service.open.DPOpenAPI;
import com.gtt.pets.util.HttpUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author tiantiangao
 */
@Service
public class DPOpenAPIImpl implements DPOpenAPI {

	private static final String APP_KEY = "808875624";
	private static final String SECRET_KEY = "d632233435114cb2966088c30799afcc";

	private static final String URL_GET_BATSH_SHOP = "http://api.dianping.com/v1/business/get_batch_businesses_by_id";

	@Override
	public String getBatchShop(List<Integer> shopIdList) {
		if (CollectionUtils.isEmpty(shopIdList)) {
			return null;
		}

		String shopIdStr = Joiner.on(",").join(shopIdList);

		Map<String, String> params = Maps.newHashMap();
		params.put("business_ids", shopIdStr);
		params.put("out_offset_type", "1");
		params.put("platform", "1");
		params.put("format", "json");
		String sign = generateSign(URL_GET_BATSH_SHOP, params);

		params.put("appkey", APP_KEY);
		params.put("sign", sign);
		return HttpUtils.executeGet(URL_GET_BATSH_SHOP, params);
	}

	private String generateSign(String url, Map<String, String> paramMap) {
		// 对参数名进行字典排序
		String[] keyArray = paramMap.keySet().toArray(new String[0]);
		Arrays.sort(keyArray);

		// 拼接有序的参数名-值串
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(APP_KEY);
		for (String key : keyArray) {
			stringBuilder.append(key).append(paramMap.get(key));
		}

		stringBuilder.append(SECRET_KEY);
		String codes = stringBuilder.toString();
		return org.apache.commons.codec.digest.DigestUtils.shaHex(codes).toUpperCase();
	}
}
