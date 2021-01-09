package com.awad.anthony.springsecurityjwt.security.util;

public class StringUtility {
public static boolean isEmptyOrNull(String s) {
	if(s!=null)
		return s.isEmpty();
	return true;
}
}
