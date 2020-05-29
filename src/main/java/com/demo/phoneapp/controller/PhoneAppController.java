package com.demo.phoneapp.controller;

import static com.demo.phoneapp.constants.PhoneConstants.VALID_PHONE_REGEX;

import java.util.concurrent.ExecutionException;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.phoneapp.client.WSClient;
import com.demo.phoneapp.model.Notification;
import com.demo.phoneapp.response.PhoneAppResponse;
import com.demo.phoneapp.service.PhoneAppService;

@RestController
@RequestMapping("/phone-app")
public class PhoneAppController {

	public PhoneAppService phoneAppService = new PhoneAppService();

	@GetMapping("/computePhoneNumbers")
	public ResponseEntity<String> computePhoneNumbers(
			@RequestParam @NotEmpty @Pattern(regexp = VALID_PHONE_REGEX) String input,
			@RequestParam(defaultValue = "10") String pageSize, @RequestParam String pageNumber) {

		String phoneCombinations = phoneAppService.getPage(phoneAppService.letterCombinations(input),
				Integer.valueOf(pageNumber), Integer.valueOf(pageSize)).toString();
		
		WSClient wsClient = new WSClient();
        ListenableFuture<StompSession> f = wsClient.connect();
        Notification notification = new Notification("Received a request in Phone Service");
        try {
            StompSession stompSession = f.get();
            wsClient.send(stompSession, notification);
            Thread.sleep(1000);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
		
		return new ResponseEntity<String>("Hello from Phone App Service!",
				HttpStatus.BAD_REQUEST);
		
		/*return new ResponseEntity<PhoneAppResponse>(PhoneAppResponse.builder().response(phoneCombinations).build(),
				HttpStatus.BAD_REQUEST);*/
		
		

	}

}
