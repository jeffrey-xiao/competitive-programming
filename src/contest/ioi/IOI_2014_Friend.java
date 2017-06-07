package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IOI_2014_Friend {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[] confidence, host, protocol;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    confidence = new int[n];
    host = new int[n];
    protocol = new int[n];
    for (int i = 0; i < n; i++)
      confidence[i] = readInt();
    for (int i = 1; i < n; i++) {
      host[i] = readInt();
      protocol[i] = readInt();
    }
    int res = 0;
    for (int i = n - 1; i >= 1; i--) {
      if (protocol[i] == 1)
        confidence[host[i]] += confidence[i];
      else if (protocol[i] == 2)
        confidence[host[i]] = Math.max(confidence[host[i]], confidence[i]);
      else {
        res += confidence[i];
        confidence[host[i]] = Math.max(0, confidence[host[i]] - confidence[i]);
      }
    }
    out.println(res + confidence[0]);

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
