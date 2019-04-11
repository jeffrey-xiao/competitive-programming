package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2010_S1 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    Computer[] computers = new Computer[n];
    for (int x = 0; x < n; x++)
      computers[x] = new Computer(next(), readInt(), readInt(), readInt());
    Arrays.sort(computers);
    for (int x = 0; x < Math.min(computers.length, 2); x++)
      System.out.println(computers[x].name);

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

  static class Computer implements Comparable<Computer> {
    String name;
    int value;

    Computer(String name, int RAM, int CPU, int disk) {
      this.name = name;
      value = RAM * 2 + CPU * 3 + disk;
    }

    @Override
    public int compareTo(Computer o) {
      if (o.value == value)
        return name.compareTo(o.name);
      return o.value - value;
    }
  }
}
