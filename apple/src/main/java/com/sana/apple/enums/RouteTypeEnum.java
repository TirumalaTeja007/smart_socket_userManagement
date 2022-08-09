package com.sana.apple.enums;

import java.util.HashSet;
import java.util.Set;

public enum RouteTypeEnum {
	METRO, SHUTTLE;

	private static final Set<String> values = new HashSet<>(RouteTypeEnum.values().length);

	static {
		for (RouteTypeEnum routeEnum : RouteTypeEnum.values())
			values.add(routeEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}

}
