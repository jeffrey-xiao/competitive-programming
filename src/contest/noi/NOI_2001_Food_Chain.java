package contest.noi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class NOI_2001_Food_Chain {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  static int[] id;
  static int[] size;
  static ArrayList<HashSet<Integer>> eat = new ArrayList<HashSet<Integer>>();

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    size = new int[n];
    id = new int[n];
    for (int x = 0; x < n; x++) {
      eat.add(new HashSet<Integer>());
      size[x] = 1;
      id[x] = x;
    }
    int total = 0;
    for (int z = 0; z < k; z++) {
      int c = readInt();
      int x = readInt() - 1;
      int y = readInt() - 1;
      if (x >= n || y >= n) {
        total++;
        continue;
      }
      System.out.println("Z: " + z + " " + eat.get(find(x)));
      System.out.println("FIND X " + find(x));
      if (c == 1) {
        if (eat.get(find(y)).contains(find(x)) || eat.get(find(x)).contains(find(y))) {
          total++;
          continue;
        }
        union(x, y);
      } else if (c == 2) {

        if (find(y) == find(x) || eat.get(find(y)).contains(find(x))) {
          total++;
          System.out.println("Z: " + z + " HERE");
          continue;
        } else {
          System.out.println("ADDED HERE");

          eat.get(find(x)).add(find(y));
          System.out.println(eat.get(find(x)));
        }
      }
    }
    System.out.println(total);
  }

  public static int find (int i) {
    while (i != id[i]) {
      id[i] = id[id[i]];
      i = id[i];
    }
    return i;
  }

  public static void union (int x, int y) {
    int rootx = find(x);
    int rooty = find(y);
    if (rootx == rooty)
      return;
    if (size[rootx] < size[rooty]) {
      id[rootx] = id[rooty];
      size[rooty] += size[rootx];
      eat.get(rooty).addAll(eat.get(rootx));
    } else {
      id[rooty] = id[rootx];
      size[rootx] += size[rooty];
      eat.get(rootx).addAll(eat.get(rooty));
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
