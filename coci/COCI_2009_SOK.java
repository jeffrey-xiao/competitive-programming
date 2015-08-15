package coci;

import java.util.Scanner;

public class COCI_2009_SOK {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		double a = scan.nextInt();
		double b = scan.nextInt();
		double c = scan.nextInt();
		double i = scan.nextInt();
		double j = scan.nextInt();
		double k = scan.nextInt();
		double a1 = a / i;
		double b1 = b / j;
		double c1 = c / k;
		if (a1 <= b1 && a1 <= c1) {
			a = 0;
			b -= a1 * j;
			c -= a1 * k;
		} else if (b1 <= a1 && b1 <= c1) {
			b = 0;
			a -= b1 * i;
			c -= b1 * k;
		} else {
			c = 0;
			b -= c1 * j;
			a -= c1 * i;
		}
		System.out.printf("%.6f %.6f %.6f", a, b, c);
	}

}
