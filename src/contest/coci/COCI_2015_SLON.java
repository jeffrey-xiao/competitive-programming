package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2015_SLON {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static long P, M;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		String input = readLine();
		P = readInt();
		M = readInt();
		long change = eval(input, 1) - eval(input, 0);
		long initial = eval(input, 0);

		// initial + change * x = P mod M
		ArrayList<Long> res = linearEquationSolver(change, P - initial, M);
		Collections.sort(res);
		out.println(res.get(0));
		out.close();
	}

	// finds all solutions to ax = b mod n
	public static ArrayList<Long> linearEquationSolver (long a, long b, long n) {
		ArrayList<Long> ret = new ArrayList<Long>();
		long[] res = euclid(a, n);
		long d = res[0], x = res[1];

		if (b % d == 0) {
			x = mod(x * (b / d), n);
			for (int i = 0; i < d; i++)
				ret.add(mod(x + i * (n / d), n));
		}

		return ret;
	}

	// returns d = gcd(a, b); finds x, y such that d = ax * by
	public static long[] euclid (long a, long b) {
		long x = 1, y = 0, x1 = 0, y1 = 1, t;
		while (b != 0) {
			long q = a / b;
			t = x;
			x = x1;
			x1 = t - q * x1;
			t = y;
			y = y1;
			y1 = t - q * y1;
			t = b;
			b = a - q * b;
			a = t;
		}
		return a > 0 ? new long[] {a, x, y} : new long[] {-a, -x, -y};
	}

	static long mod (long a, long b) {
		return ((a % b) + b) % b;
	}

	private static boolean isDelim (char c) {
		return c == ' ';
	}

	private static boolean isOperator (char c) {
		return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
	}

	private static int getPriority (char op) {
		switch (op) {
			case '+':
			case '-':
				return 1;
			case '*':
			case '/':
			case '%':
				return 2;
			default:
				return -1;
		}
	}

	static void operate (LinkedList<Long> st, char op) {
		long r = st.removeLast();
		long l = st.removeLast();
		switch (op) {
			case '+':
				st.add((l + r) % M);
				break;
			case '-':
				st.add((l - r) % M);
				break;
			case '*':
				st.add((l * r) % M);
				break;
		}
	}

	public static long eval (String s, long x) {
		LinkedList<Long> st = new LinkedList<>();
		LinkedList<Character> op = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (isDelim(c))
				continue;
			if (c == '(') {
				op.add('(');
			} else if (c == ')') {
				while (op.getLast() != '(')
					operate(st, op.removeLast());
				op.removeLast();
			} else if (isOperator(c)) {
				while (!op.isEmpty() && getPriority(c) <= getPriority(op.getLast()))
					operate(st, op.removeLast());
				op.add(c);
			} else {
				long operand = 0;
				if (s.charAt(i) == 'x') {
					operand = x;
				} else {
					while (i < s.length() && '0' <= s.charAt(i) && s.charAt(i) <= '9')
						operand = (operand * 10 + s.charAt(i++) - '0') % M;
					--i;
				}
				st.add(operand);
			}
		}
		while (!op.isEmpty())
			operate(st, op.removeLast());
		return st.get(0);
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

