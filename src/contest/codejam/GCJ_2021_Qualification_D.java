package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2021_Qualification_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    int N = readInt();
    int Q = readInt();

    for (int t = 1; t <= T; t++) {
      ArrayList<Integer> nums = new ArrayList<Integer>();
      for (int i = 1; i <= N; i++) {
        nums.add(i);
      }
      nums = sort(nums, null);
      for (int i = 0; i < nums.size(); i++) {
        out.printf("%d ", nums.get(i));
      }
      out.println();
      out.flush();
      readInt();
    }

    out.close();
  }

  static ArrayList<Integer> sort(ArrayList<Integer> nums, Partition p) throws IOException {
    if (nums.size() <= 1) {
      return nums;
    }

    int p1 = nums.get(0);
    int p2 = nums.get(1);
    if (p != null) {
      if (p.type == 0) {
        out.printf("%d %d %d%n", p1, p2, p.p1);
        out.flush();
        int median = readInt();
        if (median == p1) {
          int temp = p1;
          p1 = p2;
          p2 = temp;
        }
      } else if (p.type == 1) {
        out.printf("%d %d %d%n", p1, p2, p.p1);
        out.flush();
        int median = readInt();
        if (median == p2) {
          int temp = p1;
          p1 = p2;
          p2 = temp;
        }
      } else {
        out.printf("%d %d %d%n", p1, p2, p.p2);
        out.flush();
        int median = readInt();
        if (median == p2) {
          int temp = p1;
          p1 = p2;
          p2 = temp;
        }
      }
    }

    ArrayList<Integer> left = new ArrayList<Integer>();
    ArrayList<Integer> center = new ArrayList<Integer>();
    ArrayList<Integer> right = new ArrayList<Integer>();

    for (int i = 2; i < nums.size(); i++) {
      int n = nums.get(i);
      out.printf("%d %d %d%n", p1, p2, n);
      out.flush();
      int median = readInt();
      if (median == p1) {
        left.add(n);
      } else if (median == p2) {
        right.add(n);
      } else {
        center.add(n);
      }
    }

    left = sort(left, new Partition(p1, p2, 0));
    center = sort(center, new Partition(p1, p2, 1));
    right = sort(right, new Partition(p1, p2, 2));

    left.add(p1);
    left.addAll(center);
    left.add(p2);
    left.addAll(right);
    return left;
  }

  static class Partition {
    int p1, p2;
    // 0 for left, 1 for center, 2 for right.
    int type;

    Partition (int p1, int p2, int type) {
      this.p1 = p1;
      this.p2 = p2;
      this.type = type;
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
