package com.biencloud.smarthome.web.rest.util;

import java.util.Map;

import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

public class MultiValueUtils {

	static public MultiValueMap<String, Object> arrayToListMultiValueMap(Map<String, Object[]> parts){
		MultiValueMap<String, Object> map = new LinkedMultiValueMap<String, Object>(10);
		for (Map.Entry<String, Object[]> entry : parts.entrySet()){
			map.add(entry.getKey(), CollectionUtils.arrayToList(entry.getValue()));
		}
		return map;
	}
}
