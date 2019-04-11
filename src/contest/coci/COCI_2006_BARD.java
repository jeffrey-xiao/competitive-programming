package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.StringTokenizer;

public class COCI_2006_BARD {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    HashSet<Integer>[] v = new HashSet[n];
    int songCount = 0;
    for (int x = 0; x < n; x++)
      v[x] = new HashSet<Integer>();
    for (int x = 0; x < k; x++) {
      int num = readInt();
      ArrayList<Integer> present = new ArrayList<Integer>();
      for (int y = 0; y < num; y++)
        present.add(readInt() - 1);
      Collections.sort(present);
      if (present.get(0) == 0) {
        for (int y = 1; y < num; y++) {
          v[present.get(y)].add(x);
        }
        songCount++;
      } else {
        HashSet<Integer> all = new HashSet<Integer>();
        for (int y = 0; y < num; y++)
          all.addAll(v[present.get(y)]);
        for (int y = 0; y < num; y++) {
          v[present.get(y)].clear();
          v[present.get(y)].addAll(all);
        }
      }
    }
    int count = 1;
    System.out.println(1);
    for (HashSet<Integer> hs : v) {
      if (hs.size() == songCount)
        System.out.println(count);
      count++;
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
