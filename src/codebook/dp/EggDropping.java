/*
 * Dynamic programming algorithm that computes the number of trials to determine which floor out of K floors will an egg break withing N eggs.
 * Eggs cannot be reused if they are broken.
 *
 * Time complexity: O(NK) where N is the number of eggs and K is the number of floors.
 */

package codebook.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EggDropping {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));
    int n = readInt();
    int k = readInt();
    int[][] eggFloor = new int[n + 1][k + 1];
    int res;

    // We need one trial for one floor and 0 trials for 0 floors
    for (int i = 1; i <= n; i++) {
      eggFloor[i][1] = 1;
      eggFloor[i][0] = 0;
    }

    // We always need i trials for one egg and i floors.
    for (int i = 1; i <= k; i++)
      eggFloor[1][i] = i;

    for (int i = 2; i <= n; i++) {
      for (int j = 2; j <= k; j++) {
        eggFloor[i][j] = Integer.MAX_VALUE;
        for (int x = 1; x <= j; x++) {
          res = 1 + Math.max(eggFloor[i - 1][x - 1], eggFloor[i][j - x]);
          if (res < eggFloor[i][j])
            eggFloor[i][j] = res;
        }
      }
    }
    out.println(eggFloor[n][k]);
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
