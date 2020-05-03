package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_1C_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      int U = readInt();
      HashMap<Character, Integer> occ = new HashMap<>();
      HashSet<Character> charset = new HashSet<>();
      for (int i = 0; i < 10000; i++) {
        next();
        char[] R = next().toCharArray();
        for (int j = 0; j < R.length; j++) {
          charset.add(R[j]);
        }
        if (occ.containsKey(R[0])) {
          occ.put(R[0], occ.get(R[0]) + 1);
        } else {
          occ.put(R[0], 1);
        }
      }
      ArrayList<Occurrence> sortedOcc = new ArrayList<>();
      for (HashMap.Entry<Character, Integer> e : occ.entrySet()) {
        sortedOcc.add(new Occurrence(e.getKey(), e.getValue()));
      }
      Collections.sort(sortedOcc, Collections.reverseOrder());
      Character zero = null;
      for (Character c : charset) {
        if (!occ.containsKey(c)) {
          zero = c;
        }
      }
      sortedOcc.add(0, new Occurrence(zero, 0));
      out.printf("Case #%d: ", t);
      for (int i = 0; i < 10; i++) {
        out.print(sortedOcc.get(i).c);
      }
      out.println();
    }

    out.close();
  }

  static class Occurrence implements Comparable<Occurrence> {
    char c;
    int occ;
    Occurrence(char c, int occ) {
      this.c = c;
      this.occ = occ;
    }

    @Override
    public int compareTo(Occurrence e) {
      return occ - e.occ;
    }
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
