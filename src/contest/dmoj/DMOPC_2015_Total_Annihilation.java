package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DMOPC_2015_Total_Annihilation {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static long[] a;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    a = new long[n + m];
    for (int i = 0; i < n + m; i++)
      a[i] = readLong();
    int left = (n + m) / 2;
    int right = n + m - left;
    HashMap<Long, Long> hs = new HashMap<Long, Long>();
    long ans = 0;
    for (int i = 0; i < (1 << left); i++) {
      long sum = 0;
      for (int j = 0; j < left; j++)
        if ((i & 1 << j) > 0)
          sum += j < n ? a[j] : -a[j];
      if (!hs.containsKey(sum))
        hs.put(sum, 0l);
      hs.put(sum, hs.get(sum) + 1);
    }
    for (int i = 0; i < (1 << right); i++) {
      long sum = 0;
      for (int j = 0; j < right; j++)
        if ((i & 1 << j) > 0)
          sum += left + j < n ? a[left + j] : -a[left + j];

      if (hs.containsKey(-sum))
        ans += hs.get(-sum);
    }
    out.println(ans - 1);
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
