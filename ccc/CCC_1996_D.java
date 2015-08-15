package ccc;

import java.util.Scanner;
import java.util.TreeMap;

public class CCC_1996_D {
	static Scanner scan = new Scanner(System.in);
	static TreeMap<Character, Integer> values = new TreeMap<Character, Integer>();

	public static void main (String[] args) {
		values.put('I', 1);
		values.put('V', 5);
		values.put('X', 10);
		values.put('L', 50);
		values.put('C', 100);
		values.put('D', 500);
		values.put('M', 1000);
		int x = scan.nextInt();
		scan.nextLine();
		for (; x > 0; x--) {
			String s = scan.nextLine();
			String[] nums = s.split("\\+|=");
			int sum = getDecimal(nums[0]) + getDecimal(nums[1]);
			System.out.print(s);
			while (sum > 0) {
				if (sum > 1000) {
					sum = 0;
					System.out.println("CONCORDIA CUM VERITATE");
				} else if (sum == 1000) {
					sum -= 1000;
					System.out.print("M");
				} else if (sum - 900 >= 0) {
					sum -= 900;
					System.out.print("CM");
				} else if (sum - 500 >= 0) {
					sum -= 500;
					System.out.print("D");
				} else if (sum - 400 >= 0) {
					sum -= 400;
					System.out.print("CD");
				} else if (sum - 100 >= 0) {
					sum -= 100;
					System.out.print("C");
				} else if (sum - 90 >= 0) {
					sum -= 90;
					System.out.print("XC");
				} else if (sum - 50 >= 0) {
					sum -= 50;
					System.out.print("L");
				} else if (sum - 40 >= 0) {
					sum -= 40;
					System.out.print("XL");
				} else if (sum - 10 >= 0) {
					sum -= 10;
					System.out.print("X");
				} else if (sum - 9 >= 0) {
					sum -= 9;
					System.out.print("IX");
				} else if (sum - 5 >= 0) {
					sum -= 5;
					System.out.print("V");
				} else if (sum - 4 >= 0) {
					sum -= 4;
					System.out.print("IV");
				} else if (sum >= 1) {
					sum--;
					System.out.print("I");
				}
			}
			System.out.println();
		}
	}

	private static int getDecimal (String n) {
		if (n == null || n.length() == 0)
			return 0;
		int biggestValue = 0;
		int biggestIndex = 0;
		for (int x = 0; x < n.length(); x++) {
			if (values.get(n.charAt(x)) > biggestValue) {
				biggestValue = values.get(n.charAt(x));
				biggestIndex = x;
			}
		}
		int endIndex = biggestIndex - 1;
		for (; endIndex >= 0; endIndex--)
			if (values.get(n.charAt(endIndex)) != biggestValue)
				break;
		endIndex++;
		return (biggestIndex - endIndex + 1) * values.get(n.charAt(endIndex))
				- getDecimal(n.substring(0, endIndex))
				+ getDecimal(n.substring(biggestIndex + 1, n.length()));
	}
}
