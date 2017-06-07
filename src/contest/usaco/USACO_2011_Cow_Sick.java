package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2011_Cow_Sick {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    Queue<Integer> notes = new LinkedList<Integer>();
    for (int x = 0; x < n; x++)
      notes.add(readInt());
    ArrayList<Integer> currNotes = new ArrayList<Integer>();
    ArrayList<Integer> indices = new ArrayList<Integer>();
    int count = 0;
    int m = readInt();
    int[] badNotes = new int[m];

    for (int x = 0; x < m; x++)
      badNotes[x] = readInt();
    Arrays.sort(badNotes);
    for (int x = 0; x <= n; x++) {
      if (x < m)
        currNotes.add(notes.poll());
      else {
        boolean isBad = true;

        ArrayList<Integer> copy = new ArrayList<Integer>(currNotes);
        Collections.sort(copy);

        int diff = copy.get(0) - badNotes[0];
        for (int y = 1; y < m; y++) {
          if (copy.get(y) - diff != badNotes[y]) {
            isBad = false;
          }
        }
        if (isBad) {
          count++;
          indices.add(x - m + 1);
        }
        currNotes.remove(0);
        currNotes.add(notes.poll());
      }
    }
    System.out.println(count);
    for (Integer i : indices)
      System.out.println(i);
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
