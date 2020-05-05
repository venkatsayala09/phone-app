package com.demo.phoneapp.service;

import static  com.demo.phoneapp.constants.PhoneConstants.KEYPAD;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PhoneAppService {

	List<String> output = new ArrayList<String>();
	

	public void backtrack(String combination, String next_digits) {
		if (next_digits.length() == 0) {
			output.add(combination);
		} else {
			String digit = next_digits.substring(0, 1);
			String letters = KEYPAD.get(digit);
			for (int i = 0; i < letters.length(); i++) {
				String letter = KEYPAD.get(digit).substring(i, i + 1);
				backtrack(combination + letter, next_digits.substring(1));
			}
		}
	}

	public List<String> letterCombinations(String digits) {
		if (digits.length() != 0)
			backtrack("", digits);
		return output;
	}

	public List getPage(List sourceList, int page, int pageSize) {
		if (pageSize <= 0 || page <= 0) {
			throw new IllegalArgumentException("invalid page size: " + pageSize);
		}
		int fromIndex = (page - 1) * pageSize;
		if (sourceList == null || sourceList.size() < fromIndex) {
			return Collections.emptyList();
		}
		return sourceList.subList(fromIndex, Math.min(fromIndex + pageSize, sourceList.size()));
	}

}