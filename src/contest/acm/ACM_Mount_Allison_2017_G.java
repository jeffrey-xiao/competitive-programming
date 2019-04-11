package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class ACM_Mount_Allison_2017_G {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, M, ans;
  static HashMap<String, Integer> toIndex = new HashMap<String, Integer>();
  ;
  static HashSet<Pair> badPairs = new HashSet<Pair>();
  static int[] curr, best;
  static String[] colors;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();

      toIndex.clear();
      badPairs.clear();
      curr = new int[N];
      colors = new String[N];
      best = null;
      ans = 0;

      for (int i = 0; i < N; i++) {
        curr[i] = i;
        String color = next();
        colors[i] = color;
        toIndex.put(color, i);
      }

      M = readInt();

      for (int i = 0; i < M; i++) {
        int a = toIndex.get(next());
        int b = toIndex.get(next());
        badPairs.add(new Pair(a, b));
      }

      permute(0);
      out.println(ans);
      for (int i = 0; i < N; i++)
        out.print(colors[best[i]] + " ");
      out.println();
    }

    out.close();
  }

  static void permute(int i) {
    if (i == N) {
      ans++;
      if (best == null)
        best = Arrays.copyOf(curr, N);
      else {
        boolean smaller = true;
        for (int j = 0; j < N; j++) {
          if (curr[j] > best[j]) {
            smaller = false;
            break;
          } else if (curr[j] < best[j]) {
            smaller = true;
            break;
          }
        }
        if (smaller)
          best = Arrays.copyOf(curr, N);
      }
      return;
    }
    for (int j = i; j < N; j++) {
      swap(i, j);
      if (i == 0 || !badPairs.contains(new Pair(curr[i], curr[i - 1])))
        permute(i + 1);
      swap(i, j);
    }
  }

  static void swap(int i, int j) {
    int temp = curr[i];
    curr[i] = curr[j];
    curr[j] = temp;
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

  static class Pair {
    int x, y;

    Pair(int x, int y) {
      this.x = Math.max(x, y);
      this.y = Math.min(x, y);
    }

    @Override
    public boolean equals(Object o) {
      if (o instanceof Pair) {
        Pair p = (Pair) o;
        return p.x == x && p.y == y;
      }
      return false;
    }

    @Override
    public int hashCode() {
      return x * 13 + y;
    }
  }
}
