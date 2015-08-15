package other;

import java.util.Scanner;

public class Battleships {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int r = scan.nextInt();
		int c = scan.nextInt();
		int n = scan.nextInt();
		long sum = 0;
		if (n > r || n > c) {
			System.out.println(0);
			return;
		}
		double d = (r + 1 - n) * (c + 1 - n);
		for (int x = 0; x < r; x++) {
			String s = scan.next();
			for (int y = 0; y < c; y++) {
				if (s.charAt(y) == 'X') {
					int a1 = Math.min(x, n - 1);
					int a2 = Math.min(r - 1 - x, n - 1);
					int b1 = Math.min(y, n - 1);
					int b2 = Math.min(c - 1 - y, n - 1);
					sum += ((((a1 + a2 + 1)) - n + 1) * (((b1 + b2 + 1) - n + 1)));
					/*
					 * System.out.println(x+" " + y);
					 * System.out.println(((((a1+a2
					 * +1))-n+1)*(((b1+b2+1)-n+1)))); System.out.println(a1+" "
					 * +a2+" "+b1+" "+b2);
					 */
				}
			}
		}
		/*
		 * System.out.println(sum); System.out.println(d);
		 */
		System.out.println(sum / d);
	}
}
