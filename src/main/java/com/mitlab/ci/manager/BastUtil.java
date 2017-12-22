package com.mitlab.ci.manager;

import java.util.UUID;

public class BastUtil {

	public static String getUuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
}
