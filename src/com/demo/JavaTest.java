package com.demo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class JavaTest {

	// E.g. Input: Automation, Output: noitamotuA
	// Please don't use method such as s.reverse()
	public static String reverseStringWithoutUsingStringMethod(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}

	// Sort the integer in ASC order without using the built-in method such as
	// ArrayUtils.sort
	public static Integer[] sortIntegers(Integer[] array) {
		// Insertion Sort
		for (int i = 1; i < array.length; i++) {
			int key = array[i];

			// binary search to reduce resorting time
			int low = 0;
			int high = (i - 1);

			while (low <= high) {
				int mid = (low + high) / 2;
				if (key < array[mid])
					high = mid - 1;
				else
					low = mid + 1;
			}

			int j = (i - 1);
			while (j >= low) {
				array[j + 1] = array[j];
				j--;
			}

			array[low] = key;

		}
		return array;
	}

	// Check if the given date is within the date range
	public static boolean isInDateRange(Date givenDate, Date startDate, Date endDate) {
		if (givenDate == null || startDate == null || endDate == null)
			return false;
		return !givenDate.before(startDate) && !givenDate.after(endDate);
	}

	// sort the given String in ASC order without using method like Arrays.sort
	public static char[] sortStringInAscOrder(String input) {
		char[] array = input.toCharArray();

		// Insertion Sort
		for (int i = 1; i < array.length; i++) {
			char key = array[i];

			// binary search to reduce resorting time
			int low = 0;
			int high = (i - 1);

			while (low <= high) {
				int mid = (low + high) / 2;
				if (key < array[mid])
					high = mid - 1;
				else
					low = mid + 1;
			}

			int j = (i - 1);
			while (j >= low) {
				array[j + 1] = array[j];
				j--;
			}

			array[low] = key;

		}
		return array;
		// return new char[] { 'A', 'a', 'a' };
	}

	// Given a Alphanumeric, please return a character with the lowest occurrence
	// E.g. AbcdAbc123123, the answer is d as it only occurs once
	// If there is more than 1 char with the same lowest occurrence, return the
	// first char in ASC order
	public static char lowestOccurrence(String input) {
		char[] arr = input.toCharArray();

		Map<Character, Integer> occMap = new HashMap<>();

		for (char c : arr) {
			occMap.put(c, occMap.getOrDefault(c, 0) + 1);
		}

		int minCount = Integer.MAX_VALUE;
		char result = 0;

		for (Entry<Character, Integer> occ : occMap.entrySet()) {
			char key = occ.getKey();
			int val = occ.getValue();

			if (val < minCount) {
				minCount = val;
				result = key;
			}

			// If there is more than 1 char with the same lowest occurrence, return the
			// first char in ASC order
			else if (val == minCount && key < result) {
				result = key;
			}
		}

		return result;
		// return 'd';
	}

	// Given an input, please apply the provided equations (+, -, x, /)
	// E.g. input: 1.5, equations: x*2, x+10/2, x*1.5-6
	// Answer: 1st equation: x*2 = 1.5*2 = 3
	// 2nd equation: x+10/2 = 3+10/2 = 8
	// 3rd equation: x*1.5-6 = 8*1.5-6 = 6
	// return 6.0
	public static double solveEquations(double input, String[] equations) {
		for (String exp : equations) {
			exp = exp.replace("x", String.valueOf(input));
			input = recursiveCalculation(exp);
		}
		return input;
		//return 0.0;
	}

	public static double recursiveCalculation(String exp) {
		double result = 0;
		if (exp.contains("+") || exp.contains("-")) {
			int i = 0;
			while (i < exp.length()) {
				char c = exp.charAt(i);
				if (c == '+' || c == '-') {
					double first = recursiveCalculation(exp.substring(0, i));
					double second = recursiveCalculation(exp.substring(i + 1));
					result = (c == '+') ? first + second : first - second;
				}
				i++;
			}
		} else if (exp.contains("*") || exp.contains("/")) {
			int i = 0;
			while (i < exp.length()) {
				char c = exp.charAt(i);
				if (c == '*' || c == '/') {
					double first = recursiveCalculation(exp.substring(0, i));
					double second = recursiveCalculation(exp.substring(i + 1));
					result = (c == '*') ? first * second : first / second;
				}
				i++;
			}
		} else {
			result = Double.valueOf(exp);
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println("Test 1: " + reverseStringWithoutUsingStringMethod("Automation"));
		Integer[] intArray = new Integer[] { 10, 12, 54, 1, 2, -9, 8 };
		System.out.print("Test 2: ");
		for (Integer i : sortIntegers(intArray)) {
			System.out.print(i + ", ");
		}

		System.out.println();
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			Date startDate = sdf.parse("2024-12-01 13:09:22");
			Date endDate = sdf.parse("2025-01-09 20:10:12");
			Date givenDate = sdf.parse("2025-01-02 00:11:22");
			System.out.println("Test 3: " + isInDateRange(givenDate, startDate, endDate));
		} catch (Exception e) {
			System.out.println(e);
		}

		char[] sorted = sortStringInAscOrder("testingNG311");
		System.out.print("Test 4 :");
		for (char c : sorted) {
			System.out.print(c + ", ");
		}
		System.out.println();
		System.out.println("Test 5: " + lowestOccurrence("Abc1dd23affbc1ee23u3278"));
		System.out.print("Test 6: ");
		//double calculated = solveEquations(1.5, new String[] { "x*2", "x+10/2", "x*1.5-6"});
		double calculated = solveEquations(3.4, new String[] { "x*x", "x-10/2.2", "x+4-10", "x+5*8" });
		System.out.print("calculated = " + calculated);
	}

}
