package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Woburn_Challenge_2016_Away_Mission {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, Q;
  static int[] R, G, B, best;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    Q = readInt();
    R = new int[N];
    G = new int[N];
    B = new int[N];
    best = new int[N];

    for (int i = 0; i < N; i++)
      R[i] = readInt();
    for (int i = 0; i < N; i++)
      G[i] = readInt();
    for (int i = 0; i < N; i++)
      B[i] = readInt();

    Arrays.sort(G);
    Arrays.sort(B);
    Arrays.sort(R);
    if (Q == 1) {
      reverse(B);
      for (int i = 0; i < N; i++)
        best[i] = Math.max(B[i], G[i]);
      Arrays.sort(best);
      int pointer = 0, i;
      for (i = 0; i < N; i++) {
        while (pointer < N && best[pointer] < R[i])
          pointer++;
        if (pointer == N)
          break;
        pointer++;
      }
      out.println(N - i);
    } else if (Q == 2) {
      for (int i = 0; i < N; i++)
        best[i] = Math.max(B[i], G[i]);
      Arrays.sort(best);
      reverse(best);
      reverse(R);
      int pointer = 0, i;
      for (i = 0; i < N; i++) {
        while (pointer < N && R[i] <= best[pointer])
          pointer++;
        if (pointer == N)
          break;
        pointer++;
      }
      out.println(i);
    }

    out.close();
  }

  static void reverse(int[] a) {
    for (int i = 0; i < a.length / 2; i++)
      swap(a, i, a.length - i - 1);
  }

  static void swap(int[] a, int i, int j) {
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
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
