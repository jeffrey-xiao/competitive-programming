package contest.other;

import java.util.Scanner;

public class Adding_Reversed_Numbers {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int x = scan.nextInt(); x > 0; x--) {
			System.out.println(reverse(reverse(scan.nextInt())
					+ reverse(scan.nextInt())));
		}
	}

	public static int reverse (int i) {
		return Integer.parseInt(new StringBuilder(Integer.toString(i))
				.reverse().toString());
	}
}
