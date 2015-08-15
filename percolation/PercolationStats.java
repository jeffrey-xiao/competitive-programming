package percolation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PercolationStats {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	double total = 0;
	double deviationTotal = 0;
	double e = 0;
	int N;
	int T;
	double diff;

	public PercolationStats (int N, int T) {
		Percolation p;
		this.N = N;
		this.T = T;
		long c = System.currentTimeMillis();
		for (int x = 0; x < T; x++) {
			p = new Percolation(N);
			double d = p.getOperations() / (double) (N * N);
			e += (-2 * d);
			deviationTotal += d * d;
			total += d;
		}
		System.out.println(System.currentTimeMillis() - c);
		total /= T;
		e += (T * total);
		e *= total;
		diff = (1.96d * (stddev()) / (Math.sqrt(T)));
	}

	public double mean () {
		return total;
	}

	public double stddev () {
		if (T == 1)
			return total;
		return Math.sqrt((e + deviationTotal) / (T - 1));
	}

	public double confidenceLo () {
		if (T == 1)
			return total;
		return total - diff;
	}

	public double confidenceHi () {
		if (T == 1)
			return total;
		return total + diff;
	}

	public static void main (String[] args) throws IOException {
		System.out.println("Enter N: ");
		int N = readInt();
		System.out.println("Enter T: ");
		int T = readInt();
		PercolationStats ps = new PercolationStats(N, T);
		System.out.printf("%-7s = %f\n", "mean", ps.mean());
		System.out.printf("%-7s = %f\n", "std dev", ps.stddev());
		System.out.printf("%-7s : Low = %f; High = %f\n", "95%",
				ps.confidenceLo(), ps.confidenceHi());
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
