import java.io.*;
import java.util.*;

public class A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    long A = readInt();
    long B = readInt();
    long C = readInt();

    long weight = A * B / gcd(A, B);
    weight = weight * C / gcd(weight, C);

    ArrayList<Long> guests = new ArrayList<Long>();
    guests.add(A);
    guests.add(B);
    guests.add(C);
    Collections.sort(guests);

    ArrayList<Long> pieces = new ArrayList<Long>();
    for (int i = 0; i < guests.get(2); i++) {
      pieces.add(weight / guests.get(2));
    }

    long remaining = weight / guests.get(1);
    ArrayList<Long> newPieces = new ArrayList<Long>();
    for (int i = 0; i < pieces.size(); i++) {
      if (pieces.get(i) <= remaining) {
        remaining -= pieces.get(i);
        newPieces.add(pieces.get(i));
      } else {
        newPieces.add(remaining);
        newPieces.add(pieces.get(i) - remaining);
        remaining = weight / guests.get(1) - (pieces.get(i) - remaining);
      }
      if (remaining == 0) {
        remaining = weight / guests.get(1);
      }
    }

    pieces = newPieces;
    newPieces = new ArrayList<Long>();
    remaining = weight / guests.get(0);
    for (int i = 0; i < pieces.size(); i++) {
      if (pieces.get(i) <= remaining) {
        remaining -= pieces.get(i);
        newPieces.add(pieces.get(i));
      } else {
        newPieces.add(remaining);
        newPieces.add(pieces.get(i) - remaining);
        remaining = weight / guests.get(0) - (pieces.get(i) - remaining);
      }
      if (remaining == 0) {
        remaining = weight / guests.get(0);
      }
    }

    pieces = newPieces;
    long[][] ans = new long[pieces.size()][3];
    int j = 0;
    for (int i = 0; i < A; i++) {
      remaining = weight / A;
      while (remaining > 0) {
        remaining -= pieces.get(j);
        ans[j][0] = i + 1;
        j++;
      }
    }
    j = 0;
    for (int i = 0; i < B; i++) {
      remaining = weight / B;
      while (remaining > 0) {
        remaining -= pieces.get(j);
        ans[j][1] = i + 1;
        j++;
      }
    }
    j = 0;
    for (int i = 0; i < C; i++) {
      remaining = weight / C;
      while (remaining > 0) {
        remaining -= pieces.get(j);
        ans[j][2] = i + 1;
        j++;
      }
    }

    out.println(pieces.size());
    for (int i = 0; i < pieces.size(); i++) {
      out.printf("%d %d %d %d\n", pieces.get(i), ans[i][0], ans[i][1], ans[i][2]);
    }

    out.close();
  }

  static long gcd(long a, long b) {
    return b == 0 ? a : gcd(b, a % b);
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
