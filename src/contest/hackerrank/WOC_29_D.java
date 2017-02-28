package contest.hackerrank;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class WOC_29_D {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final long MAX_VALUE = (long)(1e15);
    static final int UNCERTAINITY = 20;
	static long A, B, ans;
	static ArrayList<Integer> primes = new ArrayList<Integer>();
	static Random rng = new Random(0);
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		A = readLong();
		B = readLong();

		long value = nextCandidateMegaPrime(A);
		long ans = 0;

		while (value <= B) {
			if (isPrime(value)) ans++;
			value = incrementMegaPrime(value);
		}

		out.println(ans);
		out.close();
	}
	
	static long nextCandidateMegaPrime (long n) {
		char[] temp = ("0" + Long.toString(n)).toCharArray();
		int[] value = new int[temp.length];
		for (int i = 0; i < temp.length; i++)
			value[i] = temp[i] - '0';
		boolean any = false;
		for (int i = 1; i < value.length; i++) {
			if (any) {
				value[i] = 2;
			} else if (value[i] != 2 && value[i] != 3 && value[i] != 5 && value[i] != 7) {
				any = true;
				int prev = value[i];
				value[i] = getNextValue(value[i]);
				if (prev >= 7)
					propagate(value, i - 1);
			}
		}
		return megaPrimeToLong(value);
	}
	
	static long megaPrimeToLong (int[] curr) {
		long ret = 0;
		for (int i = 0; i < curr.length; i++)
			ret = ret * 10 + curr[i];
		return ret;
	}
	
	static long incrementMegaPrime (long n) {
		long prev = n % 10;
		long curr = getNextValue((int)prev);
		if (prev >= 7)
			return incrementMegaPrime(n / 10) * 10 + curr;
		else
			return n / 10 * 10 + curr;
	}
	
	static void propagate (int[] value, int index) {
		int prev = value[index];
		value[index] = getNextValue(value[index]);
		if (prev >= 7)
			propagate(value, index - 1);
	}
	
	static int getNextValue (int n) {
		switch(n) {
			case 0:
			case 1:
				return 2;
			case 2:
				return 3;
			case 3:
			case 4:
				return 5;
			case 5:
			case 6:
				return 7;
			case 7:
			case 8:
			case 9:
				return 2;
		}
		assert false;
		return -1;
	}
	
	static boolean isPrime (long n) {
		return new BigInteger(Long.toString(n)).isProbablePrime(UNCERTAINITY);
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

