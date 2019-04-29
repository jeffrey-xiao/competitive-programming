package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Spring_E {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int N = readInt();

    TreeMap<String, Integer> occ = new TreeMap<String, Integer>();

    for (int i = 0; i < N; i++) {
      char[] in = next().replace("-", "").toCharArray();
      for (int j = 0; j < in.length; j++) {
        if ('A' <= in[j] && in[j] <= 'C')
          in[j] = '2';
        if ('D' <= in[j] && in[j] <= 'F')
          in[j] = '3';
        if ('G' <= in[j] && in[j] <= 'I')
          in[j] = '4';
        if ('J' <= in[j] && in[j] <= 'L')
          in[j] = '5';
        if ('M' <= in[j] && in[j] <= 'O')
          in[j] = '6';
        if ('P' <= in[j] && in[j] <= 'S')
          in[j] = '7';
        if ('T' <= in[j] && in[j] <= 'V')
          in[j] = '8';
        if ('W' <= in[j] && in[j] <= 'Y')
          in[j] = '9';
      }
      String res = new String(in);
      if (!occ.containsKey(res)) {
        occ.put(res, 0);
      }
      occ.put(res, occ.get(res) + 1);
    }

    boolean dup = false;
    for (Map.Entry<String, Integer> e : occ.entrySet()) {
      if (e.getValue() > 1) {
        out.printf("%s-%s %d%n", e.getKey().substring(0, 3), e.getKey().substring(3), e.getValue());
        dup = true;
      }
    }

    if (!dup) {
      out.println("No duplicates.");
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
