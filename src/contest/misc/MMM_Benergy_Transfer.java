package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class MMM_Benergy_Transfer {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		double[] initialB = new double[n];
		double[] speedB = new double[n];
		double[] initialS = new double[n];
		double[] speedS = new double[n];
		for (int x = 0; x < n; x++)
			initialB[x] = readFloat();
		for (int x = 0; x < n; x++)
			initialS[x] = readFloat();
		for (int x = 0; x < n; x++)
			speedB[x] = readFloat();
		for (int x = 0; x < n; x++)
			speedS[x] = readFloat();
		double lower = 0;
		double higher = Integer.MAX_VALUE;
		while (higher - lower > 0.00000000005f) {
			double lowDist = getDistance(setDistance(initialB, speedB, lower), setDistance(initialS, speedS, lower));
			double highDist = getDistance(setDistance(initialB, speedB, higher), setDistance(initialS, speedS, higher));
			if (lowDist <= highDist)
				higher = (lower + higher) / 2;
			else
				lower = (lower + higher) / 2;

		}
		System.out.println(lower);
	}

	static double[] setDistance (double[] ori, double[] speed, double time) {
		double[] curr = new double[n];
		for (int x = 0; x < n; x++) {
			curr[x] = (ori[x] + speed[x] * time);
		}
		return curr;
	}

	static double getDistance (double[] pos1, double[] pos2) {
		double total = 0;
		for (int x = 0; x < pos1.length; x++) {
			double a = pos1[x];
			double b = pos2[x];
			total += (a - b) * (a - b);
		}
		return Math.sqrt(total);
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

	static float readFloat () throws IOException {
		return Float.parseFloat(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}