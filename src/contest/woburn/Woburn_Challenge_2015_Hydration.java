package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Woburn_Challenge_2015_Hydration {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, K;

  static int[] cows, troughs;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    K = readInt();

    cows = new int[N];
    troughs = new int[M];

    for (int i = 0; i < N; i++)
      cows[i] = readInt();

    for (int j = 0; j < M; j++)
      troughs[j] = readInt();

    Arrays.sort(cows);
    Arrays.sort(troughs);

    int lo = 1;
    int hi = N;

    while (lo <= hi) {
      int mid = (lo + hi) >> 1;
      if (isPossible(mid)) {
        hi = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    out.println(lo == N + 1 ? -1 : lo);
    out.close();
  }

  static boolean isPossible (int val) {
    int min = 0;
    int cnt = 0;
    for (int i = 0; i < N; i++) {
      while (min < M && (troughs[min] < cows[i] - K || cnt == val)) {
        min++;
        cnt = 0;
      }
      if (min == M || troughs[min] > cows[i])
        return false;
      cnt++;
    }
    return true;
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
