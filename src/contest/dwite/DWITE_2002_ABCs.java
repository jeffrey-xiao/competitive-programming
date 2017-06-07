package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class DWITE_2002_ABCs {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int length;

  public static void main (String[] args) throws IOException {
    for (int t = 0; t < 5; t++) {
      length = 0;
      String s = readLine().toUpperCase();
      TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
      for (int x = 0; x < s.length(); x++) {
        char curr = s.charAt(x);
        if (curr - 'A' >= 0 && curr - 'A' <= 25)
          map = add(map, curr);
      }
      int count = 0;
      for (Entry<Character, Integer> e : map.entrySet()) {
        System.out.print(e.getKey() + "-" + e.getValue());
        if (count < length - 1)
          System.out.print(":");
        count++;
      }
      System.out.println();
    }
  }

  private static TreeMap<Character, Integer> add (TreeMap<Character, Integer> map, Character curr) {
    if (map.get(curr) == null) {
      map.put(curr, 1);
      length++;
    } else
      map.put(curr, map.get(curr) + 1);
    return map;
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

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
