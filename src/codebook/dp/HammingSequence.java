/*
 * Dynamic programming algorithm that finds the mth term of the sequence that evenly divide powers of N.
 *
 * Time complexity: O(NM) where N is the number of elements to find, and M is the number of factors.
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class HammingSequence {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int n = readInt();
    int m = readInt();

    // hamming represents the hamming sequence
    // one-based indexing
    // "candidates" represents the viable candidates of the current index
    // "index" represents the index of the next smallest value
    int[] hamming = new int[m + 1];
    hamming[0] = 1;
    int[] factors = factor(n);
    int[] candidates = Arrays.copyOf(factors, factors.length);
    int[] index = new int[factors.length];
    for (int x = 1; x < hamming.length; x++) {
      int min = candidates[0];
      for (int y = 1; y < factors.length; y++)
        min = Math.min(candidates[y], min);
      hamming[x] = min;
      for (int y = 0; y < factors.length; y++)
        if (candidates[y] == min) {
          index[y]++;
          candidates[y] = hamming[index[y]] * factors[y];
        }
    }
    out.println(hamming[m]);
    out.close();
  }

  static int[] factor(int i) {
    // function that returns the factors of i in an array
    String f = "";
    for (int x = 2; x * x <= i; x++) {
      if (i % x == 0) {
        f += (x + " ");
        while (i % x == 0)
          i /= x;
      }
    }
    if (i != 1)
      f += (i + " ");
    String[] fs = f.substring(0, f.length() - 1).split(" ");
    int[] factors = new int[fs.length];
    for (int x = 0; x < fs.length; x++)
      factors[x] = Integer.parseInt(fs[x]);
    return factors;
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
