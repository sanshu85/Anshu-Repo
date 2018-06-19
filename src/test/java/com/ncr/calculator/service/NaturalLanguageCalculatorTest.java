package com.ncr.calculator.service;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NaturalLanguageCalculatorTest {
	
	@Test
	public void testCalculate() {
		String input = "nine over eight plus four times two divided-by three";
		double result = NaturalLanguageCalculator.calculate(input);
		double expectedValue = 3.79;
		assertEquals(result,expectedValue,0.01);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateException() {
		String input = "nine over eight plus";
		NaturalLanguageCalculator.calculate(input);
	}
}
