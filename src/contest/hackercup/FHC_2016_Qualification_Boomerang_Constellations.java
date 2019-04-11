package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class FHC_2016_Qualification_Boomerang_Constellations {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      int n = readInt();
      int[] x = new int[n];
      int[] y = new int[n];

      int ans = 0;

      for (int i = 0; i < n; i++) {
        x[i] = readInt();
        y[i] = readInt();
      }

      for (int i = 0; i < n; i++) {
        HashMap<Integer, Integer> cnt = new HashMap<Integer, Integer>();
        for (int j = 0; j < n; j++) {
          int dist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
          if (!cnt.containsKey(dist))
            cnt.put(dist, 0);
          cnt.put(dist, cnt.get(dist) + 1);
        }
        for (Map.Entry<Integer, Integer> e : cnt.entrySet())
          ans += (e.getValue() * (e.getValue() - 1));
      }
      out.printf("Case #%d: %d\n", t, ans / 2);
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
}
