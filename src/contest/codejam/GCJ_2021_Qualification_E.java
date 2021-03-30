package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2021_Qualification_E {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final double START = -3 + 0.03;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    int P = readInt();

    for (int t = 1; t <= T; t++) {
      char[][] students = new char[100][];
      for (int i = 0; i < 100; i++) {
        students[i] = next().toCharArray();
      }

      Test[] tests = new Test[10000];
      for (int i = 0; i < 10000; i++) {
        tests[i] = new Test(i, 0);
        for (int j = 0; j < 100; j++) {
          if (students[j][i] == '1') {
            tests[i].correct++;
          }
        }
      }

      Arrays.sort(tests);

      Integer maxStudentIndex = null;
      Double maxStudentError = null;

      for (int i = 0; i < 100; i++) {
        int correct = 0;
        for (int j = 0; j < 10000; j++) {
          if (students[i][j] == '1') {
            correct++;
          }
        }
        double p = 1.0 * correct / 10000;
        double skill = Math.log(1.0 / p - 1);

        double error = 0.0;
        for (int j = 0; j < 100; j++) {
          int partitionCorrect = 0;
          double Q = START + j * -1.06;
          for (int k = 0; k < 100; k++) {
            int index = j * 100 + k;
            if (students[i][tests[index].index] == '1') {
              partitionCorrect++;
            }
          }

          double expectedP = 1 / (1 + Math.pow(Math.E, skill - Q));
          double partitionP = 1.0 * partitionCorrect / 100;
          double d = expectedP - partitionP;
          error += d * d;
        }

        if (maxStudentError == null || error > maxStudentError) {
          maxStudentError = error;
          maxStudentIndex = i;
        }
      }

      out.printf("Case #%d: %d%n", t, maxStudentIndex + 1);
    }

    out.close();
  }

  static class Test implements Comparable<Test> {
    int index, correct;

    Test(int index, int cost) {
      this.index = index;
      this.correct = correct;
    }

    @Override
    public int compareTo(Test t) {
      return correct - t.correct;
    }
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
