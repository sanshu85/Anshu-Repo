package com.ncr.calculator.model;

import java.util.Arrays;

public enum Operator {
	
	PLUS("+", 1) {
		@Override
		public double execute(double operand1, double operand2) {
			return operand1 + operand2;
		}
	}, 
	
	MINUS("-", 2) {
		@Override
		public double execute(double operand1, double operand2) {
			return operand1 - operand2;
		}
	}, 
	
	MULTIPLY("*", 3) {
		@Override
		public double execute(double operand1, double operand2) {
			return operand1 * operand2;
		}
	}, 
	
	DIVIDE("/", 4) {
		@Override
		public double execute(double operand1, double operand2) {
			return operand1 / operand2;
		}
	};
	
    private final String symbol;
	
	/**
	 * Integer value representing the arithmetic operator precedence.
	 */
	private final int precedence;

	
	private Operator(String symbol, int precedence) {
		this.symbol = symbol;
		this.precedence = precedence;
	}
	
	
	public boolean isLowerPrec(Operator that) {
		return this.precedence < that.precedence;
	}
	
	@Override
	public String toString() {
		return symbol;
	}

	public abstract double execute(double operand1, double operand2);
	
	 public static boolean isOperator(String value) {
	     return Arrays.stream(Operator.values()).anyMatch(e -> e.name().equals(value));
	}
}
