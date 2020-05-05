package com.demo.phoneapp.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class PhoneConstants {

	public static final String INVALID_PHONE_NUMBER = "INVALID PHONE NUMBER";

	public static final String PHONE_NUMBER_MISSING = "PHONE NUMBER MISSING";

	public static final String SUCCESS = "SUCCESS";

	public static final String VALID_PHONE_REGEX = "^(\\(?(\\d{3})\\)?[-.\\s]?(\\d{3})[-.\\s]?(\\d{4}))|((\\d{3})[-.\\s]?(\\d{4}))$";

	private static Map<String, String> phone = new HashMap<String, String>() {
		{
			put("1", "1");
			put("2", "abc2");
			put("3", "def3");
			put("4", "ghi4");
			put("5", "jkl5");
			put("6", "mno6");
			put("7", "pqrs7");
			put("8", "tuv8");
			put("9", "wxyz9");
			put("0", "0");
		}
	};
	public static final Map<String, String> KEYPAD = Collections.unmodifiableMap(phone);

}
