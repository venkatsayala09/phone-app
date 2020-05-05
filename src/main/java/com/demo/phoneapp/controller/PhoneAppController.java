package com.demo.phoneapp.controller;

import static com.demo.phoneapp.constants.PhoneConstants.VALID_PHONE_REGEX;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.phoneapp.response.PhoneAppResponse;
import com.demo.phoneapp.service.PhoneAppService;

@RestController
@RequestMapping("/phone-app")
public class PhoneAppController {

	public PhoneAppService phoneAppService = new PhoneAppService();

	@GetMapping("/computePhoneNumbers")
	public ResponseEntity<PhoneAppResponse> computePhoneNumbers(
			@RequestParam @NotEmpty @Pattern(regexp = VALID_PHONE_REGEX) String input,
			@RequestParam(defaultValue = "10") String pageSize, @RequestParam String pageNumber) {

		String phoneCombinations = phoneAppService.getPage(phoneAppService.letterCombinations(input),
				Integer.valueOf(pageNumber), Integer.valueOf(pageSize)).toString();
		
		return new ResponseEntity<PhoneAppResponse>(PhoneAppResponse.builder().response(phoneCombinations).build(),
				HttpStatus.BAD_REQUEST);

	}

}
