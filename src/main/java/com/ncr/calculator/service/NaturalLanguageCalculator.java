package com.ncr.calculator.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.ncr.calculator.model.Operator;

public class NaturalLanguageCalculator{

    /**
     * Hashmap containing the possible arithmetic operators and numerics
     */
	public static Map<String,String> calculatorset = new HashMap<String,String>() {
		private static final long serialVersionUID = 1L;

		{
			put("one","1");
			put("two","2");
			put("three","3");
			put("four","4");
			put("five","5");
			put("six","6");
			put("seven","7");
			put("eight","8");
			put("nine","9");
			put("ten","10");
			put("add","PLUS");
			put("plus","PLUS");
			put("subtract","MINUS");
			put("minus","MINUS");
			put("less","MINUS");
			put("multiplied-by","MULTIPLY");
			put("times","MULTIPLY");
			put("divided-by","DIVIDE");
			put("over","DIVIDE");
		}};

		/**
		 * Converts the input into numeric format and performs the calculation
		 * @param input
		 * @return double
		 */
		public static double calculate(String input) {
			try {
				List<String> inputList = convertToArithmaticFormat( input.toLowerCase());
				List<String> postfixList = getPostfixList(inputList);
				double result = evaluatePostfixList(postfixList);
				return result;
			}catch(Exception ex) {
				throw new IllegalArgumentException("Invalid input,please enter valid values!");
			}
		}

		/**
		 * Converts the input string into list of string tokens with values 
		 * from calculatorset hashmap
		 * @param input
		 * @return List<String>
		 */
		private static List<String> convertToArithmaticFormat(String input){

			return	Arrays.stream(input.split("\\s+"))
					.map(token -> {return (String)calculatorset.get(token);})
					.collect(Collectors.toList());

		}

		/**
		 * Converts the input list into Reverse polish notation format using shunting-yard algorithm
		 * @param input
		 * @return List<String>
		 */
		private static List<String> getPostfixList(List<String> input) {

			List<String> output = new ArrayList<>();
			Deque<String> stack  = new LinkedList<>();

			for (String token : input) {
				if (Operator.isOperator(token)) {
					Operator operator = Operator.valueOf(token);
					while (!stack.isEmpty() && operator.isLowerPrec(Operator.valueOf(stack.peek()))) {
						output.add(stack.pop());
					}
					stack.push(token);
				} else {
					output.add(token);
				}  
			}
			while (!stack.isEmpty()) {
				output.add(stack.pop());
			}
			return output;
		}

		/**
		 * Evaluates the postfix list using postfix evaluation algorithm and returns the final result	
		 * @param postfixList
		 * @return
		 */
		private static double evaluatePostfixList(List<String> postfixList) {
			Deque<String> stack  = new LinkedList<>();
			for (String token : postfixList) {
				if (Operator.isOperator(token)) {
					Operator operator = Operator.valueOf(token);
					double operand2 = Double.parseDouble(stack.pop());  
					double operand1 = Double.parseDouble(stack.pop());      
					double result = operator.execute(operand1, operand2);
					stack.push(String.valueOf(result));              
				} else {
					stack.push(token);
				}
			}          
			return Double.parseDouble(stack.pop());
		}
}
