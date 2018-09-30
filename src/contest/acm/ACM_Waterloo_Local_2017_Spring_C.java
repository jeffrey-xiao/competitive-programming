package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Spring_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static double[] radius;
  static double[] max;
  static double ans;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    for (int i = 0; i < N; i++) {
      int M = readInt();
      radius = new double[M];
      max = new double[M];
      ans = 1 << 30;

      for (int j = 0; j < M; j++) {
        radius[j] = readDouble();
      }

      permute(0);
      out.printf("%.3f\n", ans);
    }

    out.close();
  }

  static void permute(int i) {
    if (i == radius.length) {
      max[0] = radius[0];
      for (int j = 1; j < radius.length; j++) {
        double maxLen = 0;
        for (int k = 0; k < j; k++) {
          double currLen = Math.sqrt((radius[j] + radius[k]) * (radius[j] + radius[k]) - (radius[j] - radius[k]) * (radius[j] - radius[k]));
          if (currLen + max[k] > maxLen) {
            maxLen = currLen + max[k];
          }
        }
        max[j] = Math.max(maxLen, radius[j]);
      }
      double length = 0;
      for (int j = 0; j < radius.length; j++) {
        length = Math.max(length, max[j] + radius[j]);
      }
      if (length < ans) {
        ans = length;
      }
      return;
    }

    for (int j = i; j < radius.length; j++) {
      swap(i, j);
      permute(i + 1);
      swap(i, j);
    }
  }

  static void swap(int i, int j) {
    double temp = radius[i];
    radius[i] = radius[j];
    radius[j] = temp;
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
