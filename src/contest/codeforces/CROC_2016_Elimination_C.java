package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CROC_2016_Elimination_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int k = readInt();
    int[] a = new int[n + 1];
    String in = " " + readLine();
    TreeSet<Integer> ts = new TreeSet<Integer>();
    for (int i = 1; i <= n; i++) {
      if (in.charAt(i) == '0')
        ts.add(i);
      a[i] = a[i - 1] + ((in.charAt(i) - '0') == 0 ? 1 : 0);
    }

    int min = 1 << 30;
    int l = 0;
    int r = 1;
    while (r <= n) {
      if (a[r] - a[l] == k + 1) {
        int mid = (r + l + 1) / 2;
        Integer lo = ts.floor(mid);
        Integer hi = ts.ceiling(mid);
        int minDist = 1 << 30;
        int left = l + 1;
        int right = r;
        if (lo != null && left <= lo && lo <= right) {
          minDist = Math.min(minDist, Math.max(right - lo, lo - left));
        }
        if (hi != null && left <= hi && hi <= right) {
          minDist = Math.min(minDist, Math.max(right - hi, hi - left));
        }
        min = Math.min(min, minDist);
        l++;
      } else if (a[r] - a[l] < k + 1) {
        r++;
      } else {
        l++;
      }
    }

    out.println(min);
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
