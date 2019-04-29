package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class FHC_2016_Qualification_High_Security {

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

      char[][] g = new char[2][n];
      for (int i = 0; i < 2; i++)
        g[i] = next().toCharArray();

      int ans = 0;
      for (int j = 0; j <= 1; j++) {
        TreeSet<Integer> ts = new TreeSet<Integer>();
        for (int i = 0; i < n; i++) {
          if (g[j][i] == '.' && (i - 1 < 0 || g[j][i - 1] == 'X') && (i + 1 >= n || g[j][i + 1] == 'X') && g[(j + 1) % 2][i] == '.' && (i - 1 < 0 || g[(j + 1) % 2][i - 1] == 'X') && (i + 1 >= n || g[(j + 1) % 2][i + 1] == 'X')) {
            if (j == 0) {
              ans++;
            }
          } else if (g[j][i] == '.' && (i - 1 < 0 || g[j][i - 1] == 'X') && (i + 1 >= n || g[j][i + 1] == 'X'))
            ts.add(i);
        }
        int l = 0;
        for (int i = 0; i < n;) {
          if (g[(j + 1) % 2][i] == 'X') {
            if (i - 1 > l) {
              Integer remove = ts.floor(i - 1);
              if (remove != null && remove.intValue() >= l)
                ts.remove(remove);
              ans++;
            }
            i++;
            l = i;
            continue;
          } else {
            i++;
          }
        }
        if (n - 1 > l) {
          Integer remove = ts.floor(n - 1);
          if (remove != null && remove.intValue() >= l)
            ts.remove(remove);
          ans++;
        }
        ans += ts.size();
      }
      out.printf("Case #%d: %d%n", t, ans);
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

  static class Segment {
    int l, r;

    Segment(int l, int r) {
      this.l = l;
      this.r = r;
    }
  }
}
