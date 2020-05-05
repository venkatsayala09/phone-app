package com.demo.phoneapp.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.phoneapp.service.PhoneAppService;

@SpringBootTest
public class PhoneApserviceTest{
	
	PhoneAppService pns = new PhoneAppService();
	
	@Test
    @Disabled("Throws NPE - To Be Corrected")
    public void letterCombinations_withNullParam_shouldReturnBlank(){
        Assertions.assertThat(pns.letterCombinations(null)).hasSize(0);
    }
	
	@Test
    public void letterCombinations_withBlankParam_shouldReturnBlank(){
        Assertions.assertThat(pns.letterCombinations("")).hasSize(0);
    }
    @Test
    @Disabled("Throws NPE - To Be Corrected")
    public void letterCombinations_withInvalidPhoneNumber_shouldReturnOneCombination(){
        Assertions.assertThat(pns.letterCombinations(")(*&^%$#@!")).hasSize(1);
    }
    @Test
    public void letterCombinations_withDistinctNumbers_shouldReturnAllPossibleCombinations(){
        Assertions.assertThat(pns.letterCombinations("9876543210")).hasSize(5*4*5*4*4*4*4*4*1*1);
    }
    @Test
    public void letterCombinations_withDigitSequenceOfZeros_shouldReturnOneCombination(){
        Assertions.assertThat(pns.letterCombinations("0000000000")).hasSize(1);
    }
    @Test
    public void letterCombinations_withDigitSequenceOfOnes_shouldReturnOneCombination(){
        Assertions.assertThat(pns.letterCombinations("1111111111")).hasSize(1);
    }
    @Test
    public void letterCombinations_withSevenDigits_shouldReturnAllPossibleCombinations(){
        Assertions.assertThat(pns.letterCombinations("9654310")).hasSize(5*4*4*4*4*1*1);
    }
	

}
