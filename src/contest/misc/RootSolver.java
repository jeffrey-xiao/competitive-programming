package contest.misc;

import java.util.*;
import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class RootSolver {
	static final BigDecimal EPS = new BigDecimal(1e-10);
	static final BigDecimal INF = new BigDecimal(1e20);
	static final int PRECISION = 15;
	
	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		ArrayList<Monomial> polynomial = new ArrayList<Monomial>();
		
		for (int i = 0; i < N; i++)
			polynomial.add(new Monomial(next(), readInt()));
		
		ArrayList<BigDecimal> res = findAllRoots(polynomial);
		
		if (res.size() == 0) {
			out.println("NO REAL ROOTS");
		} else {
			for (BigDecimal d : res) {
				out.println(d.setScale(6, RoundingMode.HALF_UP).toString());
			}
		}
		
		out.close();
	}

	static class Monomial {
		BigDecimal coefficient;
		int exponent;
		

		Monomial (BigDecimal coefficient, int exponent) {
			this.coefficient = coefficient.setScale(PRECISION, RoundingMode.HALF_UP);
			this.exponent = exponent;
		}
		
		Monomial (String coefficient, int exponent) {
			this.coefficient = new BigDecimal(coefficient).setScale(PRECISION, RoundingMode.HALF_UP);
			this.exponent = exponent;
		}
		
		BigDecimal evaluate (BigDecimal x) {
			return coefficient.multiply(x.pow(exponent));
		}
		
		public String toString () {
			return String.format("%sx^%d", coefficient.toString(),  exponent);
		}
	}
	
	static BigDecimal evaluate (ArrayList<Monomial> polynomial, BigDecimal x) {
		BigDecimal ret = BigDecimal.ZERO.setScale(PRECISION, RoundingMode.HALF_UP);
		for (Monomial m : polynomial)
			ret = ret.add(m.evaluate(x));
		return ret;
	}
	
	static BigDecimal findRoot (ArrayList<Monomial> polynomial, BigDecimal x1, BigDecimal x2) {
		BigDecimal y1 = evaluate(polynomial, x1), y2 = evaluate(polynomial, x2);
		boolean neg1 = y1.compareTo(BigDecimal.ZERO) < 0, neg2 = y2.compareTo(BigDecimal.ZERO) < 0;
		
		if (y1.abs().compareTo(EPS) <= 0)
			return x1;
		if (y2.abs().compareTo(EPS) <= 0)
			return null;
		
		if (neg1 == neg2)
			return null;
		
		x2 = x2.setScale(PRECISION, RoundingMode.HALF_UP);
		x1 = x1.setScale(PRECISION, RoundingMode.HALF_UP);
		
		while (x2.subtract(x1).compareTo(EPS) > 0) {
			BigDecimal x = BigDecimal.ZERO.setScale(PRECISION, RoundingMode.HALF_UP);
			x = x.add(x2.add(x1).divide(new BigDecimal("2")));
			if ((evaluate(polynomial, x).compareTo(BigDecimal.ZERO) < 0) == neg1)
				x1 = x;
			else
				x2 = x;
		}
		return x1;
	}
	
	static ArrayList<BigDecimal> findAllRoots (ArrayList<Monomial> polynomial) {
		ArrayList<Monomial> diff = new ArrayList<Monomial>();
		ArrayList<BigDecimal> ret = new ArrayList<BigDecimal>();
		
		for (Monomial m : polynomial)
			if (m.exponent > 0)
				diff.add(new Monomial(m.coefficient.multiply(new BigDecimal(Integer.toString(m.exponent)).setScale(PRECISION, RoundingMode.HALF_UP)), m.exponent - 1));
		
		if (diff.isEmpty())
			return ret;
		
		ArrayList<BigDecimal> diffRoots = findAllRoots(diff);
		diffRoots.add(0, INF.negate());
		diffRoots.add(INF);
		
		
		for (int i = 0; i < diffRoots.size() - 1; i++) {
			BigDecimal root = findRoot(polynomial, diffRoots.get(i), diffRoots.get(i + 1));
			if (root == null)
				continue;
			if (ret.isEmpty() || ret.get(ret.size() - 1).subtract(root).abs().compareTo(EPS) > 0)
				ret.add(root);
		}
		
		return ret;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}

