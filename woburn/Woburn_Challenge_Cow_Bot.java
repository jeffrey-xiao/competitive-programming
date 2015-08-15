package woburn;

import java.util.Scanner;

public class Woburn_Challenge_Cow_Bot {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int z = scan.nextInt(); z > 0; z--) {
			int y = scan.nextInt();
			for (int x = scan.nextInt(); x > 0; x--) {
				if (isPrime(y)) {
					y *= 11;
				} else if (Math.sqrt(y) - (int) Math.sqrt(y) == 0) {
					y = y
							+ Integer.parseInt(new StringBuilder(Integer
									.toString(y)).reverse().toString());
				} else if (isPalindrome(Integer.toString(y))) {
					y = y * 10 + 4;
				} else if (Integer.toString(y).charAt(0) == '2') {
					y = Integer.parseInt(new StringBuilder(Integer.toString(y))
							.insert(0, "5").toString());
				} else if (Integer.toString(y).indexOf("7") != -1) {
					y /= 10;
				} else if (y % 6 == 0) {
					String s = Integer.toString(y);
					y = Integer.parseInt(s.substring(1, s.length()));
				} else {
					StringBuilder s = new StringBuilder(Integer.toString(y));
					if (s.length() % 2 == 0) {
						y = Integer.parseInt(s.insert(s.length() / 2, 1)
								.toString());
					} else {
						y += 231;
					}
				}
			}
			System.out.println(y);
		}
	}

	private static boolean isPalindrome (String x) {
		return new StringBuilder(x).reverse().toString().equals(x);
	}

	private static boolean isPrime (int n) {
		if (n <= 1)
			return false;
		if (n == 2)
			return true;
		if (n % 2 == 0)
			return false;
		if (n < 9)
			return true;
		if (n % 3 == 0)
			return false;

		long counter = 5;
		while ((counter * counter) <= n) {
			if (n % counter == 0)
				return false;
			if (n % (counter + 2) == 0)
				return false;
			counter += 6;
		}

		return true;
	}
}
