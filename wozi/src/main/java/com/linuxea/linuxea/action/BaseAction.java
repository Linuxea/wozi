package com.linuxea.linuxea.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction  extends ActionSupport{
	private Logger logger = Logger.getLogger(BaseAction.class);
	
	
	protected static Map<String, Object> map = new HashMap<>();
	public Map<String, Object> getMap() {
		return map;
	}

	public void setMap(Map<String, Object> map) {
		this.map = map;
	}
	public void setActionResult(String code, String desc, Object data){
		map.put("code", code);
		map.put("desc", desc);
		map.put("data", data);
		doJsonOut();
	}
	public void setActionResult(String code, String desc){
		map.put("code", code);
		map.put("desc", desc);
		map.put("data", null);
		doJsonOut();
	}
	public static void doJsonOut() {
		JSON.toJSONString(map);
	}
	
	
	
}
