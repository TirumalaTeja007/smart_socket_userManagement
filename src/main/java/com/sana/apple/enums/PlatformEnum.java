package com.sana.apple.enums;

import java.util.HashSet;
import java.util.Set;

public enum PlatformEnum {
	
	MOBILE, ADMIN, WEB, POSTMAN;

	private static final Set<String> values = new HashSet<>(PlatformEnum.values().length);

	static {
		for (PlatformEnum platformEnum : PlatformEnum.values())
			values.add(platformEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}

}
