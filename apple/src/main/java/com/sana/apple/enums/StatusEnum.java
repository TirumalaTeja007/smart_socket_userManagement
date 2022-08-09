package com.sana.apple.enums;

import java.util.HashSet;
import java.util.Set;

public enum StatusEnum {

	ACTIVE, IN_ACTIVE;

	private static final Set<String> values = new HashSet<>(StatusEnum.values().length);

	static {
		for (StatusEnum statusEnum : StatusEnum.values())
			values.add(statusEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}

}
