package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_2001_Back_To_The_Future {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int x = scan.nextInt(); x > 0; x--) {
			String binary = Integer.toString(scan.nextInt(), 2);
			System.out.println(toBase10(binary));
		}
	}

	private static int toBase10 (String binary) {
		int sum = 0;
		long product = 1;
		for (int x = 0; x < binary.length(); x++) {
			sum += product * (binary.charAt(x) - 48);
			product *= 2;
		}
		return sum;
	}

}
