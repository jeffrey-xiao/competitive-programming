package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class LinkedList {

  static final long shift = 44;
  static final long diff = 8000000000000l;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    long[] val = new long[1000002];
    for (int i = 0; i <= 1000000; i++)
      val[i] = i - 1;
    int prevIndex = 0;
    int currIndex = 1000000;
    int nextIndex = 1000001;
    int freeIndex = 999999;

    val[1000000] = 0 | ((long) (prevIndex ^ nextIndex) << shift);
    int n = readInt();
    for (int i = 0; i < n; i++) {
      char c = readCharacter();
      if (c == '+') {
        // v   v v -> existing
        // 1 2 3 4
        long v = readLong() + diff;
        // creating 2
        int newIndex = freeIndex;
        freeIndex = (int) val[freeIndex];

        // setting 2, 3
        val[newIndex] = v | ((long) (prevIndex ^ currIndex) << shift);
        val[currIndex] = clearLast(val[currIndex]) | ((long) (newIndex ^ nextIndex) << shift);

        // setting 1, 4
        int prevprev = (int) (val[prevIndex] >>> shift) ^ (currIndex);
        int nextnext = (int) (val[nextIndex] >>> shift) ^ (currIndex);
        val[prevIndex] = clearLast(val[prevIndex]) | ((long) (prevprev ^ newIndex) << shift);
        val[nextIndex] = clearLast(val[nextIndex]) | ((long) (nextnext ^ currIndex) << shift);

        nextIndex = currIndex;
        currIndex = newIndex;
      } else if (c == '-') {
        val[currIndex] = freeIndex;
        freeIndex = currIndex;

        val[nextIndex] = clearLast(val[nextIndex]) | ((long) (prevIndex ^ (int) ((val[nextIndex] >>> shift) ^ currIndex)) << shift);
        val[prevIndex] = clearLast(val[prevIndex]) | ((long) (nextIndex ^ (int) ((val[prevIndex] >>> shift) ^ currIndex)) << shift);

        currIndex = nextIndex;
        nextIndex = (int) ((val[nextIndex] >>> shift) ^ prevIndex);
      } else if (c == '=') {
        val[currIndex] = clearFirst(val[currIndex]) | (readLong() + diff);
      } else if (c == '!') {
        out.println((val[currIndex] & ((1l << shift) - 1)) - diff);
      } else if (c == '>') {
        prevIndex = currIndex;
        currIndex = nextIndex;
        nextIndex = (int) ((val[currIndex] >>> shift) ^ prevIndex);
      } else if (c == '<') {
        nextIndex = currIndex;
        currIndex = prevIndex;
        prevIndex = (int) ((val[currIndex] >>> shift) ^ nextIndex);
      }
    }
    out.close();
  }

  static long clearFirst(long n) {
    return n & ~((1l << shift) - 1);
  }

  static long clearLast(long n) {
    return n & ((1l << shift) - 1);
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
