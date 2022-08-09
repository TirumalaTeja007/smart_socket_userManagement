package com.sana.apple.enums;

import java.util.HashSet;
import java.util.Set;

public enum UserTypeEnum {

	USER,ADMIN,SUPERADMIN,MANAGER,OPERATIONALMANAGER;

	private static final Set<String> values = new HashSet<>(UserTypeEnum.values().length);

	static {
		for (UserTypeEnum statusEnum : UserTypeEnum.values())
			values.add(statusEnum.name());
	}

	public static boolean contains(String value) {
		return values.contains(value);
	}

}
