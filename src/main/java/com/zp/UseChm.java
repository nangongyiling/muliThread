package com.zp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UseChm {
	
	Map<String,String> map = new HashMap<String,String>();
	ConcurrentHashMap<String,String> chm = new ConcurrentHashMap<String,String>();
	
	public String putIfAbsent(String key,String value){
		synchronized (map) {
			if(map.get(key) == null){
				return map.put(key, value);
			}else{
				return map.get(key);
			}
		}
	}
	
	public String useChm(String key,String value){
		return chm.putIfAbsent(key, value);
	}
}
