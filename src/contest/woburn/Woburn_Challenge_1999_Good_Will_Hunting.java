package contest.woburn;

import java.util.Scanner;

public class Woburn_Challenge_1999_Good_Will_Hunting {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		double x = scan.nextDouble();
		while (x != 0) {
			double r = 10.0;
			if (Math.abs(x) < 1)
				r = 0.1;
			int ex = 0;
			while (Math.abs(x) >= 10 || Math.abs(x) < 1) {
				x /= r;
				ex++;
			}
			if (r == 0.1)
				ex = -ex;
			System.out.printf("%.3f%s\n", x, ex == 0 ? "" : " x 10^" + ex);
			x = scan.nextDouble();
		}
	}
}
