package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2002_Stage_2_Duathlon {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int dist = readInt();
		int n = readInt();
		double[][] speeds = new double[n - 1][2];

		for (int x = 0; x < n - 1; x++) {
			speeds[x][0] = readDouble();
			speeds[x][1] = readDouble();
		}
		double cheaterSpeed1 = readDouble();
		double cheaterSpeed2 = readDouble();
		double maxTime = -1;
		double distance = 0;

		for (double x = 0; x <= dist; x += 0.001) {
			// for(double x = 49; x <= 51; x += 0.001){
			x = Math.round(x * 1000.0) / 1000.0;
			double cheatTime = getTime(x, dist - x, cheaterSpeed1,
					cheaterSpeed2);
			double maxOtherTime = Integer.MAX_VALUE;
			for (int y = 0; y < speeds.length; y++) {
				double otherTime = getTime(x, dist - x, speeds[y][0],
						speeds[y][1]);
				maxOtherTime = Math.min(otherTime, maxOtherTime);
				// System.out.println(otherTime);
			}
			if (cheatTime <= maxOtherTime && maxOtherTime - cheatTime > maxTime) {
				maxTime = Math.max(maxOtherTime - cheatTime, 0);
				// System.out.println(maxTime);
				distance = x;
			}
			// System.out.println(x+ " " + maxOtherTime + " " + cheatTime);
		}
		if (maxTime >= 0)
			System.out
					.printf("The cheater can win by %d seconds with r = %.2fkm and k = %.2fkm.",
							(int) Math.round(maxTime * 60 * 60), distance, dist
									- distance);
		else
			System.out.println("The cheater cannot win.");
	}

	private static double getTime (double a, double b, double c, double d) {
		return (a / c + b / d);
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}