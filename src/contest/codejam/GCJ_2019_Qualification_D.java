package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Qualification_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, B, F;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      B = readInt();
      F = readInt();

      ArrayList<Integer> missing = new ArrayList<Integer>();
      missing.add(B);

      int blockSize = 16;
      while (blockSize > 0) {
        ArrayList<Integer> newMissing = new ArrayList<Integer>();
        for (int i = 0; i < N; i++) {
          out.print((i / blockSize) % 2);
        }
        out.println();
        out.flush();

        String res = next();
        int resIndex = 0;
        int remaining = N;
        for (int i = 0; i < missing.size(); i++) {
          int prevBlockSize = blockSize * 2;
          if (blockSize == 16) {
            prevBlockSize = N;
          }
          if (i == missing.size() - 1 && N % prevBlockSize != 0) {
            prevBlockSize = N % prevBlockSize;
          }
          int expectedBlockSize = prevBlockSize - missing.get(i);
          int expectedChar = 0;
          int count = 0;
          int added = 0;
          for (int j = 0; j < expectedBlockSize; j++, resIndex++) {
            if (res.charAt(resIndex) != expectedChar + '0') {
              int currBlockSize = Math.min(remaining, blockSize);
              remaining -= currBlockSize;
              newMissing.add(currBlockSize - count);
              added++;
              count = 1;
              expectedChar = (expectedChar + 1) % 2;
            } else {
              count++;
            }
          }
          while (remaining > 0 && added != (prevBlockSize + blockSize - 1) / blockSize) {
            int currBlockSize = Math.min(remaining, blockSize);
            remaining -= currBlockSize;
            newMissing.add(currBlockSize - count);
            added++;
            count = 0;
          }
        }
        missing = newMissing;
        blockSize /= 2;
      }

      int missingCount = 0;
      for (int i = 0; i < missing.size(); i++) {
        if (missing.get(i) == 1) {
          missingCount++;
          out.print(i);
          if (missingCount != B) {
            out.print(" ");
          }
        }
      }
      out.println();
      out.flush();
      if (readInt() == -1) {
        out.close();
        return;
      }
    }

    out.close();
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
