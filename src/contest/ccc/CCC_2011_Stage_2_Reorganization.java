package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class CCC_2011_Stage_2_Reorganization {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    Employee[] e = new Employee[n + 1];
    for (int x = 1; x <= n; x++)
      e[x] = new Employee(x, readInt());
    TreeMap<Employee, Integer> ts = new TreeMap<Employee, Integer>();
    ts.put(e[1], 2);
    for (int x = 2; x <= n; x++) {
      Employee curr = ts.floorKey(e[x]);
      if (curr == null) {
        System.out.println("NO");
        return;
      }
      ts.put(curr, ts.get(curr) - 1);
      if (ts.get(curr) == 0)
        ts.remove(curr);
      ts.put(e[x], 2);
    }
    System.out.println("YES");
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Employee implements Comparable<Employee> {
    int id, rank;

    Employee(int id, int rank) {
      this.id = id;
      this.rank = rank;
    }

    @Override
    public int compareTo(Employee o) {
      return rank - o.rank;
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Employee) {
        Employee e = (Employee)o;
        return id == e.id;
      }
      return false;
    }
  }
}