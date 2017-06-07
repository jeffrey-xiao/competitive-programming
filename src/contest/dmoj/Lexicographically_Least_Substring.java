package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Lexicographically_Least_Substring {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter pr = new PrintWriter(new OutputStreamWriter(System.out));
  static StringTokenizer st;
  static final SuffixComparator C = new SuffixComparator();
  static char[] input;
  static int len;

  static Integer[] res;
  static int[] order;
  static int[] newOrder;
  static int sz = 0;

  public static void main (String[] args) throws IOException {
    input = next().toCharArray();
    len = input.length;
    res = new Integer[len];
    order = new int[len];
    newOrder = new int[len];
    int minLen = readInt();
    for (int i = 0; i < len; i++) {
      res[i] = i;
      order[i] = (int)(input[i]);
      newOrder[i] = 0;
    }
    for (sz = 1;; sz <<= 1) {
      Arrays.sort(res, C);
      for (int i = 0; i < len - 1; i++)
        newOrder[i + 1] = newOrder[i] + (C.compare(res[i], res[i + 1]) < 0 ? 1 : 0);
      for (int i = 0; i < len; i++)
        order[res[i]] = newOrder[i];
      if (newOrder[len - 1] == len - 1)
        break;
    }
    for (int i = 0; i < len; i++)
      if (len - res[i] >= minLen) {
        System.out.println(new String(input).substring(res[i], res[i] + minLen));
        break;
      }
  }

  static class SuffixComparator implements Comparator<Integer> {
    @Override
    public int compare (Integer o1, Integer o2) {
      if (order[o1] != order[o2])
        return order[o1] - order[o2];
      if ((o1 += sz) < len & (o2 += sz) < len)
        return order[o1] - order[o2];
      return o2 - o1;
    }
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
