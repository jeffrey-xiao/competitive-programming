package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: ariprog
 */
import java.util.StringTokenizer;

public class ariprog {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("ariprog.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));
    int n = readInt();
    int m = readInt();

    int size = m * m * 2;
    boolean[] check = new boolean[size + 1];

    for (int x = 0; x <= m; x++)
      for (int y = 0; y <= m; y++)
        check[x * x + y * y] = true;

    boolean empty = true;

    for (int x = 1; x < size / (n - 1) + 1; x++) {
      main:
      for (int y = 0; y <= size; y++) {
        if (!check[y])
          continue;
        for (int z = n - 1; z >= 1; z--) {
          if (y + z * x > size || !check[y + z * x])
            continue main;
        }
        pr.println(y + " " + x);
        empty = false;
      }
    }

    if (empty)
      pr.println("NONE");
    pr.close();
    System.exit(0);
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
}
