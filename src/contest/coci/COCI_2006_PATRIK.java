package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class COCI_2006_PATRIK {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    Stack<Person> s = new Stack<Person>();
    long count = 0;
    for (int x = 0; x < n; x++) {
      int h = readInt();
      long pre = 0;
      long c = 0;
      for (; !s.isEmpty() && s.peek().height < h; s.pop()) {
        c++;
      }
      if (!s.isEmpty() && s.peek().height == h) {
        pre += s.peek().precede + 1;
      } else if (!s.isEmpty() && s.peek().height > h) {
        pre++;
      }

      count += (c += pre);

      s.push(new Person(h, pre));
    }
    System.out.println(count);
  }

  static class Person {
    int height;
    long precede;

    Person (int height, long precede) {
      this.height = height;
      this.precede = precede;
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
