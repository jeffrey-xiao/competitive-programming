package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_Target_Practice {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M;
  static long[] radius;
  static int[] score, occ;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    radius = new long[N];
    score = new int[N];
    occ = new int[N];

    for (int i = 0; i < N; i++)
      radius[i] = readInt();

    for (int i = 0; i < N; i++)
      score[i] = readInt();

    for (int i = 0; i < M; i++) {
      long x = readInt();
      long y = readInt();
      bsearch(x * x + y * y);
    }

    int min = 0;
    int max = 0;

    Arrays.sort(occ);
    Arrays.sort(score);

    for (int i = 0; i < N; i++) {
      min += occ[i] * score[N - i - 1];
      max += occ[i] * score[i];
    }

    out.printf("%d%n%d%n", min, max);
    out.close();
  }

  static void bsearch(long dist) {
    int lo = 0;
    int hi = N - 1;

    while (lo <= hi) {
      int mid = (lo + hi) >> 1;

      if (radius[mid] * radius[mid] < dist)
        lo = mid + 1;
      else
        hi = mid - 1;
    }
    if (lo != N)
      occ[lo]++;
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
}
