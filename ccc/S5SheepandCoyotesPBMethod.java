package ccc;

import java.util.Scanner;

public class S5SheepandCoyotesPBMethod {

	public static void main (String[] args) {
		double[] x = new double[1000];
		double[] y = new double[1000];
		boolean[] out = new boolean[1000]; // list of "out" sheep
		double xm, ym, s, p;
		int n;
		Scanner scan = new Scanner(System.in);

		n = scan.nextInt();
		for (int i = 0; i < n; i++) {
			x[i] = scan.nextDouble();
			y[i] = scan.nextDouble();
			out[i] = false;
		}
		for (int i = 0; i < n; i++) {
			double left = 0, right = 1000;
			for (int j = 0; j < n; j++) {
				if (!out[i] && !out[j] && i != j) {
					xm = (x[i] + x[j]) / 2;
					ym = (y[i] + y[j]) / 2;
					s = (x[i] - x[j]) / (y[j] - y[i]);
					if (s == 0) {
						if (y[i] < y[j])
							out[j] = true;
						else
							out[i] = true;
					} else {
						p = -ym / s + xm;
						if (x[j] < x[i])
							left = Math.max(p, left);
						else
							right = Math.min(p, right);
					}
					// System.out.println("(" + x[i] + ", " +y[i]+") "+left +
					// " " + right);
					// System.out.println("(" + x[j] + ", " +y[j]+")");
				}
			}
			// System.out.println("(" + x[i] + ", " +y[i]+") "+left + " " +
			// right);
			if (left >= right)
				out[i] = true;
		}
		for (int j = 0; j < n; j++)
			if (!out[j])
				System.out.printf(
						"The sheep at (%.2f, %.2f) might be eaten.\n", x[j],
						y[j]);
		scan.close();
	}
}
