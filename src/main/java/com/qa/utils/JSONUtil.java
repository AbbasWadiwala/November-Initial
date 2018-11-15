package com.qa.utils;

import com.google.gson.Gson;

public class JSONUtil {

	private static Gson gson;

	//	public JSONUtil() {
	//		this.gson = new Gson();
	//	}
	
	private JSONUtil() {};

	public static String getJSONForObject(Object obj) {
		return gson.toJson(obj);
	}

	public static <T> T getObjectForJSON(String jsonString, Class<T> clazz) {
		return gson.fromJson(jsonString, clazz);
	}

}