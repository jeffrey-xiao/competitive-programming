/*
 * This algorithm determines the index of a permutation of the first N natural numbers.
 * 
 * Time complexity: O(N^2)
 */

package codebook.algorithms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PermutationIndex {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static int[] a;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    a = new int[n];

    for (int i = 0; i < n; i++)
      a[i] = readInt();

    int index = 0;
    int position = 2;
    int factor = 1;
    for (int i = n - 2; i >= 0; i--) {
      int cnt = 0;
      for (int j = i + 1; j < n; j++)
        if (a[i] > a[j])
          cnt++;
      index += cnt * factor;
      factor *= position;
      position++;
    }
    out.println(index + 1);

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
