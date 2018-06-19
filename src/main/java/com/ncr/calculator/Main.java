package com.ncr.calculator;

import java.text.DecimalFormat;
import java.util.Scanner;

import com.ncr.calculator.service.NaturalLanguageCalculator;

public class Main {
	
	public static void main(String[] args) {
		System.out.println("Please enter a calculation:");
		String input = readInput();
		double result = NaturalLanguageCalculator.calculate(input);
		System.out.format("Result: %s", new DecimalFormat("0.##").format(result));
	}
	
	private static String readInput() {
		try (Scanner scanner = new Scanner(System.in)) {
			return scanner.nextLine();
		}
	}

}
