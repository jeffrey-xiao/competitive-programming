package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class GCJ_2017_Round_1C_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, K;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();
      K = readInt();

      Pancake[] P = new Pancake[N];

      for (int i = 0; i < N; i++)
        P[i] = new Pancake(readInt(), readInt());

      double ans = 0;
      for (int i = 0; i < N; i++) {
        ArrayList<Pancake> p = new ArrayList<Pancake>();
        for (int j = 0; j < N; j++)
          if (i != j)
            p.add(P[j]);

        Collections.sort(p);

        int radius = P[i].radius;
        double currAns = Math.PI * 2.0 * P[i].radius * P[i].height;
        for (int j = 0; j < K - 1; j++) {
          radius = Math.max(radius, p.get(j).radius);
          currAns += Math.PI * 2.0 * p.get(j).radius * p.get(j).height;
        }

        ans = Math.max(ans, Math.PI * radius * radius + currAns);
      }
      out.printf("Case #%d: %f\n", t, ans);
    }

    out.close();
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

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Pancake implements Comparable<Pancake> {
    int radius, height;

    Pancake(int radius, int height) {
      this.radius = radius;
      this.height = height;
    }

    @Override
    public int compareTo(Pancake p) {
      Double d1 = Math.PI * height * radius * 2;
      Double d2 = Math.PI * p.height * p.radius * 2;
      return d2.compareTo(d1);
    }
  }
}
