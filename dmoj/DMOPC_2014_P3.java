package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class DMOPC_2014_P3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		TreeSet<Employee> ts = new TreeSet<Employee>();
		for (int x = 0; x < n; x++)
			ts.add(new Employee(next(), readInt(), x));
		int q = readInt();
		// for(Employee e : ts){
		// System.out.println(e.name + " " + e.skill);
		// }
		for (int x = 0; x < q; x++) {
			int skill = readInt();
			int d = readInt();
			Employee next = ts.ceiling(new Employee("", skill, 0));
			if (next == null || next.skill - skill > d) {
				System.out.println("No suitable teacher!");
			} else {
				System.out.println(next.name);
			}

		}
	}

	static class Employee implements Comparable<Employee> {
		String name;
		int skill, index;

		Employee (String name, int skill, int index) {
			this.name = name;
			this.skill = skill;
			this.index = index;
		}

		@Override
		public int compareTo (Employee o) {
			if (skill == o.skill)
				return index - o.index;
			return skill - o.skill;
		}
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
