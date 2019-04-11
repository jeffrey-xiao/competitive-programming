package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2009_J4 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    String[] words = {"WELCOME", "TO", "CCC", "GOOD", "LUCK", "TODAY"};
    Integer[] lengths = {7, 2, 3, 4, 4, 5};
    int currWords = 0;
    int currLength = 0;
    ArrayList<String> currLine = new ArrayList<String>();
    for (int x = 0; x < 6; x++) {
      if (currLength + lengths[x] >= n - (currWords - 1)) {

        int[] gaps = getGap(currWords, n - currLength);
        for (int y = 0; y < currLine.size(); y++) {
          System.out.print(currLine.get(y) + getDot(gaps[y]));
        }
        System.out.println();
        currWords = 1;
        currLength = lengths[x];
        currLine.clear();
        currLine.add(words[x]);
      } else {
        currLength += lengths[x];
        currLine.add(words[x]);
        currWords++;
      }
    }
    int[] gaps = getGap(currWords, n - currLength);
    for (int y = 0; y < currLine.size(); y++) {
      System.out.print(currLine.get(y) + getDot(gaps[y]));
    }
  }

  private static int[] getGap(int i, int j) {
    int[] gaps = new int[i];
    int total = 0;
    if (i != 1) {
      int average = j / (i - 1);
      int remainder = j % (i - 1);
      for (int x = 0; x < i - 1; x++) {
        if (x < remainder) {
          gaps[x] = average + 1;
          total += average + 1;
        } else {
          gaps[x] = average;
          total += average;
        }
      }
    }
    gaps[i - 1] = (j - total);
    return gaps;
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static String getDot(int n) {
    String s = "";
    for (int x = 0; x < n; x++)
      s += ".";
    return s;
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
