package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class VMSS_GGG {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();

    HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

    for (int i = 0; i < n; i++)
      hm.put(readInt(), i);

    int m = readInt();

    ArrayList<Integer> vals = new ArrayList<Integer>();
    int[] dp = new int[m + 1];
    for (int i = 1; i <= m; i++)
      dp[i] = 1 << 30;

    for (int i = 0; i < m; i++) {
      int val = readInt();
      if (hm.get(val) != null)
        vals.add(hm.get(val));
    }

    int len = 0;
    for (int i = 0; i < vals.size(); i++) {
      int lo = 1;
      int hi = len;
      while (lo <= hi) {
        int mid = (lo + hi) >> 1;
        if (vals.get(dp[mid]) >= vals.get(i))
          hi = mid - 1;
        else
          lo = mid + 1;
      }
      dp[lo] = i;
      if (lo > len)
        len = lo;
    }
    out.println(len);

    out.close();
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
