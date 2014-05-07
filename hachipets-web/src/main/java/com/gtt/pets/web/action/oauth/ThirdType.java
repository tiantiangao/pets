package com.gtt.pets.web.action.oauth;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

/**
 * @author tiantiangao
 */
public class ThirdType {

	private static BiMap<String, Integer> typeNameValueMap;
	private static BiMap<Integer, String> typeValueNameMap;

	static {
		typeNameValueMap = HashBiMap.create();
		typeNameValueMap.put("qq", 1);
		typeNameValueMap.put("sina", 2);

		typeValueNameMap = typeNameValueMap.inverse();
	}

	public static int getThirdValue(String name) {
		return typeNameValueMap.containsKey(name) ? typeNameValueMap.get(name) : 0;
	}

	public static String getThirdName(int value) {
		return typeValueNameMap.containsKey(value) ? typeValueNameMap.get(value) : null;
	}

}
