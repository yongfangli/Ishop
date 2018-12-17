package com.thinkgem.jeesite.modules;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "${webPath}/test/")
public class TestControllerDemo {
	@RequestMapping("test1")
	public String test() {
		LinkedHashMap map=new LinkedHashMap();
		map.put(1, "aa");
		map.put(2,"bb");
		map.put(3,"cc");
		Set<Entry> integers= map.entrySet();
		for(Entry i:integers) {
			System.err.println(i+":"+map.get(i));
		}
		return "modules/test/test";
	}
	public static void main(String[] arg) {
		LinkedHashMap map=new LinkedHashMap();
		map.put(1, "aa");
		map.put(2,"bb");
		map.put(3,"cc");
		Set<Entry> integers= map.entrySet();
		for(Entry i:integers) {
			System.err.println(i.getKey()+":"+i.getValue());
		}
	}
}
